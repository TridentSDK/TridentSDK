package net.tridentsdk.api.util;

public final class StringUtil {

    private StringUtil() {
    }

    /**
     * A for-loop efficient method for concating strings (or in some cases objects)
     *
     * @param objects Objects you wish to concat into a String
     * @return Built string
     */
    public static String concat(Object... objects) {
        StringBuilder builder = new StringBuilder();

        for(Object o : objects) {
            builder.append(o);
        }

        return builder.toString();
    }

}
