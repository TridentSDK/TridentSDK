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
/**
 * Configuration wrappers over JSON/facilities provided by GSON
 *
 * <p>Configs can be used and created using the {@link net.tridentsdk.config.JsonConfig}</p>
 *
 * <p>Basic functionality for the JSON configuration is in the {@link net.tridentsdk.config.ConfigSection}</p>
 *
 * <p>This package does not contain thread safe classes. Primarily, this is for usage within a single plugin. Use extra
 * caution to have the right executor when using configurations of other plugins.</p>
 */

package net.tridentsdk.config;