package net.tridentsdk.entity.living;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.tridentsdk.base.Position;
import net.tridentsdk.chat.ChatComponent;
import net.tridentsdk.chat.ChatType;
import net.tridentsdk.entity.meta.living.PlayerMeta;
import net.tridentsdk.ui.bossbar.BossBar;
import net.tridentsdk.ui.tablist.TabList;
import net.tridentsdk.world.World;
import net.tridentsdk.world.opt.GameMode;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public class PlayerTest {

    @Test
    public void testSendMessageDefault() {
        List<SentMessage> messages = new LinkedList<>();
        Player player = new DummyPlayer(messages);
        ChatComponent component = ChatComponent.text("hi there");
        player.sendMessage(component, ChatType.CHAT);
        player.sendMessage(component, ChatType.SYSTEM);
        player.sendMessage(component);

        assertEquals(messages.get(0).getMessage(), component);
        assertEquals(messages.get(0).getType(), ChatType.CHAT);
        assertEquals(messages.get(1).getMessage(), component);
        assertEquals(messages.get(1).getType(), ChatType.SYSTEM);
        assertEquals(messages.get(2).getMessage(), component);
        assertEquals(messages.get(2).getType(), ChatType.SYSTEM);
    }

    @Data
    private final class SentMessage {
        private final ChatComponent message;
        private final ChatType type;
    }

    @AllArgsConstructor
    private final class DummyPlayer implements Player {

        private final List<SentMessage> sentMessages;

        @Override
        public int getId() {
            return 0;
        }

        @Override
        public Position getPosition() {
            return null;
        }

        @Override
        public void setPosition(Position position) {

        }

        @Override
        public boolean isOnGround() {
            return false;
        }

        @Override
        public World getWorld() {
            return null;
        }

        @Override
        public void remove() {

        }

        @Override
        public void updateMetadata() {

        }

        @Override
        public String name() {
            return null;
        }

        @Override
        public UUID uuid() {
            return null;
        }

        @Override
        public void sendMessage(ChatComponent message, ChatType type) {
            sentMessages.add(new SentMessage(message, type));
        }

        @Override
        public void kick(ChatComponent reason) {

        }

        @Override
        public GameMode getGameMode() {
            return null;
        }

        @Override
        public void setGameMode(GameMode gameMode) {

        }

        @Override
        public TabList getTabList() {
            return null;
        }

        @Override
        public void setTabList(TabList tabList) {

        }

        @Override
        public PlayerMeta getMetadata() {
            return null;
        }

        @Override
        public Collection<BossBar> getBossBars() {
            return null;
        }

        @Override
        public void addBossBar(BossBar bar) {

        }

        @Override
        public void removeBossBar(BossBar bar) {

        }

        @Override
        public void updateBossBars() {

        }

    }

}
