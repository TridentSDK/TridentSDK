/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2014 The TridentSDK Team
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
package java.net.tridentsdk.meta;

import net.tridentsdk.meta.ChatColor;
import net.tridentsdk.meta.Message;
import net.tridentsdk.meta.MessageBuilder;
import org.junit.Assert;
import org.junit.Test;

public class MessageBuilderTest {

    @Test
    public void testClass() throws Exception{

        MessageBuilder message = new MessageBuilder("Hello World");
        message.color(ChatColor.AQUA);
        message.link("URL");
        message.file("FILE");
        message.hover("HOVER");
        message.then("THEN");
        message.then(new Message().text("MESSAGE"));
        message.build();

        Assert.assertEquals(message.toJson(), "{\"text\":\"\",\"extra\":[{\"text\":\"Hello World\",\"color\":\"aqua\",\"clickEvent\":{\"action\":\"open_file\",\"value\":\"FILE\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"HOVER\"}},{\"text\":\"THEN\"}]}");

    }

}