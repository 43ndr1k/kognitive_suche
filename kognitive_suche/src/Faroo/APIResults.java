package Faroo;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by hendrik on 11.12.14.
 */
public class APIResults {

    private ArrayList<Result> resultsList =null;



    /**
     * ArrayList mit den Suchergebnissen wird angefordert.
     */
    public APIResults(ArrayList<Result> results){
        this.resultsList = results;

    }

    /**
     * Es wird eie ArrayListe mit den Suchergebnissenzurück gegeben.
     * @return resultsList
     */
    public ArrayList<Result> getResultsList() {
        return resultsList;
    }

    public int getResultCount(){
        return resultsList.size();
    }




}
