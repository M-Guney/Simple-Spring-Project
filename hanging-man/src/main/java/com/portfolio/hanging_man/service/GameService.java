package com.portfolio.hanging_man.service;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service  // annonation = (tr translate) dip not
public class GameService {
    static String randomWord = "";
    static String randomCategory = "";
    public record Result(String category, String word) {}
    int tryremaining =0;
    char[] wordChracters;
    char[] dashedWord;
//    private String[] Words = {"hello world","father","mother","brother","pen","open","close","book","light","dark","library"};
    Random random = new Random();

    public GameService(){
        newWord("ENG"); // Default Language Model: English
    }

    public void newWord(String languageModel){
        WordService.getWord(languageModel);

        System.out.println("\nRandomly chosen word is: " + randomWord + "\n");
        wordChracters = new char[randomWord.length()];//Example hello world
        dashedWord = new char[randomWord.length()];
        initializeDashes();

        if (randomWord !=null && !randomWord.isEmpty()){
            tryremaining = randomWord.length() - randomWord.length()/2;
        }
    }

    private void initializeDashes() {
        // Convert non-space characters to dashes
        for (int i = 0; i < randomWord.length(); i++) {
            if (randomWord.charAt(i) == ' ') {
                dashedWord[i] = ' '; // Keep spaces as they are
            } else {
                dashedWord[i] = '_'; // Replace other characters with dashes
            }
        }
    }

    @Override
    public String toString() {
        return randomWord;
    }
    public String toDashes(){
        return String.valueOf(dashedWord);
    }
    public int getTryremaining(){
        return tryremaining;
    }
    public String getCategory(){
        return randomCategory;
    }

    public boolean addGuess(char guessedChar){
        boolean allfound = false;
        boolean found = false;
        boolean alreadyGuessed = false;

        // Zaten tahmin edilmiş bir karakter mi kontrol et
        for (int k = 0; k < randomWord.length(); k++) { //randomWord.length() -1 durumundaydı
            if (dashedWord[k] == guessedChar) {
                alreadyGuessed = true;
                break; // Karakter zaten tahmin edildiyse döngüyü kır
            }
        }

        if (!alreadyGuessed) {

            for (int k = 0; k < randomWord.length(); k++) {
                if (randomWord.charAt(k) == guessedChar) {
                    dashedWord[k] = guessedChar; // Dash ile karakteri değiştir
                    found = true;
                }
                else if (Character.isUpperCase(randomWord.charAt(k))){
                    if (randomWord.charAt(k) == Character.toUpperCase(guessedChar)) {
                        dashedWord[k] = Character.toUpperCase(guessedChar); // Dash ile karakteri değiştir
                        found = true;
                    }
                }
                else if (Character.isLowerCase(randomWord.charAt(k))){
                    if (randomWord.charAt(k) == Character.toLowerCase(guessedChar)) {
                        dashedWord[k] = Character.toLowerCase(guessedChar); // Dash ile karakteri değiştir
                        found = true;
                    }
                }
            }

        if (found) {
            System.out.println("Correct guess: " + guessedChar);

        } else {
            System.out.println("Incorrect guess: " + guessedChar);
            if (!alreadyGuessed){
                tryremaining -= 1;
            }
        }
        System.out.println("current try remains : "+tryremaining);
        System.out.println("Current state: " + String.valueOf(dashedWord));

        if (String.valueOf(dashedWord).equals(randomWord)){
            allfound = true;
            }else {
            System.out.println("Character '" + guessedChar + "' has already been guessed.");
            }
        }
            System.out.println("all word are known : "+allfound);
        return allfound;
        }
}
