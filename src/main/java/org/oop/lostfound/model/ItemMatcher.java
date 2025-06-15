package org.oop.lostfound.model;

import java.util.*;

public class ItemMatcher {
    public static List<Item> matchItems(List<LostItem> lostItems, List<FoundItem> foundItems) {
        List<Item> matches = new ArrayList<>();
        for (LostItem lost : lostItems) {
            for (FoundItem found : foundItems) {
                if (lost.getCategory().equalsIgnoreCase(found.getCategory()) &&
                    lost.getDescription().toLowerCase().contains(found.getDescription().toLowerCase())) {
                    matches.add(found);
                }
            }
        }
        return matches;
    }
}