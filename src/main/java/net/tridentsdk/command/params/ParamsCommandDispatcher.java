package net.tridentsdk.command.params;

import com.esotericsoftware.reflectasm.MethodAccess;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.regex.Pattern;
import lombok.Getter;
import net.tridentsdk.Server;
import net.tridentsdk.command.Command;
import net.tridentsdk.command.CommandDispatcher;
import net.tridentsdk.command.CommandHandler;
import net.tridentsdk.command.CommandListener;
import net.tridentsdk.command.CommandSource;
import net.tridentsdk.command.CommandSourceType;
import net.tridentsdk.entity.living.Player;
import net.tridentsdk.logger.Logger;
import net.tridentsdk.ui.chat.ChatColor;
import net.tridentsdk.ui.chat.ChatComponent;

/**
 * @author Nick Robson
 */
public class ParamsCommandDispatcher extends CommandDispatcher {

    private static final Map<Class<?>, BiFunction<String, Parameter, ?>> transformers = new ConcurrentHashMap<>();
    private static final Map<Class<?>, BiFunction<String, Parameter, ?>> inheritedTransformers = new ConcurrentHashMap<>();

    static {
        registerTransformer(Byte.class, (s, p) -> {
            try {
                return Byte.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter an integer in -128 to 127!");
            }
        });
        registerTransformer(Short.class, (s, p) -> {
            try {
                return Short.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter an integer in -65536 to 65535!");
            }
        });
        registerTransformer(Integer.class, (s, p) -> {
            try {
                return Integer.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter an integer!");
            }
        });
        registerTransformer(Long.class, (s, p) -> {
            try {
                return Long.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter an integer!");
            }
        });
        registerTransformer(Float.class, (s, p) -> {
            try {
                return Float.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter a number!");
            }
        });
        registerTransformer(Double.class, (s, p) -> {
            try {
                return Double.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter a number!");
            }
        });
        registerTransformer(Number.class, (s, p) -> {
            try {
                return Double.valueOf(s);
            } catch (Exception ex) {
                throw new TransformationException("Invalid input! Enter a number!");
            }
        });
        registerTransformer(Boolean.class, (s, p) -> s.length() > 0 && (s.charAt(0) == 'y' || s.charAt(0) == 't'));
        registerTransformer(String.class, (s, p) -> s);
        registerTransformer(Object.class, (s, p) -> s);
        registerTransformer(Player.class, (s, p) -> {
            ParamsAnnotations.PlayerExactMatch pem = p != null ? p.getAnnotation(ParamsAnnotations.PlayerExactMatch.class) : null;
            ParamsAnnotations.PlayerFuzzyMatch pfm = p != null ? p.getAnnotation(ParamsAnnotations.PlayerFuzzyMatch.class) : null;
            ParamsAnnotations.PlayerRegexMatch prm = p != null ? p.getAnnotation(ParamsAnnotations.PlayerRegexMatch.class) : null;
            if (pem != null) {
                return Server.getInstance().getPlayerExact(s);
            } else if (pfm != null) {
                return Server.getInstance().getPlayersFuzzyMatching(s).stream().findAny().orElse(null);
            } else if (prm != null) {
                Pattern pattern;
                try {
                    pattern = Pattern.compile(s);
                } catch (Exception ex) {
                    throw new TransformationException("Invalid regex pattern provided");
                }
                return Server.getInstance().getPlayers().stream()
                        .filter(x -> pattern.matcher(x.getName()).find())
                        .findAny()
                        .orElse(null);
            } else {
                return Server.getInstance().getPlayersMatching(s).stream().findAny().orElse(null);
            }
        });
    }

    public static <T> void registerTransformer(Class<T> clazz, BiFunction<String, Parameter, ?> transformer) {
        Objects.requireNonNull(clazz, "class cannot be null");
        Objects.requireNonNull(transformer, "transformer for " + clazz + " cannot be null");
        if (transformers.putIfAbsent(clazz, transformer) != null)
            return;
        Class<?> cls = clazz.getSuperclass();
        while (cls != null && cls != Object.class) {
            if (inheritedTransformers.putIfAbsent(cls, transformer) != null)
                break;
            cls = cls.getSuperclass();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T transform(String input, Parameter parameter, Class<T> clazz) throws Exception {
        Objects.requireNonNull(input, "input cannot be null");
        Objects.requireNonNull(parameter, "parameter cannot be null");
        BiFunction<String, Parameter, ?> transformer = transformers.get(clazz);
        if (transformer == null)
            transformer = inheritedTransformers.get(clazz);
        if (transformer == null)
            throw new UnsupportedOperationException("transformer missing for " + clazz);
        return (T) transformer.apply(input, parameter);
    }

    private final MethodAccess access;
    private final CommandListener container;
    private final Method method;
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
        this.method = method;
        this.methodIndex = access.getIndex(this.method.getName(), this.method.getParameterTypes());
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
                ParamsAnnotations.MinCount a0 = param.getAnnotation(ParamsAnnotations.MinCount.class);
                if (a0 != null) {
                    if ((minVar = a0.value()) < 0)
                        throw new IllegalArgumentException("minimum cannot be less than 0");
                    min += minVar - 1;
                } else {
                    min -= 1;
                }
                ParamsAnnotations.MaxCount a1 = param.getAnnotation(ParamsAnnotations.MaxCount.class);
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

        ParamsAnnotations.PermissionRequired permissionRequired = method.getAnnotation(ParamsAnnotations.PermissionRequired.class);
        if (permissionRequired != null && permissionRequired.value().length > 0) {
            this.permissionRequired = permissionRequired.value();
        } else {
            this.permissionRequired = null;
        }

        ParamsAnnotations.AllowedSourceTypes sources = method.getAnnotation(ParamsAnnotations.AllowedSourceTypes.class);
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
            source.sendMessage(cc.addExtra(ChatComponent.create().setColor(ChatColor.RED).setBold(false).setText(getUsage())));
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
                result = transform(args[i], param, paramType);
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
                source.sendMessage(cc.addExtra(ChatComponent.create().setColor(ChatColor.RED).setBold(false).setText(getUsage())));
                return;
            }
            Object arr = Array.newInstance(parameters[varargsIndex].getType().getComponentType(), variableArgs.size());
            for (int i = 0, j = variableArgs.size(); i < j; i++)
                Array.set(arr, i, variableArgs.get(i));
            invokeArguments[2 + varargsIndex] = arr;
        }

//      (String cmd, CommandSource src, int a, int b, int[] c, int d)
//                               args.length = 7, parameters.length = 4
//                                      20     20     1 2 3 4  20
//                         args idx     0      1      2 3 4 5  6
//                       params idx     0      1      2 2 2 2  3

        this.access.invoke(this.container, this.methodIndex, invokeArguments);
    }

    @Override
    public boolean isContainedBy(Class<? extends CommandListener> cls) {
        return this.container.getClass().equals(cls);
    }

}
