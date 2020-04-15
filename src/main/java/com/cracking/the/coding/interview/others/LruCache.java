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
package com.cracking.the.coding.interview.others;

import java.util.HashMap;

;

/**
 *
 * @author Oluwole Oyetoke - oluwoleoyetoke@gmail.com
 */
public class LruCache {
    
    HashMap<String, WebPage> lookupMap;
    int CACHE_SIZE;
    int CURRENT_SIZE;
    WebPage first;
    WebPage last;

    LruCache(int size) {
        CACHE_SIZE = size;
        lookupMap = new HashMap<>();
    }

    public WebPage goToPage(String url) {
        if (lookupMap.containsKey(url)) {
            WebPage page = lookupMap.get(url);
            removeNode(page);
            addToTop(page);
            return page;
        } else {
            System.out.println("Page Not in cache. Go to main memory");
            String content = getPageFromMainMemory();
            addPageToCache(url, content);
            return goToPage(url); 
        }
    }

    public void addPageToCache(String url, String content) {
            WebPage newNode = new WebPage(content);
            newNode.url = url;
            if (CURRENT_SIZE >= CACHE_SIZE) {
                lookupMap.remove(last.url);
                removeNode(last);
                addToTop(newNode);
            } else {
                addToTop(newNode);
                CURRENT_SIZE++;
            }
            
    }
    
    public void editPageInCache(String url, String content){
        if (lookupMap.containsKey(url)) {
            WebPage present = lookupMap.get(url);
            present.content = content;
            removeNode(present);
            addToTop(present);
        }
    }

    public void removeNode(WebPage node) {
        if (node.before != null) {
            node.before.after = node.after;
        } else {
            first = node.after;
        }
        if (node.after != null) {
            node.after.before = node.before;
        } else {
            last = node.before;
        }
        CURRENT_SIZE--;
    }

    public void addToTop(WebPage node) {
        node.after = first;
        node.before = null;
        if (first != null) {
            first.before = node;
            first = node;
        }
        if (last == null) {
            last = first;
        }
    }
    
    public String getPageFromMainMemory(){
        return "def_content";
    }
    
    
        
    class WebPage {
        WebPage before;
        WebPage after;
        String url;
        String content;

        WebPage(String con) {
            content = con;
        }
    }
}
