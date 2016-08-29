/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2016 The TridentSDK Team
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
package net.tridentsdk.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.*;
import lombok.experimental.Accessors;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Represents a component in a Minecraft chat format.
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@ThreadSafe
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatComponent {
    /**
     * The text that this chat component represents
     */
    @Getter @Setter
    private volatile String text;
    /**
     * The translate modifier for the chat message
     */
    @Getter
    @Setter
    private volatile String translate;
    /**
     * The chat selector
     */
    @Getter
    @Setter
    private volatile String selector;
    /**
     * The insertion modifier for the chat message
     */
    @Getter
    @Setter
    private volatile String insertion;
    /**
     * The scoreboard username
     */
    @Getter
    @Setter
    private volatile String scoreUsername;
    /**
     * The scoreboard objective
     */
    @Getter
    @Setter
    private volatile String scoreObjective;

    /**
     * The color of the chat message
     */
    @Getter @Setter
    private volatile ChatColor color;
    /**
     * A click event that this message may contain
     */
    @Getter @Setter
    private volatile ClickEvent clickEvent;
    /**
     * A hover event that this message may contain
     */
    @Getter @Setter
    private volatile HoverEvent hoverEvent;

    /**
     * The list of other JSON formatted components
     */
    private final List<JsonElement> with = Collections.synchronizedList(new ArrayList<>());
    /**
     * The list of other extra components added to this
     * message
     */
    private final List<ChatComponent> extra = Collections.synchronizedList(new ArrayList<>());

    /**
     * Whether or not this message is bolded
     */
    private final AtomicReference<Boolean> bold = new AtomicReference<>();
    /**
     * Whether or not this message is italicized
     */
    private final AtomicReference<Boolean> italic = new AtomicReference<>();
    /**
     * Whether or not this message is underlined
     */
    private final AtomicReference<Boolean> underlined = new AtomicReference<>();
    /**
     * Whether or not this message is crossed out
     */
    private final AtomicReference<Boolean> strikethrough = new AtomicReference<>();
    /**
     * Whether or not this message is obfuscated
     */
    private final AtomicReference<Boolean> obfuscated = new AtomicReference<>();

    /**
     * Gets all elements attached to the 'with' array.
     *
     * The 'with' array is used in conjunction with the 'translate' component.
     *
     * @return The with elements.
     */
    public List<JsonElement> getWith() {
        return Collections.unmodifiableList(this.with);
    }

    /**
     * Adds an element to the 'with' array.
     *
     * @param element The JSON element.
     * @return This object.
     */
    public ChatComponent addWith(JsonElement element) {
        this.with.add(element);
        return this;
    }

    /**
     * Adds an string to the 'with' array.
     *
     * @param element The string.
     * @return This object.
     */
    public ChatComponent addWith(String element) {
        return this.addWith(new JsonPrimitive(element));
    }

    /**
     * Gets all components attached to the 'extra' array.
     *
     * <p>Components in this array are appended to this
     * component.</p>
     *
     * @return The extra components.
     */
    public List<ChatComponent> getExtra() {
        return Collections.unmodifiableList(this.extra);
    }

    /**
     * Adds a component to the 'extra' array.
     *
     * @param component The component.
     * @return This component.
     */
    public ChatComponent addExtra(ChatComponent component) {
        if (!this.hasExtra(component, true)) {
            this.extra.add(component);
        }
        return this;
    }

    /**
     * Adds a string to the 'extra' array.
     *
     * @param string The string.
     * @return This component.
     */
    public ChatComponent addExtra(String string) {
        this.extra.add(ChatComponent.create().setText(string));
        return this;
    }

    /**
     * Checks if this component contains the given
     * component, optionally recursively.
     *
     * @param component The component
     * @param recursive Whether to check children as well.
     * @return True iff the given component exists in this
     * component's hierarchy.
     */
    public boolean hasExtra(ChatComponent component, boolean recursive) {
        List<ChatComponent> extra = this.extra;
        if (extra.contains(component)) {
            return true;
        } else if (!recursive) {
            return false;
        }

        for (ChatComponent child : extra) {
            if (child.hasExtra(component, true)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets whether or not this component is bold.
     *
     * @return True iff it is.
     */
    public boolean isBold() {
        Boolean flag = this.bold.get();
        return flag != null && flag;
    }

    /**
     * Sets this component's boldness.
     *
     * @param bold The boldness.
     * @return This component.
     */
    public ChatComponent setBold(boolean bold) {
        this.bold.set(bold);
        return this;
    }

    /**
     * Gets whether or not this component is in italics.
     *
     * @return True iff it is.
     */
    public boolean isItalic() {
        Boolean flag = this.italic.get();
        return flag != null && flag;
    }

    /**
     * Sets this component's italic.
     *
     * @param italic The italic.
     * @return This component.
     */
    public ChatComponent setItalic(boolean italic) {
        this.italic.set(italic);
        return this;
    }

    /**
     * Gets whether or not this component is underlined.
     *
     * @return True iff it is.
     */
    public boolean isUnderlined() {
        Boolean flag = this.underlined.get();
        return flag != null && flag;
    }

    /**
     * Sets this component's underlined.
     *
     * @param underlined The underline.
     * @return This component.
     */
    public ChatComponent setUnderlined(boolean underlined) {
        this.underlined.set(underlined);
        return this;
    }

    /**
     * Gets whether or not this component is striked through.
     *
     * @return True iff it is.
     */
    public boolean isStrikethrough() {
        return this.strikethrough != null && this.strikethrough.get();
    }

    /**
     * Sets this component's strikethrough.
     *
     * @param strikethrough The strikethrough.
     * @return This component.
     */
    public ChatComponent setStrikethrough(boolean strikethrough) {
        this.strikethrough.set(strikethrough);
        return this;
    }

    /**
     * Gets whether or not this component is in obfuscation.
     *
     * @return True iff it is.
     */
    public boolean isObfuscated() {
        Boolean flag = this.obfuscated.get();
        return flag != null && flag;
    }

    /**
     * Sets this component's obfuscation.
     *
     * @param obfuscated The new obfuscated state.
     * @return This component.
     */
    public ChatComponent setObfuscated(boolean obfuscated) {
        this.obfuscated.set(obfuscated);
        return this;
    }

    /**
     * Gets this component as a JSON object, ready to be sent to a client.
     *
     * @return The JSON object.
     */
    public JsonObject asJson() {
        JsonObject json = new JsonObject();

        String text = this.text;
        if (text != null) {
            json.addProperty("text", text);
        }

        String translate = this.translate;
        if (translate != null) {
            json.addProperty("translate", translate);
            JsonArray array = new JsonArray();
            this.with.forEach(array::add);
            json.add("with", array);
        }

        String scoreUsername = this.scoreUsername;
        String scoreObjective = this.scoreObjective;
        if (scoreUsername != null && scoreObjective != null) {
            JsonObject score = new JsonObject();
            score.addProperty("name", scoreUsername);
            score.addProperty("objective", scoreObjective);
            json.add("score", score);
        }

        String selector = this.selector;
        if (selector != null) {
            json.addProperty("selector", selector);
        }

        List<ChatComponent> extra = this.extra;
        if (!extra.isEmpty()) {
            JsonArray extraArray = new JsonArray();
            extra.forEach(e -> extraArray.add(e.asJson()));
            json.add("extra", extraArray);
        }

        Boolean isBold = this.bold.get();
        if (isBold != null) {
            json.addProperty("bold", isBold);
        }

        Boolean isItalic = this.italic.get();
        if (isItalic != null) {
            json.addProperty("italic", isItalic);
        }

        Boolean isUnderlined = this.underlined.get();
        if (isUnderlined != null) {
            json.addProperty("underlined", isUnderlined);
        }

        Boolean isStrikethrough = this.strikethrough.get();
        if (isStrikethrough != null) {
            json.addProperty("strikethrough", isStrikethrough);
        }

        Boolean isObfuscated = this.obfuscated.get();
        if (isObfuscated != null) {
            json.addProperty("obfuscated", isObfuscated);
        }

        ChatColor color = this.color;
        if (this.color != null) {
            json.addProperty("color", color.name().toLowerCase());
        }

        ClickEvent clickEvent = this.clickEvent;
        if (clickEvent != null) {
            json.add("clickEvent", clickEvent.asJson());
        }

        HoverEvent hoverEvent = this.hoverEvent;
        if (hoverEvent != null) {
            json.add("hoverEvent", hoverEvent.asJson());
        }

        String insertion = this.insertion;
        if (insertion != null) {
            json.addProperty("insertion", insertion);
        }
        return json;
    }

    /**
     * Gets this component as a JSON string.
     *
     * @return The JSON string.
     */
    public String toString() {
        return this.asJson().toString();
    }

    /**
     * Creates a new empty component.
     *
     * @return The new component.
     */
    public static ChatComponent create() {
        return new ChatComponent();
    }

    /**
     * Creates a component with an empty string for text.
     *
     * @return The component.
     */
    public static ChatComponent empty() {
        return create().setText("");
    }

    /**
     * Creates a component with a given string for text.
     *
     * @param text The text.
     * @return The component.
     */
    public static ChatComponent text(String text) {
        return create().setText(text);
    }

    /**
     * Builds a component from a format string containing color codes.
     *
     * @param format The format string.
     * @return The component.
     */
    public static ChatComponent fromFormat(String format) {
        ChatComponent component = ChatComponent.create();
        char[] chars = format.toCharArray();
        String currentText = "";
        ChatColor currentColor = null;
        ChatComponent currentComponent = create();
        boolean sub = false;
        for (int i = 0, j = chars.length; i < j; i++) {
            boolean prevSection = i != 0 && chars[i - 1] == '\u00A7';
            char c = chars[i];
            if (prevSection) {
                ChatColor color = ChatColor.getColor(c);
                if (color != null) {
                    if (color.isColor()) {
                        if (!currentText.isEmpty()) {
                            if (sub) {
                                component.addExtra(currentComponent.setText(currentText).setColor(currentColor));
                            } else {
                                component.setText(currentText).setColor(currentColor);
                                sub = true;
                            }
                            currentComponent = create();
                        }
                        currentColor = color;
                        currentText = "";
                    } else {
                        ChatComponent curr = sub ? currentComponent : component;
                        switch (color) {
                            case OBFUSCATED:
                                curr.setObfuscated(true);
                                break;
                            case BOLD:
                                curr.setBold(true);
                                break;
                            case STRIKETHROUGH:
                                curr.setStrikethrough(true);
                                break;
                            case UNDERLINE:
                                curr.setUnderlined(true);
                                break;
                            case ITALIC:
                                curr.setItalic(true);
                                break;
                            case RESET:
                                ChatComponent next = create();
                                if (curr.isBold())
                                    next.setBold(false);
                                if (curr.isItalic())
                                    next.setItalic(false);
                                if (curr.isUnderlined())
                                    next.setUnderlined(false);
                                if (curr.isStrikethrough())
                                    next.setStrikethrough(false);
                                if (curr.isObfuscated())
                                    next.setObfuscated(false);
                                if (!currentText.isEmpty()) {
                                    if (sub) {
                                        component.addExtra(curr.setText(currentText).setColor(currentColor));
                                    } else {
                                        component.setText(currentText).setColor(currentColor);
                                        sub = true;
                                    }
                                }
                                currentText = "";
                                currentComponent = next;
                                currentColor = null;
                            default:
                                break;
                        }
                    }
                }
            } else if (c != '\u00A7') {
                currentText += c;
            }
        }
        if (!currentText.isEmpty()) {
            component.addExtra(currentComponent.setText(currentText).setColor(currentColor));
        }
        return component;
    }
}