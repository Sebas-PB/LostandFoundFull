# Lost and Found System

Aplikasi Lost and Found berbasis Java yang membantu pengguna untuk melaporkan, mencari, dan mengklaim barang hilang atau ditemukan. Proyek ini menggunakan JavaFX untuk antarmuka pengguna dan menerapkan arsitektur modular dengan pemisahan antara controller, model, service, dan DAO.

## Fitur Utama
- Registrasi dan login pengguna
- Pelaporan barang hilang dan ditemukan
- Daftar barang hilang dan ditemukan
- Klaim barang oleh pemilik
- Laporan aktivitas untuk admin dan user
- Statistik jumlah barang dan klaim

## Struktur Proyek
```
src/
  main/
    java/
      org/
        oop/
          lostfound/
            MainApp.java
            config/
            controller/
            dao/
            enums/
            model/
            service/
    resources/
      org/
        oop/
          image/         # Gambar ikon aplikasi
          lostfound/     # File FXML tampilan
          style/         # CSS styling
```

## Cara Build & Run

### Prasyarat
- Java 11 atau lebih baru
- Maven

### Build
```sh
mvnw clean package
```

### Jalankan Aplikasi
```sh
mvnw javafx:run
```

## Kontributor
- [Nama Anda]

## Lisensi
Proyek ini menggunakan lisensi MIT.
