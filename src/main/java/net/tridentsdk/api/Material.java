package net.tridentsdk.api;

/**
 * TODO
 */
public enum Material {

    ;

    private String id;
    private String data;

    private Material(String id, String data) {
        this.id = id;
        this.data = data;
    }

    private Material(String id) {
        this(id, "");
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public static Material fromString(String id) {
        for(Material mat : values()) {
            if(mat.getId().replaceAll("_", " ").equalsIgnoreCase(id))
                return mat;
        }

        return null;
    }
}
