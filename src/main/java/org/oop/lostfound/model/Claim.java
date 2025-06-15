package org.oop.lostfound.model;

import java.time.LocalDate;

public class Claim {
    private int claimId;
    private String itemName;
    private LocalDate claimDate;
    private String foundBy;
    private String claimedBy;
    private String description;
    private String claimantPhone;
    private String imageUrl;

    public Claim() {
    }

    public Claim(int claimId, String itemName, LocalDate claimDate, String foundBy, String claimedBy, String description, String claimantPhone, String imageUrl) {
        this.claimId = claimId;
        this.itemName = itemName;
        this.claimDate = claimDate;
        this.foundBy = foundBy;
        this.claimedBy = claimedBy;
        this.description = description;
        this.claimantPhone = claimantPhone;
        this.imageUrl = imageUrl;
    }

    // Getter & Setter
    public int getClaimId() { return claimId; }
    public void setClaimId(int claimId) { this.claimId = claimId; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public LocalDate getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDate claimDate) { this.claimDate = claimDate; }

    public String getFoundBy() { return foundBy; }
    public void setFoundBy(String foundBy) { this.foundBy = foundBy; }

    public String getClaimedBy() { return claimedBy; }
    public void setClaimedBy(String claimedBy) { this.claimedBy = claimedBy; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getClaimantPhone() { return claimantPhone; }
    public void setClaimantPhone(String claimantPhone) { this.claimantPhone = claimantPhone; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }


}