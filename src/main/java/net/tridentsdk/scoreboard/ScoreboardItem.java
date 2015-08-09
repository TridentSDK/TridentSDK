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

/**
 * TODO Write Description
 *
 * @author The TridentSDK Team
 * @since 0.4-alpha
 */
public class ScoreboardItem {

    private int score;
    private String value;

    public ScoreboardItem(String value) {
        this.value = value;
    }

    /**
     * Returns the item value
     *
     * @return the item value
     */
    public String value() {
        return value;
    }

    /**
     * Returns the item score
     *
     * @return the item score
     */
    public int score() {
        return score;
    }

    /**
     * Set the score of the item
     *
     * @param score The score to set to
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass()) && score == ((ScoreboardItem) o).score && value.equals(((ScoreboardItem) o).value);

    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ScoreboardItem{" +
                "score=" + score +
                ", value='" + value + '\'' +
                '}';
    }

}
