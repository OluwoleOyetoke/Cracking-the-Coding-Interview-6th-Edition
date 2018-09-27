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
package Others;

import java.util.HashMap;

/**
 * Create a LRU cache to print web history based on recently visited sites
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class BrowserHistory {

    private HashMap<String, Webpage> cache = new HashMap<>();
    private Webpage first;
    private Webpage last;
    private int HISTORY_SIZE;
    private int MAX_SIZE;

    /**
     * Constructor
     *
     * @param maxSize
     */
    BrowserHistory(int maxSize) {
        MAX_SIZE = maxSize;
        HISTORY_SIZE = 0;
    }

    /**
     * Visit a webpage
     *
     * @param url url
     */
    public void goToWebPage(String url) {
        //check if youve gone there before
        if (cache.containsKey(url)) {
            //if so
            //remove from doubly linked list
            //place on top
            addToTop(remove(url));
        } else {
            Webpage newPage = new Webpage(url);
            cache.put(url, newPage);
            addToTop(newPage);
        }
    }

    /**
     * Remove page from cache
     *
     * @param url url
     * @return webpage webpage
     */
    private Webpage remove(String url) {
        if (first == null) {
            return null;
        }
        Webpage current = first;
        while (current != null) {
            if (current.url.equals(url)) {
                System.out.println("Removed: " + url);
                cache.remove(url);
                if (current.previous != null) {
                    current.previous.next = current.next;
                } else {
                    first = null;
                }
                if (current.next != null) {
                    current.next.previous = current.previous;
                } else {
                    last = current.previous;
                }
                HISTORY_SIZE--;
                return current;
            }

            current = current.next;
        }
        //thro an exception here
        System.out.println("Webpage (" + url + ") not found in cache");
        return null;
    }

    /**
     * Add page to top of cache
     *
     * @param page page
     */
    private void addToTop(Webpage page) {
        if (HISTORY_SIZE >= MAX_SIZE) {
            cache.remove(last.url);
            remove(last.url);
        }

        if (first == null) {
            System.out.println("Added: " + page.url);
            page.previous = null;
            first = page;
            last = first;
            HISTORY_SIZE++;
            return;
        }
        System.out.println("Added: " + page.url);
        page.previous = null;
        first.previous = page;
        page.next = first;
        first = page;
        HISTORY_SIZE++;
    }

    public void printHistory() {
        if (first == null) {
            System.out.println("You havent visited any page yet");
        }
        System.out.println("\nHISTORY: \n");
        Webpage current = first;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.url);
            count++;
            current = current.next;
        }
    }

    /**
     * web page class
     */
    class Webpage {

        Webpage previous;
        Webpage next;
        String content;
        String url;

        Webpage(String url) {
            this.url = url;
        }
    }

    /**
     * Browser history Main method
     *
     * @param args command line arguments
     *//*
    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory(4);
        browser.goToWebPage("www.eagle-beacon.com");
        browser.goToWebPage("www.google.com");
        browser.goToWebPage("www.leetcode.com");
        browser.goToWebPage("www.livescores.com");
        browser.goToWebPage("www.covenantuniversity.edu.ng");
        browser.goToWebPage("www.leeds.ac.uk");
        //browser.goToWebPage("www.livescores.com");
        browser.printHistory();
    }*/
}
