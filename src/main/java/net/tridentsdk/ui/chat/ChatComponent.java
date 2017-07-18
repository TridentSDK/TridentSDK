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
package net.tridentsdk.ui.chat;

import lombok.*;
import lombok.experimental.Accessors;
import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.JsonValue;
import org.hjson.Stringify;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a component in a Minecraft chat format.
 *
 * <p>This class is <strong>NOT</strong> thread-safe!</p>
 *
 * @author TridentSDK
 * @since 0.5-alpha
 */
@NotThreadSafe
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatComponent {
    /**
     * The text that this chat component represents
     */
    @Getter @Setter
    private String text;
    /**
     * The translate modifier for the chat message
     */
    @Getter @Setter
    private String translate;
    /**
     * The chat selector
     */
    @Getter @Setter
    private String selector;
    /**
     * The insertion modifier for the chat message
     */
    @Getter @Setter
    private String insertion;
    /**
     * The scoreboard username
     */
    @Getter @Setter
    private String scoreUsername;
    /**
     * The scoreboard objective
     */
    @Getter @Setter
    private String scoreObjective;

    /**
     * The color of the chat message
     */
    @Getter @Setter
    private ChatColor color;
    /**
     * A click event that this message may contain
     */
    @Getter @Setter
    private ClickEvent clickEvent;
    /**
     * A hover event that this message may contain
     */
    @Getter @Setter
    private HoverEvent hoverEvent;

    /**
     * The list of chat components added to the 'with' array.
     */
    private final Collection<ChatComponent> with = new ArrayList<>();

    /**
     * The list of chat components added to the 'extra' array.
     */
    private final Collection<ChatComponent> extra = new ArrayList<>();

    /**
     * Whether or not this message is bolded
     */
    private Boolean bold;

    /**
     * Whether or not this message is italicized
     */
    private Boolean italic;

    /**
     * Whether or not this message is underlined
     */
    private Boolean underlined;

    /**
     * Whether or not this message is crossed out
     */
    private Boolean strikethrough;

    /**
     * Whether or not this message is obfuscated
     */
    private Boolean obfuscated;

    /**
     * Gets all elements attached to the 'with' array.
     *
     * The 'with' array is used in conjunction with the 'translate' component.
     *
     * @return The with elements.
     */
    public Collection<ChatComponent> getWith() {
        return this.with;
    }

    /**
     * Adds a component to the 'with' array.
     *
     * @param component The component.
     * @return This object.
     */
    public ChatComponent addWith(ChatComponent component) {
        if (!this.hasWith(component, true)) {
            this.with.add(component);
        }
        return this;
    }

    /**
     * Adds a string to the 'with' array.
     *
     * @param string The string.
     * @return This object.
     */
    public ChatComponent addWith(String string) {
        return this.addWith(new StringChatComponent(string));
    }

    /**
     * Checks if this component contains the given
     * component in the 'with' array, optionally recursively.
     *
     * @param component The component
     * @param recursive Whether to check children as well.
     * @return True iff the given component exists in this
     * component's hierarchy.
     */
    public boolean hasWith(ChatComponent component, boolean recursive) {
        if (this.with.contains(component)) {
            return true;
        } else if (!recursive) {
            return false;
        }

        for (ChatComponent child : this.with) {
            if (child.hasWith(component, true)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all components attached to the 'extra' array.
     *
     * <p>Components in this array are appended to this
     * component.</p>
     *
     * @return The extra components.
     */
    public Collection<ChatComponent> getExtra() {
        return this.extra;
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
        this.extra.add(new StringChatComponent(string));
        return this;
    }

    /**
     * Checks if this component contains the given
     * component in the 'extra' array, optionally recursively.
     *
     * @param component The component
     * @param recursive Whether to check children as well.
     * @return True iff the given component exists in this
     * component's hierarchy.
     */
    public boolean hasExtra(ChatComponent component, boolean recursive) {
        Collection<ChatComponent> extra = this.extra;
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
        Boolean flag = this.bold;
        return flag != null && flag;
    }

    /**
     * Sets this component's boldness.
     *
     * @param bold The boldness.
     * @return This component.
     */
    public ChatComponent setBold(boolean bold) {
        this.bold = bold;
        return this;
    }

    /**
     * Gets whether or not this component is in italics.
     *
     * @return True iff it is.
     */
    public boolean isItalic() {
        Boolean flag = this.italic;
        return flag != null && flag;
    }

    /**
     * Sets this component's italic.
     *
     * @param italic The italic.
     * @return This component.
     */
    public ChatComponent setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }

    /**
     * Gets whether or not this component is underlined.
     *
     * @return True iff it is.
     */
    public boolean isUnderlined() {
        Boolean flag = this.underlined;
        return flag != null && flag;
    }

    /**
     * Sets this component's underlined.
     *
     * @param underlined The underline.
     * @return This component.
     */
    public ChatComponent setUnderlined(boolean underlined) {
        this.underlined = underlined;
        return this;
    }

    /**
     * Gets whether or not this component is striked through.
     *
     * @return True iff it is.
     */
    public boolean isStrikethrough() {
        Boolean flag = this.strikethrough;
        return flag != null && flag;
    }

    /**
     * Sets this component's strikethrough.
     *
     * @param strikethrough The strikethrough.
     * @return This component.
     */
    public ChatComponent setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }

    /**
     * Gets whether or not this component is in obfuscation.
     *
     * @return True iff it is.
     */
    public boolean isObfuscated() {
        Boolean flag = this.obfuscated;
        return flag != null && flag;
    }

    /**
     * Sets this component's obfuscation.
     *
     * @param obfuscated The new obfuscated state.
     * @return This component.
     */
    public ChatComponent setObfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
        return this;
    }

    /**
     * Parses this chat component without adding
     * formatting codes.
     *
     * @return colorless and text formatting-free json
     */
    public JsonValue stripColor() {
        JsonObject json = new JsonObject();

        String text = this.text;
        if (text != null) {
            json.add("text", text);
        }

        String translate = this.translate;
        if (translate != null) {
            json.add("translate", translate);
            JsonArray array = new JsonArray();
            this.with.forEach(e -> array.add(e.stripColor()));
            json.add("with", array);
        }

        String scoreUsername = this.scoreUsername;
        String scoreObjective = this.scoreObjective;
        if (scoreUsername != null && scoreObjective != null) {
            JsonObject score = new JsonObject();
            score.add("name", scoreUsername);
            score.add("objective", scoreObjective);
            json.add("score", score);
        }

        String selector = this.selector;
        if (selector != null) {
            json.add("selector", selector);
        }

        Collection<ChatComponent> extra = this.extra;
        if (!extra.isEmpty()) {
            JsonArray extraArray = new JsonArray();
            extra.forEach(e -> extraArray.add(e.stripColor()));
            json.add("extra", extraArray);
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
            json.add("insertion", insertion);
        }
        return json;
    }

    /**
     * Gets this component as a JSON element, ready to be sent to a client.
     *
     * @return The JSON element.
     */
    public JsonValue asJson() {
        JsonObject json = new JsonObject();

        String text = this.text;
        if (text != null) {
            json.add("text", text);
        }

        String translate = this.translate;
        if (translate != null) {
            json.add("translate", translate);
            JsonArray array = new JsonArray();
            this.with.forEach(e -> array.add(e.asJson()));
            json.add("with", array);
        }

        String scoreUsername = this.scoreUsername;
        String scoreObjective = this.scoreObjective;
        if (scoreUsername != null && scoreObjective != null) {
            JsonObject score = new JsonObject();
            score.add("name", scoreUsername);
            score.add("objective", scoreObjective);
            json.add("score", score);
        }

        String selector = this.selector;
        if (selector != null) {
            json.add("selector", selector);
        }

        Collection<ChatComponent> extra = this.extra;
        if (!extra.isEmpty()) {
            JsonArray extraArray = new JsonArray();
            extra.forEach(e -> extraArray.add(e.asJson()));
            json.add("extra", extraArray);
        }

        Boolean isBold = this.bold;
        if (isBold != null) {
            json.add("bold", isBold);
        }

        Boolean isItalic = this.italic;
        if (isItalic != null) {
            json.add("italic", isItalic);
        }

        Boolean isUnderlined = this.underlined;
        if (isUnderlined != null) {
            json.add("underlined", isUnderlined);
        }

        Boolean isStrikethrough = this.strikethrough;
        if (isStrikethrough != null) {
            json.add("strikethrough", isStrikethrough);
        }

        Boolean isObfuscated = this.obfuscated;
        if (isObfuscated != null) {
            json.add("obfuscated", isObfuscated);
        }

        ChatColor color = this.color;
        if (color != null && !color.isFormat()) {
            json.add("color", color.name().toLowerCase());
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
            json.add("insertion", insertion);
        }
        return json;
    }

    /**
     * Gets this component as a JSON string.
     *
     * @return The JSON string.
     */
    public String toString() {
        return this.asJson().toString(Stringify.PLAIN);
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
     * Creates a component based on a given JSON object.
     *
     * @param json The JSON.
     * @return The component.
     */
    public static ChatComponent fromJson(JsonObject json) {
        ChatComponent cc = create();
        JsonValue text = json.get("text");
        if (text != null) {
            cc.setText(text.asString());
        }
        JsonValue translate = json.get("translate");
        if (translate != null) {
            cc.setTranslate(translate.asString());
        }
        JsonValue with = json.get("with");
        if (with != null) {
            JsonArray array = with.asArray();
            for (int i = 0, j = array.size(); i < j; i++) {
                JsonValue el = array.get(i);
                if (el.isString() || el.isNumber() || el.isBoolean())
                    cc.addWith(el.asString());
                else if (el.isObject())
                    cc.addWith(fromJson(el.asObject()));
            }
        }
        JsonValue score = json.get("score");
        if (score != null) {
            JsonObject scoreArray = score.asObject();
            cc.setScoreUsername(scoreArray.get("name").asString());
            cc.setScoreObjective(scoreArray.get("objective").asString());
        }
        JsonValue selector = json.get("selector");
        if (selector != null) {
            cc.setSelector(selector.asString());
        }
        JsonValue extra = json.get("extra");
        if (extra != null) {
            JsonArray array = extra.asArray();
            for (int i = 0, j = array.size(); i < j; i++) {
                JsonValue el = array.get(i);
                if (el.isString() || el.isNumber() || el.isBoolean())
                    cc.addExtra(el.asString());
                else if (el.isString() || el.isNumber() || el.isBoolean())
                    cc.addExtra(fromJson(el.asObject()));
            }
        }
        JsonValue bold = json.get("bold");
        if (bold != null) {
            cc.setBold(bold.asBoolean());
        }
        JsonValue italic = json.get("italic");
        if (italic != null) {
            cc.setItalic(italic.asBoolean());
        }
        JsonValue underlined = json.get("underlined");
        if (underlined != null) {
            cc.setUnderlined(underlined.asBoolean());
        }
        JsonValue strikethrough = json.get("strikethrough");
        if (strikethrough != null) {
            cc.setStrikethrough(strikethrough.asBoolean());
        }
        JsonValue obfuscated = json.get("obfuscated");
        if (obfuscated != null) {
            cc.setObfuscated(obfuscated.asBoolean());
        }
        JsonValue color = json.get("color");
        if (color != null) {
            cc.setColor(ChatColor.valueOf(color.asString().toUpperCase()));
        }
        JsonValue clickEvent = json.get("clickEvent");
        if (clickEvent != null) {
            cc.setClickEvent(ClickEvent.fromJson(clickEvent.asObject()));
        }
        JsonValue hoverEvent = json.get("hoverEvent");
        if (hoverEvent != null) {
            cc.setHoverEvent(HoverEvent.fromJson(hoverEvent.asObject()));
        }
        JsonValue insertion = json.get("insertion");
        if (insertion != null) {
            cc.setInsertion(insertion.asString());
        }
        return cc;
    }

    /**
     * Builds a component from a format string containing color codes.
     *
     * @param format The format string.
     * @return The component.
     */
    public static ChatComponent fromFormat(String format) {
        char[] chars = format.toCharArray();
        String currentText = "";
        ChatColor currentColor = null;
        ChatComponent component = ChatComponent.create(), currentComponent = null;
        boolean bold, italic, underline, strikethrough, obfuscate;
        bold = italic = underline = strikethrough = obfuscate = false;
        for (int i = 0, j = chars.length; i < j; i++) {
            boolean prevSection = i != 0 && chars[i - 1] == '\u00A7';
            char c = chars[i];
            if (prevSection) {
                ChatColor color = ChatColor.of(c);
                if (color != null) {
                    ChatComponent curr = currentComponent == null ? component : currentComponent;
                    // splice off current component
                    if (!currentText.isEmpty()) {
                        curr.setText(currentText).setColor(currentColor);
                        if (currentComponent != null)
                            component.addExtra(currentComponent);
                        curr = currentComponent = create();
                        currentText = "";
                    }
                    if (color.isColor()) {
                        currentColor = color;
                        // disable all formatting
                        if (bold)
                            curr.setBold(bold = false);
                        if (italic)
                            curr.setItalic(italic = false);
                        if (underline)
                            curr.setUnderlined(underline = false);
                        if (strikethrough)
                            curr.setStrikethrough(strikethrough = false);
                        if (obfuscate)
                            curr.setObfuscated(obfuscate = false);
                    } else {
                        // formatting code
                        switch (color) {
                            case BOLD:
                                if (!bold) {
                                    curr.setBold(bold = true);
                                }
                                break;
                            case ITALIC:
                                if (!italic) {
                                    curr.setItalic(italic = true);
                                }
                                break;
                            case UNDERLINE:
                                if (!underline) {
                                    curr.setUnderlined(underline = true);
                                }
                                break;
                            case STRIKETHROUGH:
                                if (!strikethrough) {
                                    curr.setStrikethrough(strikethrough = true);
                                }
                                break;
                            case OBFUSCATED:
                                if (!obfuscate) {
                                    curr.setObfuscated(obfuscate = true);
                                }
                                break;
                            case RESET:
                                // remove current color
                                currentColor = null;
                                // disable all formatting
                                if (bold)
                                    curr.setBold(bold = false);
                                if (italic)
                                    curr.setItalic(italic = false);
                                if (underline)
                                    curr.setUnderlined(underline = false);
                                if (strikethrough)
                                    curr.setStrikethrough(strikethrough = false);
                                if (obfuscate)
                                    curr.setObfuscated(obfuscate = false);
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

    public boolean equals(Object o) {
        if (o == null || o == this || o.getClass() != this.getClass()) {
            return o == this;
        }
        ChatComponent cc = (ChatComponent) o;
        return this.asJson().equals(cc.asJson());
    }

    @Getter
    @AllArgsConstructor
    private final class StringChatComponent extends ChatComponent {
        private String string;

        @Override
        public JsonValue stripColor() {
            return this.asJson();
        }

        @Override
        public JsonValue asJson() {
            return JsonValue.valueOf(this.string);
        }
    }
}
