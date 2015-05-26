package de.leipzig.htwk.searchApi;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;

/**
 * @Autor Hendrik Sawade
 */


/**
 * Startet den Phantomjs driver, mit den richtigen Konfigurationen.
 * Beinhaltet den ghostdriver
 */
public class PhantomjsDriver {

    /**
     * Pfad zu Phantomjs
     */
    String PHANTOMJS;


    /**
     * unitDriver lädt die Engerine für die Websuche.
     */
    private PhantomJSDriver driver;

    //File f = new File((getClass().getResource("/lib/phantomjsLinux").getPath()));

    public PhantomjsDriver() {
        //Create instance of PhantomJS driver
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,getPHANTOMJSPhad());
        driver = new PhantomJSDriver(capabilities);

    }

    /**
     * gibt die Instance des phantomjs driver zurück.
     * @return driver
     */
    public PhantomJSDriver getDriver() {
        return driver;
    }

    /**
     * Ermittelt das Betriebsystem und gibt den Pfad zu phantomjs zurück.
     * @return var String mit dem Pfad zu phantomjs
     */
    private String getPHANTOMJSPhad() {
        String phantomjs[] = {"lib/phantomjsLinux", "lib/phantomjsMac", "lib/phantomjsWin.exe"};


        String os = "os.name";

        Properties prop = System.getProperties();
        String system = prop.getProperty(os);
        System.out.println( "Betriebssystem: " + system);

        String var = null;

        switch (system) {
            case "Linux":
                var = phantomjs[0];
                break;
            case "MAC OS X":
                var = phantomjs[1];
                break;
           default:
                var = phantomjs[2];
                break;

        }

        return var;
    }
}
