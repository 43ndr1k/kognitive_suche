package de.leipzig.htwk.searchApi;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

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
    String pfad;
    /**
     * unitDriver lädt die Engerine für die Websuche.
     */
    private PhantomJSDriver driver;

    public PhantomjsDriver(String pfad) {
        this.pfad = pfad;
        //Create instance of PhantomJS driver
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,pfad);
        driver = new PhantomJSDriver(capabilities);

    }

    /**
     * gibt die Instance des phantomjs driver zurück.
     * @return driver
     */
    public PhantomJSDriver getDriver() {
        return driver;
    }
}



