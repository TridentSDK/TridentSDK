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
 * An interface describing an object that can possess permissions.
 * 
 * @author Nick Robson
 */
public interface Permissible {
	
	/**
	 * Gets whether or not this user has a specified permission, defaulting to operators only.
	 * @param permission The permission to be checked for.
	 * @return True iff they do.
	 */
	default boolean hasPermission(String permission) {
		return hasPermission(new Permission(permission));
	}
	
	/**
	 * Gets whether or not this user has a specified permission.
	 * @param permission The {@link Permission} to be checked for.
	 * @return True iff they do.
	 */
	boolean hasPermission(Permission permission);
	
	/**
	 * Sets a specified permission to be enabled or disabled for the permissible object.
	 * @param permission The permission to be granted.
	 * @param enabled Whether or not the permission should be enabled.
	 */
	default void setPermission(String permission, boolean enabled) {
		setPermission(new Permission(permission), enabled);
	}
	
	/**
	 * Sets a specified permission to be enabled or disabled for the permissible object.
	 * @param permission The permission to be granted.
	 * @param enabled Whether or not the permission should be enabled.
	 */
	void setPermission(Permission permission, boolean enabled);

}
