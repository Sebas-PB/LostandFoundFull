package org.oop.lostfound.model;

import java.time.LocalDate;
import org.oop.lostfound.enums.*;

public abstract class Item {
    protected int id;
    protected String name;
    protected String description;
    protected String location;
    protected Category category;
    protected String contact;
    protected String imageUrl;
    protected LocalDate date;

    public Item(int id, String name, String description, String location, Category category, String contact, String imageUrl, LocalDate date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.category = category;
        this.contact = contact;
        this.imageUrl = imageUrl;
        this.date = date;
    }

    // Getters dan Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    // Abstract method untuk laporan
    public abstract String generateReport();
}
