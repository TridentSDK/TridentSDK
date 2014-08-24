package net.tridentsdk.api;

import com.google.common.base.Preconditions;

public final class Trident {
	private static Server SERVER;
	
	/**
	 * Do not call.
	 */
	public static void setServer(Server s) {
		Preconditions.checkArgument(SERVER == null, "Can only set server instance once!");
		SERVER = s;
	}
	
	public static Server getServer() {
		return SERVER;
	}
}
