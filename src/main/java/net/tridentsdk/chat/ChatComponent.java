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

    public List<JsonElement> getWith() {
        return Collections.unmodifiableList(with);
    }

    public ChatComponent addWith(JsonElement element) {
        $lock.writeLock().lock();
        with.add(element);
        $lock.writeLock().unlock();
        return this;
    }

    public ChatComponent addWith(String with) {
        return addWith(new JsonPrimitive(with));
    }

    public List<ChatComponent> getExtra() {
        return Collections.unmodifiableList(extra);
    }

    public ChatComponent addExtra(ChatComponent component) {
        if (!hasExtra(component, true)) {
            $lock.writeLock().lock();
            this.extra.add(component);
            $lock.writeLock().unlock();
        }
        return this;
    }

    public ChatComponent addExtra(String extra) {
        return addExtra(ChatComponent.create().setText(extra));
    }

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

    public boolean isBold() {
        return bold != null && bold.get();
    }

    public ChatComponent setBold(boolean bold) {
        $lock.writeLock().lock();
        if (this.bold == null)
            this.bold = new AtomicBoolean(bold);
        else
            this.bold.set(bold);
        $lock.writeLock().unlock();
        return this;
    }

    public boolean isItalic() {
        return italic != null && italic.get();
    }

    public ChatComponent setItalic(boolean italic) {
        $lock.writeLock().lock();
        if (this.italic == null)
            this.italic = new AtomicBoolean(italic);
        else
            this.italic.set(italic);
        $lock.writeLock().unlock();
        return this;
    }

    public boolean isUnderlined() {
        return underlined != null && underlined.get();
    }

    public ChatComponent setUnderlined(boolean underlined) {
        $lock.writeLock().lock();
        if (this.underlined == null)
            this.underlined = new AtomicBoolean(underlined);
        else
            this.underlined.set(underlined);
        $lock.writeLock().unlock();
        return this;
    }

    public boolean isStrikethrough() {
        return strikethrough != null && strikethrough.get();
    }

    public ChatComponent setStrikethrough(boolean strikethrough) {
        $lock.writeLock().lock();
        if (this.strikethrough == null)
            this.strikethrough = new AtomicBoolean(strikethrough);
        else
            this.strikethrough.set(strikethrough);
        $lock.writeLock().unlock();
        return this;
    }

    public boolean isObfuscated() {
        return obfuscated != null && obfuscated.get();
    }

    public ChatComponent setObfuscated(boolean obfuscated) {
        $lock.writeLock().lock();
        if (this.obfuscated == null)
            this.obfuscated = new AtomicBoolean(obfuscated);
        else
            this.obfuscated.set(obfuscated);
        $lock.writeLock().unlock();
        return this;
    }

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

    public String toString() {
        return asJson().toString();
    }

    public static ChatComponent create() {
        return new ChatComponent();
    }

    public static ChatComponent empty() {
        return create().setText("");
    }

    public static ChatComponent text(String text) {
        return create().setText(text);
    }

}
