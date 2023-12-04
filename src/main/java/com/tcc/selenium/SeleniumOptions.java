package com.tcc.selenium;


import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;

public class SeleniumOptions {

    public ChromeOptions createChromeOptions() {

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "arquivos" + File.separator);

        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return chromeOptions;
    }

    public static void defineBrowserDriver() {
        if (Boolean.FALSE.equals(System.getProperty("os.name").toUpperCase().contains("LINUX"))) {
            String path = System.getProperty("user.dir") + File.separator;
            path = path.concat("driver\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver",path);
        }
        else{
            String path = System.getProperty("user.dir") + File.separator;
            path = path.concat("driver/chromedriver");
            System.setProperty("webdriver.chrome.driver",path);
        }
    }
}

