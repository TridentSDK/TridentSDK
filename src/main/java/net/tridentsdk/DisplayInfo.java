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
package net.tridentsdk;

import net.tridentsdk.config.Config;
import net.tridentsdk.registry.Factory;
import net.tridentsdk.registry.Registered;
import net.tridentsdk.util.TridentLogger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Information displayed to the client in the server list
 *
 * @author The TridentSDK Team
 * @since 0.3-alpha-DP
 */
public class DisplayInfo {
    private final Config config = Factory.newConfig(Trident.fileContainer() + "server.json"); // Init code
    private final String motd = config.getString("motd", Defaults.MOTD);
    private final File file = new File(config.getString("image-position", Defaults.MOTD_IMAGE_LOCATION));
    private final int maxPlayers = config.getInt("max-players", Defaults.MAX_PLAYERS);
    private final boolean canChangeImage = config.getBoolean("image-changing-allowed", Defaults.IMAGE_CHANGING_ALLOWED);

    public DisplayInfo() {
    }

    /**
     * A string containing the current broadcast MOTD of the server
     *
     * @return a string containing the MOTD of the server, may be empty, never null
     */
    public String motd() {
        return motd;
    }

    /**
     * Returns the {@link java.io.File} that represents the picture displayed next to the server listing on clients
     *
     * @return the file that represents the picture sent to clients when they ping the server
     * @see #motdImage() for the representing the image sent to clients
     */
    public File motdPicture() {
        return file;
    }

    /**
     * Gets the {@link java.awt.image.BufferedImage} that represents the Motd picture sent to clients
     *
     * @return the image sent to clients
     * @see #motdPicture() for the file itself
     */
    public BufferedImage motdImage() {
        BufferedImage img = null;

        try {
            img = ImageIO.read(motdPicture());
        } catch (IOException ex) {
            TridentLogger.error(ex);
        }

        return img;
    }

    /**
     * Obtains the minecraft server version
     *
     * @return
     */
    public String version() {
        return "1.8";
    }

    /**
     * Sets the MOTD image sent to clients, may or may not take a server restart to take effect
     *
     * @param image the image to set it to
     * @return 0 for success, -1 if this feature is disabled in config, -2 for generic failure
     */
    public int setMotdImage(BufferedImage image) {
        if (!canChangeImage) {
            return -1;
        }

        try {
            ImageIO.write(image, "PNG", motdPicture());
            return 0;
        } catch (IOException ignored) {
            return -2;
        }
    }

    /**
     * Gets the maximum number of players allowed on the server
     *
     * @return the maximum number of players the server will allow
     */
    public int maxPlayers() {
        return maxPlayers;
    }

    public int playerCount() {
        return Registered.players().size();
    }
}
