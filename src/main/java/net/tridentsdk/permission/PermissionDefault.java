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

package net.tridentsdk.permission;

/**
 * An enum containing standard default access rights for permissions.
 * 
 * @author Nick Robson
 */
public enum PermissionDefault {
	
	/**
	 * All {@link Permissible}s have this by default. The permission can be set to false to disable.
	 */
	ALL,
	/**
	 * All server operators have this by default. The permission can be set to true/false to enable/disable.
	 */
	OP,
	/**
	 * All {@link Permissible}s that are not server operators have this by default. The permission can be set to true/false to enable/disable.
	 */
	NOT_OP,
	/**
	 * No {@link Permissible}s have this permission by default. The permission must be set to true to enable.
	 */
	NONE;

}
