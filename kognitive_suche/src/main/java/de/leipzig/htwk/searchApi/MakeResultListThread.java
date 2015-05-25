package de.leipzig.htwk.searchApi;

/**
 * @Autor Hendrik Sawade
 */


/**
 * Naut ein Result Objekt. jedes Objekt wird in einem Thread erstellt.
 */
public class MakeResultListThread extends Thread{

    private String title = null;
    private String link = null;
    private String description = null;
    private SearchApi sa;

    public MakeResultListThread(String title, String description, String link,SearchApi sa) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.sa = sa;
    }


    @Override
    public void run()
    {
        Result r = new Result( this.title, this.description, this.link);
        sa.setResult(r);
    }
}

