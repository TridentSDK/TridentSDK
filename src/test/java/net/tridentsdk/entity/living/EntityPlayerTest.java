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
package net.tridentsdk.entity.living;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.tridentsdk.base.Position;
import net.tridentsdk.command.CommandSourceType;
import net.tridentsdk.inventory.Inventory;
import net.tridentsdk.inventory.PlayerInventory;
import net.tridentsdk.meta.entity.living.EntityPlayerMeta;
import net.tridentsdk.ui.bossbar.BossBar;
import net.tridentsdk.ui.chat.ChatComponent;
import net.tridentsdk.ui.chat.ChatType;
import net.tridentsdk.ui.tablist.TabList;
import net.tridentsdk.ui.title.Title;
import net.tridentsdk.world.World;
import net.tridentsdk.world.opt.GameMode;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
public class EntityPlayerTest {

    @Test
    public void testSendMessageDefault() {
        List<EntityPlayerTest.SentMessage> messages = new LinkedList<>();
        EntityPlayer player = new DummyEntityPlayer(messages);
        ChatComponent component = ChatComponent.text("hi there");
        player.sendMessage(component, ChatType.CHAT);
        player.sendMessage(component);
        player.sendMessage("hi there");

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
    private final class DummyEntityPlayer implements EntityPlayer {

        private final List<EntityPlayerTest.SentMessage> sentMessages;

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
        public String getName() {
            return null;
        }

        @Override
        public UUID getUuid() {
            return null;
        }

        @Override
        public ChatComponent getDisplayName() {
            return null;
        }

        @Override
        public void setDisplayName(ChatComponent displayName) {

        }

        @Override
        public void sendMessage(ChatComponent message, ChatType type) {
            this.sentMessages.add(new EntityPlayerTest.SentMessage(message, type));
        }

        @Override
        public void kick(ChatComponent reason) {

        }

        @Override
        public void chat(String msg) {

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
        public EntityPlayerMeta getMetadata() {
            return null;
        }

        @Override
        public List<BossBar> getBossBars() {
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

        @Override
        public void sendTitle(Title title) {

        }

        @Override
        public void resetTitle() {

        }

        @Override
        public PlayerInventory getInventory() {
            return null;
        }

        @Override
        public void openInventory(Inventory inventory) {

        }

        @Override
        public boolean isGodMode() {
            return false;
        }

        @Override
        public void setGodMode(boolean godMode) {

        }

        @Override
        public boolean canFly() {
            return false;
        }

        @Override
        public void setCanFly(boolean canFly) {

        }

        @Override
        public boolean isFlying() {
            return false;
        }

        @Override
        public void setFlying(boolean flying) {

        }

        @Override
        public float getFlyingSpeed() {
            return 0;
        }

        @Override
        public void setFlyingSpeed(float flyingSpeed) {

        }

        @Override
        public float getWalkingSpeed() {
            return 0;
        }

        @Override
        public void setWalkingSpeed(float walkingSpeed) {

        }

        @Override
        public void setSprinting(boolean sprinting) {

        }

        @Override
        public boolean isSprinting() {
            return false;
        }

        @Override
        public void setCrouching(boolean crouching) {

        }

        @Override
        public boolean isCrouching() {
            return false;
        }

        @Override
        public void runCommand(String command) {

        }

        @Override
        public CommandSourceType getCmdType() {
            return null;
        }

        @Override
        public boolean hasPermission(String perm) {
            return false;
        }

        @Override
        public void addPermission(String perm) {}

        @Override
        public boolean removePermission(String perm) {
            return false;
        }

        @Override
        public void setOp(boolean op) {

        }

        @Override
        public boolean isOp() {
            return false;
        }
    }
}
