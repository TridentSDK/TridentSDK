package net.tridentsdk.api;

import com.google.common.base.Preconditions;

public final class Trident {
	private static Server server;
	
	/**
	 * Do not call.
	 */
	public static void setServer(Server s) {
		Preconditions.checkArgument(server != null, "Can only set server instance once!");
		server = s;
	}
	
	public static Server getServer() {
		return server;
	}
}
