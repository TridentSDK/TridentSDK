/*
 * Trident - A Multithreaded Server Alternative
 * Copyright 2017 The TridentSDK Team
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
package net.tridentsdk.entity.meta.inanimate;

import net.tridentsdk.entity.meta.EntityMeta;

import java.awt.*;

/**
 * @author TridentSDK
 * @since 0.5-alpha
 */
// TODO - documentation
public interface AreaEffectCloudMeta extends EntityMeta {

    float getRadius();

    void setRadius(float radius);

    Color getColor();

    void setColor(Color color);

    boolean isSinglePoint();

    void setSinglePoint(boolean singlePoint);

    int getParticleID();

    void setParticleID(int id);

    int getParticleParameter1();

    void setParticleParameter1(int par);

    int getParticleParameter2();

    void setParticleParameter2(int par);

}
