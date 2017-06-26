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
package net.tridentsdk.registry;

import net.tridentsdk.concurrent.Scheduler;
import net.tridentsdk.concurrent.SelectableThreadPool;
import net.tridentsdk.docs.InternalUseOnly;
import net.tridentsdk.event.Events;
import net.tridentsdk.inventory.Inventories;
import net.tridentsdk.inventory.crafting.RecipeManager;
import net.tridentsdk.meta.component.MetaProvider;
import net.tridentsdk.plugin.Plugins;
import net.tridentsdk.plugin.channel.PluginChannels;
import net.tridentsdk.plugin.cmd.Commands;
import net.tridentsdk.service.ChatFormatter;
import net.tridentsdk.service.Transactions;
import net.tridentsdk.world.MassChange;
import net.tridentsdk.world.World;
import net.tridentsdk.world.WorldLoader;
import net.tridentsdk.world.gen.ChunkGenerator;

import java.util.Map;

@InternalUseOnly
public interface Implementation {
    SelectableThreadPool newPool(int i, String s);

    WorldLoader newLoader(Class<? extends ChunkGenerator> g);
    MassChange newMc(World world);

    Map<String, World> worlds();

    MetaProvider meta();

    Transactions trasacts();

    ChatFormatter format();

    Players players();
    PlayerStatus statuses();

    Events events();
    Plugins plugins();

    PluginChannels channels();

    Commands cmds();
    Scheduler scheduler();
    Inventories inventories();

    RecipeManager recipe();
}
