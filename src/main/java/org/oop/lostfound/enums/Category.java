package org.oop.lostfound.enums;

public enum Category { ELEKTRONIK, DOKUMEN, ALAT_TULIS, LAINNYA, OTHER;

     public boolean equalsIgnoreCase(Category other) {
        return other != null && this.name().equalsIgnoreCase(other.name());
    }

    public boolean equalsIgnoreCase(String other) {
        return other != null && this.name().equalsIgnoreCase(other);
    }


    @Override
    public String toString() {
        switch (this) {
            case ELEKTRONIK: return "Elektronik";
            case DOKUMEN: return "Dokumen";
            case ALAT_TULIS: return "Alat Tulis";
            case LAINNYA: return "Lainnya";
            default: return name();
        }
    }
}