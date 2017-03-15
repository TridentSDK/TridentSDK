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
package net.tridentsdk.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.*;
import lombok.experimental.Accessors;

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
    public JsonElement stripColor() {
        JsonObject json = new JsonObject();

        String text = this.text;
        if (text != null) {
            json.addProperty("text", text);
        }

        String translate = this.translate;
        if (translate != null) {
            json.addProperty("translate", translate);
            JsonArray array = new JsonArray();
            this.with.forEach(e -> array.add(e.stripColor()));
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
            json.addProperty("insertion", insertion);
        }
        return json;
    }

    /**
     * Gets this component as a JSON element, ready to be sent to a client.
     *
     * @return The JSON element.
     */
    public JsonElement asJson() {
        JsonObject json = new JsonObject();

        String text = this.text;
        if (text != null) {
            json.addProperty("text", text);
        }

        String translate = this.translate;
        if (translate != null) {
            json.addProperty("translate", translate);
            JsonArray array = new JsonArray();
            this.with.forEach(e -> array.add(e.asJson()));
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

        Collection<ChatComponent> extra = this.extra;
        if (!extra.isEmpty()) {
            JsonArray extraArray = new JsonArray();
            extra.forEach(e -> extraArray.add(e.asJson()));
            json.add("extra", extraArray);
        }

        Boolean isBold = this.bold;
        if (isBold != null) {
            json.addProperty("bold", isBold);
        }

        Boolean isItalic = this.italic;
        if (isItalic != null) {
            json.addProperty("italic", isItalic);
        }

        Boolean isUnderlined = this.underlined;
        if (isUnderlined != null) {
            json.addProperty("underlined", isUnderlined);
        }

        Boolean isStrikethrough = this.strikethrough;
        if (isStrikethrough != null) {
            json.addProperty("strikethrough", isStrikethrough);
        }

        Boolean isObfuscated = this.obfuscated;
        if (isObfuscated != null) {
            json.addProperty("obfuscated", isObfuscated);
        }

        ChatColor color = this.color;
        if (color != null && !color.isFormat()) {
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
     * Creates a component based on a given JSON object.
     *
     * @param json The JSON.
     * @return The component.
     */
    public static ChatComponent fromJson(JsonObject json) {
        ChatComponent cc = create();
        if (json.has("text")) {
            cc.setText(json.get("text").getAsString());
        }
        if (json.has("translate")) {
            cc.setTranslate(json.get("translate").getAsString());
        }
        if (json.has("with")) {
            JsonArray array = json.getAsJsonArray("with");
            for (int i = 0, j = array.size(); i < j; i++) {
                JsonElement el = array.get(i);
                if (el.isJsonPrimitive())
                    cc.addWith(el.getAsString());
                else if (el.isJsonObject())
                    cc.addWith(fromJson(el.getAsJsonObject()));
            }
        }
        if (json.has("score")) {
            JsonObject score = json.getAsJsonObject("score");
            cc.setScoreUsername(score.get("name").getAsString());
            cc.setScoreObjective(score.get("objective").getAsString());
        }
        if (json.has("selector")) {
            cc.setSelector(json.get("selector").getAsString());
        }
        if (json.has("extra")) {
            JsonArray array = json.getAsJsonArray("extra");
            for (int i = 0, j = array.size(); i < j; i++) {
                JsonElement el = array.get(i);
                if (el.isJsonPrimitive())
                    cc.addExtra(el.getAsString());
                else if (el.isJsonObject())
                    cc.addExtra(fromJson(el.getAsJsonObject()));
            }
        }
        if (json.has("bold")) {
            cc.setBold(json.get("bold").getAsBoolean());
        }
        if (json.has("italic")) {
            cc.setItalic(json.get("italic").getAsBoolean());
        }
        if (json.has("underlined")) {
            cc.setUnderlined(json.get("underlined").getAsBoolean());
        }
        if (json.has("strikethrough")) {
            cc.setStrikethrough(json.get("strikethrough").getAsBoolean());
        }
        if (json.has("obfuscated")) {
            cc.setObfuscated(json.get("obfuscated").getAsBoolean());
        }
        if (json.has("color")) {
            cc.setColor(ChatColor.valueOf(json.get("color").getAsString().toUpperCase()));
        }
        if (json.has("clickEvent")) {
            cc.setClickEvent(ClickEvent.fromJson(json.getAsJsonObject("clickEvent")));
        }
        if (json.has("hoverEvent")) {
            cc.setHoverEvent(HoverEvent.fromJson(json.getAsJsonObject("hoverEvent")));
        }
        if (json.has("insertion")) {
            cc.setInsertion(json.get("insertion").getAsString());
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
        return this.asJson().equals(cc.asJson());
    }

    @Getter
    @AllArgsConstructor
    private final class StringChatComponent extends ChatComponent {
        private String string;

        @Override
        public JsonElement stripColor() {
            return this.asJson();
        }

        @Override
        public JsonElement asJson() {
            return new JsonPrimitive(this.string);
        }
    }
}
