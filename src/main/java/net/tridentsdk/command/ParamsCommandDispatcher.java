/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.tridentsdk.command;

import com.esotericsoftware.reflectasm.MethodAccess;
import lombok.Getter;
import net.tridentsdk.command.annotation.AllowedSourceTypes;
import net.tridentsdk.command.annotation.MaxCount;
import net.tridentsdk.command.annotation.MinCount;
import net.tridentsdk.command.annotation.PermissionRequired;
import net.tridentsdk.logger.Logger;
import net.tridentsdk.ui.chat.ChatColor;
import net.tridentsdk.ui.chat.ChatComponent;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nick Robson
 */
public class ParamsCommandDispatcher extends CommandDispatcher {

    private final MethodAccess access;
    private final CommandListener container;
    private final int methodIndex;
    @Getter private final String plugin;
    @Getter private final Command command;

    private final Parameter[] parameters;

    private final int varargsIndex;

    private final int minimumParameters;
    private final int maximumParameters;

    private final int minimumVariableArgs;
    private final int maximumVariableArgs;

    private final String[] permissionRequired;
    private final CommandSourceType[] allowedSourceTypes;

    public ParamsCommandDispatcher(MethodAccess access, CommandListener container, Method method, String plugin, Command command) {
        this.access = access;
        this.container = container;
        this.methodIndex = access.getIndex(method.getName(), method.getParameterTypes());
        this.plugin = plugin;
        this.command = command;
        this.parameters = Arrays.copyOfRange(method.getParameters(), 2, method.getParameterCount());
        int vai = -1, min = parameters.length, max = parameters.length;
        int minVar = 0, maxVar = Integer.MAX_VALUE;
        boolean warnedNoParamNames = false;
        for (int i = 0; i < this.parameters.length; i++) {
            Parameter param = this.parameters[i];
            if (!warnedNoParamNames && !param.isNamePresent()) {
                Logger.get(CommandHandler.class).warn("Warning while registering command \"" + command.name() + "\" from " + CommandHandler.getMethodSignature(method) + ": parameter names are missing, so help messages will look terrible. Add '-parameters' to compile options for parameter names!");
                warnedNoParamNames = true;
            }
            if (param.getType().isArray()) {
                if (vai != -1)
                    throw new RuntimeException("cannot have more than one variable length parameter");
                vai = i;
                MinCount a0 = param.getAnnotation(MinCount.class);
                if (a0 != null) {
                    if ((minVar = a0.value()) < 0)
                        throw new IllegalArgumentException("minimum cannot be less than 0");
                    min += minVar - 1;
                } else {
                    min -= 1;
                }
                MaxCount a1 = param.getAnnotation(MaxCount.class);
                if (a1 != null) {
                    if ((maxVar = a1.value()) < 0)
                        throw new IllegalArgumentException("maximum cannot be less than 0");
                    max += maxVar - 1;
                } else {
                    max = Integer.MAX_VALUE;
                }
                if (min > max)
                    throw new RuntimeException("minimum cannot be more than the maximum");
            }
        }
        this.varargsIndex = vai;
        this.minimumParameters = min;
        this.maximumParameters = max;
        this.minimumVariableArgs = minVar;
        this.maximumVariableArgs = maxVar;

        PermissionRequired permissionRequired = method.getAnnotation(PermissionRequired.class);
        if (permissionRequired != null && permissionRequired.value().length > 0) {
            this.permissionRequired = permissionRequired.value();
        } else {
            this.permissionRequired = null;
        }

        AllowedSourceTypes sources = method.getAnnotation(AllowedSourceTypes.class);
        if (sources != null && sources.value().length > 0) {
            this.allowedSourceTypes = sources.value();
        } else {
            this.allowedSourceTypes = new CommandSourceType[]{ CommandSourceType.ALL };
        }
    }

    private String getUsage() {
        StringBuilder sb = new StringBuilder("/" + command.name());
        for (Parameter param : parameters) {
            sb.append(" <").append(param.getName());
            Class<?> type = param.getType();
            if (param.getType().isArray()) {
                sb.append("...");
                type = type.getComponentType();
            }
            if (type != String.class && type != Object.class) {
                sb.append(" : ").append(type.getSimpleName());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @Override
    public void run(String cmd, CommandSource source, String[] args) {
        if (this.permissionRequired != null) {
            boolean hasPermission = false;
            for (String permission : this.permissionRequired) {
                if (source.hasPermission(permission)) {
                    hasPermission = true;
                }
            }
            if (!hasPermission) {
                source.sendMessage(ChatComponent.create().setColor(ChatColor.RED)
                        .setText("You do not have the appropriate permissions to execute this command. " +
                                 "Contact the server administrators if you believe that this is in error."));
                return;
            }
        }

        boolean allowedType = false;
        for (CommandSourceType cst : allowedSourceTypes) {
            if (cst.isTypeOf(source)) {
                allowedType = true;
                break;
            }
        }
        if (!allowedType) {
            source.sendMessage(ChatComponent.create().setColor(ChatColor.RED).setText(source.getCmdType() + "s cannot execute this command"));
            return;
        }

        boolean tooFewArguments = args.length < this.minimumParameters;
        boolean tooManyArguments = args.length > this.maximumParameters;
        if (tooFewArguments || tooManyArguments) {
            ChatComponent cc = ChatComponent.create().setColor(ChatColor.RED).setBold(true);
            cc.setText(tooFewArguments ? "Too few arguments! " : "Too many arguments! ");
            source.sendMessage(cc.addExtra(ChatComponent.create().setColor(ChatColor.RED).setBold(false).setText(this.getUsage())));
            return;
        }

        int variableArgsCount = args.length - parameters.length + 1;

        String[] fullArgs = new String[args.length + 1];
        fullArgs[0] = cmd;
        System.arraycopy(args, 0, fullArgs, 1, args.length);

        Object[] invokeArguments = new Object[2 + this.parameters.length];
        invokeArguments[0] = source;
        invokeArguments[1] = fullArgs;

        List<Object> variableArgs = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            int paramIndex = i;
            if (varargsIndex >= 0 && i >= varargsIndex) {
                paramIndex = i >= varargsIndex + variableArgsCount ? i - variableArgsCount + 1 : varargsIndex;
            }
            Parameter param = this.parameters[paramIndex];
            Class<?> paramType = param.getType().isArray() ? param.getType().getComponentType() : param.getType();
            Object result;
            try {
                result = Transformers.transform(args[i], param, paramType);
            } catch (UnsupportedOperationException ex) {
                source.sendMessage(ChatComponent.create().setColor(ChatColor.RED).setText("Failed to parse \"" + args[i] + "\" as a " + paramType.getSimpleName() + " (no transformer!)"));
                return;
            } catch (TransformationException ex) {
                source.sendMessage(ChatComponent.create().setColor(ChatColor.RED).setText(ex.getMessage()));
                return;
            } catch (Exception ex) {
                source.sendMessage(ChatComponent.create().setColor(ChatColor.RED).setText("\"" + args[i] + "\" is not a valid input, a " + paramType.getSimpleName() + " is wanted"));
                return;
            }
            if (paramIndex == varargsIndex) {
                if (result != null) {
                    variableArgs.add(result);
                }
            } else {
                invokeArguments[2 + paramIndex] = result;
            }
        }

        if (varargsIndex >= 0) {
            tooFewArguments = variableArgs.size() < this.minimumVariableArgs;
            tooManyArguments = variableArgs.size() > this.maximumVariableArgs;
            if (tooFewArguments || tooManyArguments) {
                ChatComponent cc = ChatComponent.create().setColor(ChatColor.RED).setBold(true);
                cc.setText(tooFewArguments ? "Too few arguments! " : "Too many arguments! ");
                source.sendMessage(cc.addExtra(ChatComponent.create().setColor(ChatColor.RED).setBold(false).setText(this.getUsage())));
                return;
            }
            Object arr = Array.newInstance(parameters[varargsIndex].getType().getComponentType(), variableArgs.size());
            for (int i = 0, j = variableArgs.size(); i < j; i++)
                Array.set(arr, i, variableArgs.get(i));
            invokeArguments[2 + varargsIndex] = arr;
        }
        this.access.invoke(this.container, this.methodIndex, invokeArguments);
    }

    @Override
    public boolean isContainedBy(Class<? extends CommandListener> cls) {
        return this.container.getClass().equals(cls);
    }

}
