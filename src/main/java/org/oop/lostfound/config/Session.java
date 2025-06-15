package org.oop.lostfound.config;

//untuk menyimpan informasi sesi user yang sedang login
public class Session {
    private static int id;
    private static String username;

    // Setter Getter ID dan Username
    public static void setId(int id) { Session.id = id; }
    public static int getId() { return id; }

    public static void setUsername(String username) { Session.username = username; }
    public static String getUsername() { return username; }

    // Hapus data sesi (logout)
    public static void clear() {
        id = 0;
        username = null;
    }
}
