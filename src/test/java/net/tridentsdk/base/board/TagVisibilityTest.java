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

package net.tridentsdk.base.board;

import net.tridentsdk.base.board.TagVisibility;
import org.junit.Assert;
import org.junit.Test;

public class TagVisibilityTest {

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals(TagVisibility.ALWAYS.toString(), "always");
        Assert.assertEquals(TagVisibility.HIDE_OTHER_TEAMS.toString(), "hideFromOtherTeams");
        Assert.assertEquals(TagVisibility.HIDE_OWN_TEAM.toString(), "hideFromOwnTeam");
        Assert.assertEquals(TagVisibility.NEVER.toString(), "never");
    }
}