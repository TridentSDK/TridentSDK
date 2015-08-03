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
package net.tridentsdk.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO Write Description
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public abstract class ScoreboardModule {

    private List<ScoreboardItem> permanentItems = new ArrayList<>();
    private List<ScoreboardItem> lastItems = new ArrayList<>();
    private List<ScoreboardItem> liveItems = new ArrayList<>();
    private int tickInterval = 1;
    private int resetSpace = 0;
    private int space = 0;

    public abstract void onTick();

    public void setTickInterval(int ticks){
        this.tickInterval = ticks;
    }

    public ScoreboardItem addPermanentSpace(){
        ScoreboardItem item = new ScoreboardItem("%SPACE%" + space++);
        resetSpace++;
        if(!permanentItems.contains(item)){
            permanentItems.add(item);
        }
        return item;
    }

    public ScoreboardItem addSpace(){
        ScoreboardItem item = new ScoreboardItem("%SPACE%" + space++);
        if(!lastItems.contains(item)){
            liveItems.add(item);
        }
        return item;
    }

    public ScoreboardModule addSpace(int score){
        ScoreboardItem item = new ScoreboardItem("%SPACE%" + space++);
        item.setScore(score);
        if(!lastItems.contains(item)){
            liveItems.add(item);
        }
        return this;
    }

    public ScoreboardItem addPermanentItem(String value){
        ScoreboardItem item = new ScoreboardItem(value);
        if(!permanentItems.contains(item)){
            permanentItems.add(item);
        }
        return item;
    }

    public ScoreboardItem addItem(String value){
        ScoreboardItem item = new ScoreboardItem(value);
        if(!lastItems.contains(item)){
            liveItems.add(item);
        }
        return item;
    }

    public ScoreboardModule addItem(String value, int score){
        ScoreboardItem item = new ScoreboardItem(value);
        item.setScore(score);
        if(!lastItems.contains(item)){
            liveItems.add(item);
        }
        return this;
    }

    public boolean update(int tick){
        if(tick % tickInterval == 0){
            space = resetSpace;
            lastItems.clear();
            lastItems.addAll(liveItems);
            liveItems.clear();
            onTick();
            return true;
        }

        return false;
    }

    public List<ScoreboardItem> updatedItems(){
        return liveItems.stream().filter(item -> !lastItems.contains(item)).collect(Collectors.toList());
    }

    public List<ScoreboardItem> removedItems(){
        return lastItems.stream().filter(item -> !liveItems.contains(item)).collect(Collectors.toList());
    }

    public List<ScoreboardItem> permanentItems(){
        return permanentItems;
    }

    public List<ScoreboardItem> liveItems(){
        return liveItems;
    }

}
