package org.example.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;  // Ajoutez ceci
import java.util.List;

public class GameMBTIScraper {

    public static void scrapeToCsv(String url) {
        // Configurez le chemin de ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");


        // Initialiser le navigateur avec Selenium
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        // Utiliser Duration pour le délai d'attente
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Modifier ici

        try (FileWriter csvWriter = new FileWriter("src/main/java/org/example/models/games_mbti.csv")) { // Chemin relatif

            // Écrire l'en-tête du fichier CSV
            csvWriter.append("Nom du jeu,Type MBTI\n");

            // Attendez la visibilité des éléments contenant les informations de chaque jeu
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-1fe0c1")));

            // Récupérer tous les blocs de jeux
            List<WebElement> gameBlocks = driver.findElements(By.cssSelector(".css-1fe0c1"));

            for (WebElement gameBlock : gameBlocks) {
                // Récupérer le nom du jeu et le type MBTI
                WebElement mbtiElement = gameBlock.findElement(By.cssSelector("h2.chakra-heading.css-1brxr6x"));
                WebElement gameElement = gameBlock.findElement(By.cssSelector("h2.chakra-heading.css-zzio9a"));

                String mbti = mbtiElement.getText();
                String gameName = gameElement.getText();

                // Écrire dans le fichier CSV
                csvWriter.append(gameName).append(",").append(mbti).append("\n");
            }

            System.out.println("Données enregistrées dans games_mbti.csv");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
