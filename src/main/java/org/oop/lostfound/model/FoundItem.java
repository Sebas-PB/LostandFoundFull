package org.oop.lostfound.model;

import java.time.LocalDate;
import org.oop.lostfound.enums.Category;

public class FoundItem extends Item implements IReportable {

    public FoundItem() {
        super(0, "", "", "", null, "", "", null);
    }
    public FoundItem(int id, String name, String description, String location, Category category, String contact, String imageUrl, LocalDate date) {
        super(id, name, description, location, category, contact, imageUrl, date);
    }

    @Override
    public String generateReport() {
        return "Laporan Penemuan: " + getName() + " ditemukan di lokasi " + getLocation();
    }

    @Override
    public String getDetails() {
        return "[FOUND] " + getName() + " - " + getDescription();
    }

    public void setDetails(String name, String description, String location, Category category, String contact, String imageUrl, LocalDate date) {
        setName(name);
        setDescription(description);
        setLocation(location);
        setCategory(category);
        setContact(contact);
        setImageUrl(imageUrl);
        setDate(date);
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
