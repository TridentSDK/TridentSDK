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

import java.util.ArrayList;
import java.util.Objects;
import lombok.*;
import lombok.experimental.Accessors;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;

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
    public Object stripColor() {
        JSONObject json = new JSONObject();

        String text = this.text;
        if (text != null) {
            json.put("text", text);
        }

        String translate = this.translate;
        if (translate != null) {
            json.put("translate", translate);
            JSONArray array = new JSONArray();
            this.with.forEach(e -> array.put(e.stripColor()));
            json.put("with", array);
        }

        String scoreUsername = this.scoreUsername;
        String scoreObjective = this.scoreObjective;
        if (scoreUsername != null && scoreObjective != null) {
            JSONObject score = new JSONObject();
            score.put("name", scoreUsername);
            score.put("objective", scoreObjective);
            json.put("score", score);
        }

        String selector = this.selector;
        if (selector != null) {
            json.put("selector", selector);
        }

        Collection<ChatComponent> extra = this.extra;
        if (!extra.isEmpty()) {
            JSONArray extraArray = new JSONArray();
            extra.forEach(e -> extraArray.put(e.stripColor()));
            json.put("extra", extraArray);
        }

        ClickEvent clickEvent = this.clickEvent;
        if (clickEvent != null) {
            json.put("clickEvent", clickEvent.asJson());
        }

        HoverEvent hoverEvent = this.hoverEvent;
        if (hoverEvent != null) {
            json.put("hoverEvent", hoverEvent.asJson());
        }

        String insertion = this.insertion;
        if (insertion != null) {
            json.put("insertion", insertion);
        }
        return json;
    }

    /**
     * Gets this component as a JSON element, ready to be sent to a client.
     *
     * @return The JSON element.
     */
    public Object asJson() {
        JSONObject json = new JSONObject();

        String text = this.text;
        if (text != null) {
            json.put("text", text);
        }

        String translate = this.translate;
        if (translate != null) {
            json.put("translate", translate);
            JSONArray array = new JSONArray();
            this.with.forEach(e -> array.put(e.asJson()));
            json.put("with", array);
        }

        String scoreUsername = this.scoreUsername;
        String scoreObjective = this.scoreObjective;
        if (scoreUsername != null && scoreObjective != null) {
            JSONObject score = new JSONObject();
            score.put("name", scoreUsername);
            score.put("objective", scoreObjective);
            json.put("score", score);
        }

        String selector = this.selector;
        if (selector != null) {
            json.put("selector", selector);
        }

        Collection<ChatComponent> extra = this.extra;
        if (!extra.isEmpty()) {
            JSONArray extraArray = new JSONArray();
            extra.forEach(e -> extraArray.put(e.asJson()));
            json.put("extra", extraArray);
        }

        Boolean isBold = this.bold;
        if (isBold != null) {
            json.put("bold", isBold);
        }

        Boolean isItalic = this.italic;
        if (isItalic != null) {
            json.put("italic", isItalic);
        }

        Boolean isUnderlined = this.underlined;
        if (isUnderlined != null) {
            json.put("underlined", isUnderlined);
        }

        Boolean isStrikethrough = this.strikethrough;
        if (isStrikethrough != null) {
            json.put("strikethrough", isStrikethrough);
        }

        Boolean isObfuscated = this.obfuscated;
        if (isObfuscated != null) {
            json.put("obfuscated", isObfuscated);
        }

        ChatColor color = this.color;
        if (color != null && !color.isFormat()) {
            json.put("color", color.name().toLowerCase());
        }

        ClickEvent clickEvent = this.clickEvent;
        if (clickEvent != null) {
            json.put("clickEvent", clickEvent.asJson());
        }

        HoverEvent hoverEvent = this.hoverEvent;
        if (hoverEvent != null) {
            json.put("hoverEvent", hoverEvent.asJson());
        }

        String insertion = this.insertion;
        if (insertion != null) {
            json.put("insertion", insertion);
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
     * Creates a component based on a given JSON object.
     *
     * @param json The JSON.
     * @return The component.
     */
    public static ChatComponent fromJson(JSONObject json) {
        ChatComponent cc = create();
        if (json.has("text")) {
            cc.setText(json.getString("text"));
        }
        if (json.has("translate")) {
            cc.setTranslate(json.getString("translate"));
        }
        if (json.has("with")) {
            JSONArray array = json.getJSONArray("with");
            for (int i = 0, j = array.length(); i < j; i++) {
                Object el = array.get(i);
                if (el instanceof JSONObject) {
                    cc.addWith(fromJson((JSONObject) el));
                } else {
                    cc.addWith(el.toString());
                }
            }
        }
        if (json.has("score")) {
            JSONObject score = json.getJSONObject("score");
            cc.setScoreUsername(score.getString("name"));
            cc.setScoreObjective(score.getString("objective"));
        }
        if (json.has("selector")) {
            cc.setSelector(json.getString("selector"));
        }
        if (json.has("extra")) {
            JSONArray array = json.getJSONArray("extra");
            for (int i = 0, j = array.length(); i < j; i++) {
                Object el = array.get(i);
                if (el instanceof JSONObject)
                    cc.addExtra(fromJson((JSONObject) el));
                else
                    cc.addExtra(el.toString());
            }
        }
        if (json.has("bold")) {
            cc.setBold(json.getBoolean("bold"));
        }
        if (json.has("italic")) {
            cc.setItalic(json.getBoolean("italic"));
        }
        if (json.has("underlined")) {
            cc.setUnderlined(json.getBoolean("underlined"));
        }
        if (json.has("strikethrough")) {
            cc.setStrikethrough(json.getBoolean("strikethrough"));
        }
        if (json.has("obfuscated")) {
            cc.setObfuscated(json.getBoolean("obfuscated"));
        }
        if (json.has("color")) {
            cc.setColor(ChatColor.valueOf(json.getString("color").toUpperCase()));
        }
        if (json.has("clickEvent")) {
            cc.setClickEvent(ClickEvent.fromJson(json.getJSONObject("clickEvent")));
        }
        if (json.has("hoverEvent")) {
            cc.setHoverEvent(HoverEvent.fromJson(json.getJSONObject("hoverEvent")));
        }
        if (json.has("insertion")) {
            cc.setInsertion(json.getString("insertion"));
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
                            case BOLD: {
                                if (!bold) {
                                    curr.setBold(bold = true);
                                }
                                break;
                            }
                            case ITALIC: {
                                if (!italic) {
                                    curr.setItalic(italic = true);
                                }
                                break;
                            }
                            case UNDERLINE: {
                                if (!underline) {
                                    curr.setUnderlined(underline = true);
                                }
                                break;
                            }
                            case STRIKETHROUGH: {
                                if (!strikethrough) {
                                    curr.setStrikethrough(strikethrough = true);
                                }
                                break;
                            }
                            case OBFUSCATED: {
                                if (!obfuscate) {
                                    curr.setObfuscated(obfuscate = true);
                                }
                                break;
                            }
                            case RESET: {
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
        Object a = this.asJson();
        Object b = cc.asJson();
        if (a instanceof JSONObject && b instanceof JSONObject) {
            return ((JSONObject) a).toMap().equals(((JSONObject) b).toMap());
        }
        return Objects.equals(a, b);
    }

    @Getter
    @AllArgsConstructor
    private final class StringChatComponent extends ChatComponent {
        private String string;

        @Override
        public Object stripColor() {
            return this.asJson();
        }

        @Override
        public Object asJson() {
            return this.string;
        }
    }
}
