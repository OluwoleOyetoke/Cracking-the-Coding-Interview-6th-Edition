/*
 * Copyright (C) 2018 Oluwole Oyetoke <oluwoleoyetoke@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package CH09_Scalability_And_System_Design.Search_Engine;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cluster which performs a more expensive search operation Assumed to be the
 * back-end that provides answer to all search queries
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class Cluster {

    HashMap<String, String> answers = new HashMap<>();
    final int MACHINE_ID = 0;

    Cluster() {
        answers.put("Who won the 2018 world cup", "France");
        answers.put("What is the capital of the USA", "Washington");
        answers.put("What country is the most populous country in Africa", "Nigeria");
        answers.put("When is the next Olympics", "2020");
        answers.put("How many days are there in a leap year", "366 Days");
        answers.put("Who is the president of the USA", "Donald Trump");
    }

    /**
     * Asynchronous cluster call
     *
     * @param query to search for
     * @param callback callback
     */
    void answerClientAsync(String query, IMachine.IResponse callback) {
        String answer = answers.get(query);
        callback.response(answer, 1, MACHINE_ID);
    }

    /**
     * Asynchronous cluster call Assumes that cluster always has an answer to
     * return. Else, the user will see results of null on their screen
     *
     * @param query to search for
     * @return answer answer to query
     */
    String answerClientNonAsync(String query) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cluster.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers.get(query);
    }
}
