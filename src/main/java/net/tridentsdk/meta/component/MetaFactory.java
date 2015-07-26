package net.tridentsdk.meta.component;

import com.google.common.base.Preconditions;
import net.tridentsdk.docs.InternalUseOnly;

/**
 * Allows Factory access to metadata components
 *
 * @author The TridentSDK Team
 */
public final class MetaFactory {
    private static volatile MetaProvider provider;

    private MetaFactory() {
    }

    @InternalUseOnly
    public static void setProvider(MetaProvider provider) {
        Preconditions.checkArgument(MetaFactory.provider == null, "Provider has already been set");
        MetaFactory.provider = provider;
    }

    /**
     * Creates a new meta collection
     *
     * @return the new meta collection
     */
    public static <T extends MetaOwner> MetaCollection<T> newCollection() {
        return new HashMetaCollection<>();
    }

    /**
     * Obtains a new instance of a meta value
     *
     * @param type the meta type class
     * @param <S>  the meta owner type
     * @param <T>  the meta value type
     * @return the new meta value
     */
    public static <S, T extends Meta<S>> T newValue(Class<T> type) {
        return provider.provide(type);
    }

    /**
     * Registers the meta value into the server meta registry
     *
     * @param meta the meta
     */
    public static void register(Meta meta) {
        provider.register(meta);
    }
}
