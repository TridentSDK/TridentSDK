package net.tridentsdk.entity.meta.minecart;

import net.tridentsdk.chat.ChatComponent;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface CommandMinecartMeta extends MinecartMeta {

    String getCommand();

    void setCommand(String command);

    ChatComponent getLastOutput();

    void setLastOutput(ChatComponent component);

}
