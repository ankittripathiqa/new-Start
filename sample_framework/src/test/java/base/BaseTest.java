package base;

import datahandler.JsonDataHandler;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import utils.AutomationConstants;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import static datahandler.PropertyDataHandler.loadconfigurationfile;

public class BaseTest {

    public static String MobileDeviceName;
    public static AppiumDriver driver;
    public static Properties config = null;
    public static String dateName = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa").format(new Date());
    static AppiumDriverLocalService service;
    static String service_url;
    public JSONObject json;

    WebDriverWait wait;

    public BaseTest() throws IOException {
    }

    @BeforeMethod
    public void initializingNewSession() throws IOException, InterruptedException {

        config = loadconfigurationfile(AutomationConstants.CONFIG_FILE);
        MobileDeviceName = config.getProperty("Device");

        service = AppiumDriverLocalService.buildService(
                new AppiumServiceBuilder()
                        .withIPAddress("127.0.0.1")
                        .withArgument(() -> "--log-timestamp")
                        .withArgument(() -> "--local-timezone")
                        .usingPort(4723)
                        .usingDriverExecutable(new File(config.getProperty("node_path")))
                        .withAppiumJS(new File(config.getProperty("appium_path")))
        );

        service.start();

        Thread.sleep(5000);
        service_url = service.getUrl().toString();

        //Desired Capabilities for Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        JSONObject capabilityObject = JsonDataHandler.getJsonObject(AutomationConstants.DEVICE_CAPABILTIES);
        System.out.println(MobileDeviceName);

        Iterator i = ((JSONArray) capabilityObject.get(MobileDeviceName)).iterator();
        while (i.hasNext()) {
            JSONObject capability = (JSONObject) i.next();
            capabilities.setCapability((String) capability.get("caps"), (String) capability.get("value"));
        }

        driver = new AppiumDriver(new URL(service_url), capabilities);
    }

    @AfterClass(description = "Class Level Teardown!")
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
        Thread.sleep(10000);
        service.stop();
    }
}