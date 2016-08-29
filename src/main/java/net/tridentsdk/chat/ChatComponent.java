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

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Represents a component in a Minecraft chat format.
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatComponent {

    @Getter @Setter
    private String text, translate, selector, insertion, scoreUsername, scoreObjective;

    @Getter @Setter
    private ChatColor color;

    @Getter @Setter
    private ClickEvent clickEvent;

    @Getter @Setter
    private HoverEvent hoverEvent;

    private List<JsonElement> with = Lists.newCopyOnWriteArrayList();
    private List<ChatComponent> extra = Lists.newCopyOnWriteArrayList();
    private AtomicBoolean bold, italic, underlined, strikethrough, obfuscated;

    private ReadWriteLock $lock = new ReentrantReadWriteLock();

    /**
     * Gets all elements attached to the 'with' array.
     *
     * The 'with' array is used in conjunction with the 'translate' component.
     *
     * @return The with elements.
     */
    public List<JsonElement> getWith() {
        return Collections.unmodifiableList(with);
    }

    /**
     * Adds an element to the 'with' array.
     *
     * @param element The JSON element.
     *
     * @return This object.
     */
    public ChatComponent addWith(JsonElement element) {
        $lock.writeLock().lock();
        with.add(element);
        $lock.writeLock().unlock();
        return this;
    }

    /**
     * Adds an string to the 'with' array.
     *
     * @param element The string.
     *
     * @return This object.
     */
    public ChatComponent addWith(String element) {
        return addWith(new JsonPrimitive(element));
    }

    /**
     * Gets all components attached to the 'extra' array.
     *
     * Components in this array are appended to this component.
     *
     * @return The extra components.
     */
    public List<ChatComponent> getExtra() {
        return Collections.unmodifiableList(extra);
    }

    /**
     * Adds a component to the 'extra' array.
     *
     * @param component The component.
     *
     * @return This component.
     */
    public ChatComponent addExtra(ChatComponent component) {
        if (!hasExtra(component, true)) {
            $lock.writeLock().lock();
            this.extra.add(component);
            $lock.writeLock().unlock();
        }
        return this;
    }

    /**
     * Adds a string to the 'extra' array.
     *
     * @param string The string.
     *
     * @return This component.
     */
    public ChatComponent addExtra(String string) {
        $lock.writeLock().lock();
        this.extra.add(ChatComponent.create().setText(string));
        $lock.writeLock().unlock();
        return this;
    }

    /**
     * Checks if this component contains the given component, optionally recursively.
     *
     * @param component The component
     * @param recursive Whether to check children as well.
     *
     * @return True iff the given component exists in this component's heirarchy.
     */
    public boolean hasExtra(ChatComponent component, boolean recursive) {
        $lock.readLock().lock();
        if (extra.contains(component)) {
            $lock.readLock().unlock();
            return true;
        }
        if (!recursive) {
            $lock.readLock().unlock();
            return false;
        }
        for (ChatComponent child : extra) {
            if (child.hasExtra(component, true)) {
                $lock.readLock().unlock();
                return true;
            }
        }
        $lock.readLock().unlock();
        return false;
    }

    /**
     * Gets whether or not this component is bold.
     *
     * @return True iff it is.
     */
    public boolean isBold() {
        return bold != null && bold.get();
    }

    /**
     * Sets this component's boldness.
     *
     * @param bold The boldness.
     *
     * @return This component.
     */
    public ChatComponent setBold(boolean bold) {
        $lock.writeLock().lock();
        if (this.bold == null)
            this.bold = new AtomicBoolean(bold);
        else
            this.bold.set(bold);
        $lock.writeLock().unlock();
        return this;
    }

    /**
     * Gets whether or not this component is in italics.
     *
     * @return True iff it is.
     */
    public boolean isItalic() {
        return italic != null && italic.get();
    }

    /**
     * Sets this component's italic.
     *
     * @param italic The italic.
     *
     * @return This component.
     */
    public ChatComponent setItalic(boolean italic) {
        $lock.writeLock().lock();
        if (this.italic == null)
            this.italic = new AtomicBoolean(italic);
        else
            this.italic.set(italic);
        $lock.writeLock().unlock();
        return this;
    }

    /**
     * Gets whether or not this component is underlined.
     *
     * @return True iff it is.
     */
    public boolean isUnderlined() {
        return underlined != null && underlined.get();
    }

    /**
     * Sets this component's underlined.
     *
     * @param underlined The underline.
     *
     * @return This component.
     */
    public ChatComponent setUnderlined(boolean underlined) {
        $lock.writeLock().lock();
        if (this.underlined == null)
            this.underlined = new AtomicBoolean(underlined);
        else
            this.underlined.set(underlined);
        $lock.writeLock().unlock();
        return this;
    }

    /**
     * Gets whether or not this component is striked through.
     *
     * @return True iff it is.
     */
    public boolean isStrikethrough() {
        return strikethrough != null && strikethrough.get();
    }

    /**
     * Sets this component's strikethrough.
     *
     * @param strikethrough The strikethrough.
     *
     * @return This component.
     */
    public ChatComponent setStrikethrough(boolean strikethrough) {
        $lock.writeLock().lock();
        if (this.strikethrough == null)
            this.strikethrough = new AtomicBoolean(strikethrough);
        else
            this.strikethrough.set(strikethrough);
        $lock.writeLock().unlock();
        return this;
    }

    /**
     * Gets whether or not this component is in obfuscation.
     *
     * @return True iff it is.
     */
    public boolean isObfuscated() {
        return obfuscated != null && obfuscated.get();
    }

    /**
     * Sets this component's obfuscation.
     *
     * @param obfuscated The new obfuscated state.
     *
     * @return This component.
     */
    public ChatComponent setObfuscated(boolean obfuscated) {
        $lock.writeLock().lock();
        if (this.obfuscated == null)
            this.obfuscated = new AtomicBoolean(obfuscated);
        else
            this.obfuscated.set(obfuscated);
        $lock.writeLock().unlock();
        return this;
    }

    /**
     * Gets this component as a JSON object, ready to be sent to a client.
     *
     * @return The JSON object.
     */
    public JsonObject asJson() {
        $lock.readLock().lock();
        JsonObject json = new JsonObject();
        if (text != null) {
            json.addProperty("text", text);
        }
        if (translate != null) {
            json.addProperty("translate", translate);
            JsonArray array = new JsonArray();
            with.forEach(array::add);
            json.add("with", array);
        }
        if (scoreUsername != null && scoreObjective != null) {
            JsonObject score = new JsonObject();
            score.addProperty("name", scoreUsername);
            score.addProperty("objective", scoreObjective);
            json.add("score", score);
        }
        if (selector != null) {
            json.addProperty("selector", selector);
        }
        if (!extra.isEmpty()) {
            JsonArray extra = new JsonArray();
            this.extra.forEach(e -> extra.add(e.asJson()));
            json.add("extra", extra);
        }
        if (bold != null) {
            json.addProperty("bold", bold.get());
        }
        if (italic != null) {
            json.addProperty("italic", italic.get());
        }
        if (underlined != null) {
            json.addProperty("underlined", underlined.get());
        }
        if (strikethrough != null) {
            json.addProperty("strikethrough", strikethrough.get());
        }
        if (obfuscated != null) {
            json.addProperty("obfuscated", obfuscated.get());
        }
        if (color != null) {
            json.addProperty("color", color.name().toLowerCase());
        }
        if (clickEvent != null) {
            json.add("clickEvent", this.clickEvent.asJson());
        }
        if (hoverEvent != null) {
            json.add("hoverEvent", this.hoverEvent.asJson());
        }
        if (insertion != null) {
            json.addProperty("insertion", insertion);
        }
        $lock.readLock().unlock();
        return json;
    }

    /**
     * Gets this component as a JSON string.
     *
     * @return The JSON string.
     */
    public String toString() {
        return asJson().toString();
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
     *
     * @return The component.
     */
    public static ChatComponent text(String text) {
        return create().setText(text);
    }

    /**
     * Builds a component from a format string containing color codes.
     *
     * @param format The format string.
     *
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
