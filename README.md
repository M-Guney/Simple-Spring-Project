# Simple-Spring-Project
I will upload some of my Java Spring projects

![Animation](https://github.com/M-Guney/Simple-Spring-Project/blob/main/Animation.gif?raw=true)

# Hangman Game

Bu proje, kelime tahmin etme oyunu olan Hangman'ın bir Java Spring uygulaması olarak geliştirilmiştir. Kullanıcılar, rastgele seçilen kelimeleri tahmin etmeye çalışırken, kalan tahmin hakları takip edilir.

## Proje Yapısı

- **GameService**: Oyunun mantığını yöneten sınıf.
- **WordService**: Kelime ve kategori seçiminden sorumlu olan sınıf.

## Gereksinimler

- Java 17 veya üstü
- Spring Boot
- Gson kütüphanesi (JSON işleme için)

## Kurulum

1. **Projenizi İndirin**:
   Projeyi GitHub üzerinden indirin veya klonlayın.

   ```bash
   git clone https://github.com/M-Guney/hanging-man.git

2. **Gerekli Kütüphaneleri Yükleyin:**:
   *Gson
   \hanging-man\src\main\resources\static\gson-2.10.1.jar
   Dizindeki jar dısyasını Project Structure kısmından eklemesini yapın
     ya da
   <dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
   </dependency>
