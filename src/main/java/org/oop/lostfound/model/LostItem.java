package org.oop.lostfound.model;

import java.time.LocalDate;
import org.oop.lostfound.enums.Category;

public class LostItem extends Item implements IReportable {
    
    public LostItem(int id, String name, String description, String location, Category category, String contact, String imageUrl, LocalDate date) {
        super(id, name, description, location, category, contact, imageUrl, date);
    }

    
    @Override
    public String generateReport() {
        return "Laporan Kehilangan: " + getName() + " di lokasi " + getLocation();
    }

    @Override
    public String getDetails() {
        return "[LOST] " + getName() + " - " + getDescription();
    }

    public void setDetails(String name, String description, String location, Category category, String contact, String imageUrl, LocalDate date)
    {
        setName(name);
        setDescription(description);
        setLocation(location);
        setCategory(category);
        setContact(contact);
        setImageUrl(imageUrl);
        setDate(date);
    }
    public int getId() {
        return super.getId();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getName() {
        return super.getName();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    public String getDescription() {
        return super.getDescription();
    }

    public void setLocation(String location) {
        super.setLocation(location);
    }

    public String getLocation() {
        return super.getLocation();
    }

    public void setCategory(Category category) {
        super.setCategory(category);
    }

    public Category getCategory() {
        return super.getCategory();
    }

    public void setContact(String contact) {
        super.setContact(contact);
    }

    public String getContact() {
        return super.getContact();
    }

    public void setImageUrl(String imageUrl) {
        super.setImageUrl(imageUrl);
    }

    public String getImageUrl() {
        return super.getImageUrl();
    }

    public void setDate(LocalDate date) {
        super.setDate(date);
    }

    public LocalDate getDate() {
        return super.getDate();
    }


    
}
