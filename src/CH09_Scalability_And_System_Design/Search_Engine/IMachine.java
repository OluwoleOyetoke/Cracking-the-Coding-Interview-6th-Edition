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

/**
 * Machine Interface
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public interface IMachine {

    void cearCache();

    void checkYourCache(int queryHash, IMachine.IResponse callback, RequestType requestType, RequestSource from, IMachine requester);

    void search(int queryHash, String query, int count, IMachine.IResponse callback);

    String processSearch(String query);

    int checkDataState(int data);

    void addToCache(int queryHash, String result);

    public void init();

    public HashMap<Integer, Integer> getDirectory();

    public int getMachineId();

    public IMachine getMe();

    public int getResponseSent();

    public HashMap<Integer, String> getCache();

    //callback
    interface IResponse {

        void response(String result, int present, int machineId);
    }
}
