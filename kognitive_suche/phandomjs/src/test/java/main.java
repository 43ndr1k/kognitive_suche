/**
 * Created by hendrik on 08.05.15.
 * https://github.com/detro/ghostdriver
 * http://www.appneta.com/blog/automated-testing-java/
 *
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class main {

    public static void main(String[] args) {

        //Create instance of PhantomJS driver
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"lib/phantomjs");
        PhantomJSDriver driver = new PhantomJSDriver(capabilities);

        driver.get("http://duckduckgo.com/html/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1&q=ente");
        // unitDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //unitDriver.get("https://duckduckgo.com/?kah=dk-da&kl=de-de&kad=de_DE&kaj=m&k1=-1&q=ente");
/*
        Sendet die Suchanfrage und klickt auf den Suchbutton
        WebElement query = unitDriver.findElement(By.name("q"));
        query.sendKeys("ente");
        query.submit();*/


        String domainName = driver.getTitle();
        System.out.println("Domain name is " + domainName);

        int z=0;
        String zeile = "n";
        do {

            if (zeile.equals("j")) {

                for (int a =0; a < z;a++) {
                    WebElement next = driver.findElement(By.className("navbutton"));
                    next.click();
                }


            }
            List<WebElement> test2 = driver.findElements(By.className("large"));
            List<WebElement> test3 = driver.findElements(By.className("url"));
            List<WebElement> test4 = driver.findElements(By.className("snippet"));

            for (int i = 0; i < test2.size(); i++) {
                System.out.println(test2.get(i).getText().toString());
                System.out.println(test3.get(i).getText().toString());
                System.out.println(test4.get(i).getText().toString());
                System.out.println("_________________________________");


            }
            System.out.println("########################################################################################");

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Geben Sie etwas ein: ");

            try {
                zeile = console.readLine();
                z++;
            } catch (IOException e) {
                // Sollte eigentlich nie passieren
                e.printStackTrace();
            }


        } while (zeile.equals( "j"));


    }
}
