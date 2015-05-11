package de.leipzig.htwk.searchApi;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @Autor Hendrik Sawade
 */
public class PhantomjsDriver {

    /**
     * Pfad zu Phantomjs
     */
    static String PHANTOMJS = "lib/phantomjsLinux";

    /**
     * unitDriver lädt die Engerine für die Websuche.
     */
    private PhantomJSDriver driver;

    private void driverSetUp() {
        //Create instance of PhantomJS driver
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,PHANTOMJS);
        driver = new PhantomJSDriver(capabilities);
    }
}
