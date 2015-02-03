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

package net.tridentsdk.concurrent;

import org.junit.Assert;
import org.junit.Test;

public class SchedulerTypeTest {

    @Test
    public void testEnum() throws Exception {
        Assert.assertEquals(SchedulerType.ASYNC_RUN, SchedulerType.ASYNC_RUN);
        Assert.assertEquals(SchedulerType.ASYNC_LATER, SchedulerType.ASYNC_LATER);
        Assert.assertEquals(SchedulerType.ASYNC_REPEAT, SchedulerType.ASYNC_REPEAT);
        Assert.assertEquals(SchedulerType.SYNC_RUN, SchedulerType.SYNC_RUN);
        Assert.assertEquals(SchedulerType.SYNC_LATER, SchedulerType.SYNC_LATER);
        Assert.assertEquals(SchedulerType.SYNC_REPEAT, SchedulerType.SYNC_REPEAT);
    }
}