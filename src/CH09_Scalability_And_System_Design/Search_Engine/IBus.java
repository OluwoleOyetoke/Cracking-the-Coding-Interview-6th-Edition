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

/**
 * Bus Interface
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public interface IBus {

    boolean request(int queryHash, IMachine.IResponse callback, RequestType requestType, RequestSource from, IMachine requester);

    void register(IMachine machine);

    void invalidateAll(int queryHash); //invalidates all shared version
}
