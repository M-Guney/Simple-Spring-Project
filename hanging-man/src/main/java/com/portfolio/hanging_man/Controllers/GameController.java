package com.portfolio.hanging_man.Controllers;

// Imported from @Contreller shortcut
import com.portfolio.hanging_man.service.GameService;
import com.portfolio.hanging_man.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** if you have an error about 8080 is full (or already you have a process on 8080 port)
 * netstat -ano | findstr :<PORT>
 *
 * Gelen ekranda eğer port 8080 için bir process aktif ise ilgili işlem numarasını görüyorum.
 * taskkill /PID <PID> /F
 *
 * you have to write id on <PID> without<>
 *
 * medium blog about: https://medium.com/@ggl.issoline/windowsta-localhost-ta-bir-ba%C4%9Flant%C4%B1-noktas%C4%B1-port-8080-kullanan-i%C5%9Flemi-iptal-etme-99e2fc3b480
 * */
@Controller
public class GameController {

    @Autowired
    GameService gameService;
    static String language ="ENG";

    @PostMapping("/language-method")
    public String handleLanguageSelection(@RequestParam("selectedLanguage") String currentLanguage, Model model) {
        language=currentLanguage;

        // Oyunun ana sayfasına dön
        return "redirect:/game-home";
    }

    @GetMapping("/game-home")
    public String showGameHomePage(@RequestParam(value="guessedChar", required = false) String guessedChar, Model model){
        System.out.println("* captured guessed character is : "+guessedChar);
        String randomCategory = gameService.getCategory();
        String wordDashes = gameService.toDashes();
        int tryRemaining = gameService.getTryremaining();
        boolean wordKnown = false;

        String randomWord = gameService.toString();
        System.out.println();
        System.out.println(randomWord);

        model.addAttribute("selectedLanguage",language);

        if(guessedChar != null && !guessedChar.isEmpty()){
            wordKnown = gameService.addGuess(guessedChar.charAt(0));
            wordDashes = gameService.toDashes();
            tryRemaining = gameService.getTryremaining();
            if (wordKnown == true) {
                model.addAttribute("completedMessage","Well done! You guessed all the letters correctly.");
                gameService.newWord(language); // if captured guessed character is : null  refresh the word
                randomWord = gameService.toString();
                randomCategory = gameService.getCategory();
                wordDashes = gameService.toDashes();
                tryRemaining = gameService.getTryremaining();
            }
        }
            else if (guessedChar != null && guessedChar.isEmpty()) {
            model.addAttribute("warningMessage", "Please enter a character.");
        }
        else {
            gameService.newWord(language); // if captured guessed character is : null  refresh the word
            randomWord = WordService.getWord(language);
            randomCategory = gameService.getCategory();
            wordDashes = gameService.toDashes();
            tryRemaining = gameService.getTryremaining();

        }
        model.addAttribute("wordToDisplay",randomWord);
        model.addAttribute("wordlenghtDashes",wordDashes);
        model.addAttribute("tryRemains",tryRemaining); //+1
        model.addAttribute("wordCategory",randomCategory);

        return "game-home-page";
    }
    @PostMapping("/refresh-word")
    public String refreshWord(Model model) {
        // Refresh the word and game state
        gameService.newWord(language);
        String randomWord = gameService.toString();
        String randomCategory = gameService.getCategory();
        String wordDashes = gameService.toDashes();
        int tryRemaining = gameService.getTryremaining();

        // Update the model with the new game state
        model.addAttribute("wordToDisplay", randomWord);
        model.addAttribute("wordlenghtDashes", wordDashes);
        model.addAttribute("tryRemains", tryRemaining);
        model.addAttribute("wordCategory", randomCategory);

        // Redirect back to the game page
        return "game-home-page";
    }

}
