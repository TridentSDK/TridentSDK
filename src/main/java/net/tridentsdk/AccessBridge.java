package net.tridentsdk;

import com.google.common.collect.Maps;
import net.tridentsdk.concurrent.HeldValueLatch;
import net.tridentsdk.util.TridentLogger;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Map;

/**
 * Allows the implementation and the API to communicate and pass values over the bridge
 *
 * @author The TridentSDK Team
 */
@ThreadSafe
public final class AccessBridge {
    private static final AccessBridge BRIDGE = new AccessBridge();

    @GuardedBy("instances")
    private final Map<Class<?>, HeldValueLatch> instances = Maps.newHashMap();

    private AccessBridge() {
    }

    /**
     * Obtains the singleton instance of the access bridge
     *
     * @return the instance of the bridge
     */
    public static AccessBridge open() {
        return BRIDGE;
    }

    /**
     * Sends an instance of an object over the bridge
     *
     * @param id the identifier for the instance
     * @param impl the instance to send
     */
    public void send(Class<?> id, Object impl) {
        HeldValueLatch latch;
        synchronized (instances) {
            latch = instances.get(id);
            if (latch == null) {
                latch = HeldValueLatch.create();
                instances.put(id, latch);
            }
        }

        latch.countDown(impl);
    }

    /**
     * Sends an instance of an object over the bridge
     *
     * <p>The identifier for this object is the superclass</p>
     *
     * @param impl the instance to send
     */
    public void sendSuper(Object impl) {
        send(impl.getClass().getSuperclass(), impl);
    }

    /**
     * Sends an instance of an object over the bridge
     *
     * <p>The identifier for this object is the class of the first implemented interface</p>
     *
     * @param impl the instance to send
     */
    public void sendImplemented(Object impl) {
        send(impl.getClass().getInterfaces()[0], impl);
    }

    /**
     * Sends an instance of an object over the bridge
     *
     * <p>The identifier for this object is the class of the object itself</p>
     *
     * @param impl the instance to send
     */
    public void sendSelf(Object impl) {
        send(impl.getClass(), impl);
    }

    /**
     * Obtains the instance sent of the bridge, blocking if it has not been sent yet
     *
     * @param c the identifier for the sent instance
     * @param <T> the type for the object
     * @return the instance of the object sent over the bridge with the provided identifier
     */
    public <T> T demand(Class<T> c) {
        HeldValueLatch latch;
        synchronized (instances) {
            latch = instances.get(c);
            if (latch == null) {
                latch = HeldValueLatch.create();
                instances.put(c, latch);
            }
        }

        try {
            return (T) latch.await();
        } catch (InterruptedException e) {
            TridentLogger.error(e);
            return null;
        }
    }
}