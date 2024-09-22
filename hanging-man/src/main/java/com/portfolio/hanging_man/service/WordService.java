package com.portfolio.hanging_man.service;
/** ?  ----- WordService -----  */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.FileReader;

import java.io.File;

@Service
public class WordService {

    public record Result(String category, String word) {}
    public static String getWord(String language){
        if (language.equals("TR")){
            // Proje kök dizinini çalışma dizininden dinamik olarak al
            String projectDir = System.getProperty("user.dir");

            // JSON dosyasının yolunu oluştur
            File TR_jsonFile = new File(projectDir, "src/main/resources/static/wordList_TR.json");

//            // Dosya bulunup bulunmadığını kontrol et
//            if (TR_jsonFile.exists()) {
//                System.out.println("JSON Dosyası bulundu: " + TR_jsonFile.getAbsolutePath());
//            } else {
//                System.out.println("JSON Dosyası bulunamadı.");
//            }
            GameService.Result result = randomWord(String.valueOf(TR_jsonFile));
            GameService.randomCategory = result.category();
            GameService.randomWord = result.word();
            return GameService.randomWord;
        }
        if (language.equals("ENG")){
            String projectDir = System.getProperty("user.dir");
            File ENG_jsonFile = new File(projectDir, "src/main/resources/static/wordList_ENG.json");

            GameService.Result result = randomWord(String.valueOf(ENG_jsonFile));
            GameService.randomCategory = result.category();
            GameService.randomWord = result.word();
            return GameService.randomWord;
        }
        else {
            System.out.println("WRONG LANGUAGE MODEL (TR / ENG)");
            return "WRONG LANGUAGE MODEL (TR / ENG)";
        }
    }

    public static GameService.Result randomWord(String jsonFilePath){
        String randomWord="";
        String randomCategory = "";
        Random random = new Random();
        try (FileReader reader = new FileReader(jsonFilePath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, List<String>>>() {}.getType();
            Map<String, List<String>> categories = gson.fromJson(reader, type);

            // Kategorilerden rastgele birini seç
            List<String> keys = categories.keySet().stream().toList();
            randomCategory = keys.get(random.nextInt(keys.size()));

            // Seçilen kategoriden rastgele bir kelime seç
            List<String> words = categories.get(randomCategory);
            randomWord = words.get(random.nextInt(words.size()));

//            System.out.println(randomCategory + ": "+ randomWord);
        }catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("result: "+new Result(randomCategory,randomWord));
        return new GameService.Result(randomCategory,randomWord);
    }
}
