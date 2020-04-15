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
package com.cracking.the.coding.interview.chapter10.sortingandsearching.question;

/**
 * <b>Sort Big File:</b>s Imagine you have a 20 GB file with one string per line.
 * Explain how you would sort the file.
 *
 * @author Oluwole Oyetoke
 * {@literal <}oluwoleoyetoke{@literal @}gmail.com{@literal>}
 */
public class Question6 {

    /**
     * When an interviewer gives a size limit of 20 gigabytes, it should tell
     * you something. In this case, it suggests that they don't want you to
     * bring all the data into memory. So what do we do? We only bring part of
     * the data into memory. We'll divide the file into chunks, which are x
     * megabytes each, where x is the amount of memory we have available. Each
     * chunk is sorted separately and then saved back to the file system. Once
     * all the chunks are sorted, we merge the chunks, one by one. At the end,
     * we have a fully sorted file. This algorithm is known as external sort.
     *
     */

}
