package de.leipzig.htwk.searchApi;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hendrik on 24.05.15.
 */
public class MakeResultListWithThreads extends Thread{

    List<WebElement> titleClassList = null;
    List<WebElement> linkClassList = null;
    List<WebElement> descriptionClassList = null;
    ArrayList<Result> resultList = new ArrayList<Result>();

    int restAnz;

    public MakeResultListWithThreads() {




    }

    public void run()
    {
        makeResultList();

    }


    private void makeResultList() {
        long zstVorher = System.currentTimeMillis();



        for (int i = 0; i < restAnz;i++) {
            this.resultList.add(new Result(
                    this.titleClassList.get(i).getText(),
                    this.descriptionClassList.get(i).getText(),
                    this.linkClassList.get(i).getAttribute("href").toString())
            );
        }

        long zstNachher = System.currentTimeMillis(); // Zeitmessung
        System.out.println("Zeit benötigt: DuckDuckGo Suche Result Liste erstellen: " + ((zstNachher - zstVorher))
            + " millisec");


    }
}

