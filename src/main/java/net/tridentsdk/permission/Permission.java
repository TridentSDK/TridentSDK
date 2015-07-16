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

import com.google.common.base.Preconditions;

/**
 * A simple class representing a permission including default access permissions.
 * 
 * @author Nick Robson
 */
public final class Permission {
	
	private String permission;
	private PermissionDefault def;
	
	public Permission(String permission, PermissionDefault def) {
		Preconditions.checkNotNull(permission, "Permission cannot be null");
		Preconditions.checkNotNull(def, "PermissionDefault cannot be null");
		
		this.permission = permission;
		this.def = def;
	}
	
	public Permission(String permission) {
		this(permission, PermissionDefault.OP);
	}
	
	/**
	 * Returns the underlying permission.
	 */
	@Override
	public String toString() {
		return permission;
	}
	
	/**
	 * Returns the default {@link PermissionDefault} setting.
	 * @return The default access.
	 */
	public PermissionDefault getDefault() {
		return def;
	}

}
