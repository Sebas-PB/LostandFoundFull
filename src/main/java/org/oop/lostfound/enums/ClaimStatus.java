package org.oop.lostfound.enums;

public enum ClaimStatus { 
    PENDING("Pending", "#FFA500" ), 
    APPROVED("Approved", "#4CAF50"),
    REJECTED("Rejected", "#F44336");

    private final String displayName;
    private final String color;

    ClaimStatus(String displayName, String color) {
        this.displayName = displayName;
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getColor() {
        return color;
    }
}
