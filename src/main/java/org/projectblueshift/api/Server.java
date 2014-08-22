package org.projectblueshift.api;

import java.net.InetSocketAddress;

public interface Server {

    public InetSocketAddress getSocketAddress();

    public short getPort();

    public void shutdown();

    public void init();
}
