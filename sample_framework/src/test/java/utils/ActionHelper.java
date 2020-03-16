package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class ActionHelper {
    AppiumDriver driver;

    public ActionHelper(AppiumDriver appiumDriver) throws IOException, InterruptedException {
        this.driver = appiumDriver;
    }

    //Drop Down
    public static void typeTextToGetDropdown(
            WebElement locatorForTypingTxtInTxtBx, String textToTypeInTxtBx,
            List<WebElement> locatorDropdown) {
        locatorForTypingTxtInTxtBx.clear();
        String append[] = textToTypeInTxtBx.split("");

        for (int i = 0; i < append.length; i++) {
            try {
                Thread.sleep(1000);
                locatorForTypingTxtInTxtBx.sendKeys(append[i]);

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<WebElement> options = locatorDropdown;
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(textToTypeInTxtBx)) {
                System.out.println(option.getText());
                option.click();
                break;
            }

        }
    }

    //Scrolling vertical
    public static void getverticalScroll(AppiumDriver<MobileElement> driver,
                                         double startPercentage, double endPercentage, String Swip) {
        try {

            Dimension size = driver.manage().window().getSize();
            System.out.println(size);
            int width = (int) (size.width / 2);
            System.out.println("width of screen : " + width);
            System.out.println(size.height + "   " + size.height / 2);
            int startpoint = (int) (size.getHeight() * startPercentage);
            int endpoint = (int) (size.getHeight() * endPercentage);
            if (Swip == "down") {
                new TouchAction(driver).press(point(width, startpoint))
                        .moveTo(point(width, endpoint)).release()
                        .perform();
                System.out.println("Scrolling down*********");
            } else if (Swip == "up") {
                new TouchAction(driver).press(point(width, endpoint))
                        .moveTo(point(width, startpoint)).release().perform();
                System.out.println("Scrolling up********");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //Click Method
    public void click(WebElement webElement) {
        webElement.click();
    }

    //Clear Method
    public void clear(WebElement webElement) {
        webElement.clear();
    }

    //Write Text
    public void writeText(WebElement webElement, String text) {
        webElement.click();
        webElement.clear();
        //TODO: I was unable to enter keyword using default send method at some pages. i.e. Login Page
        //webElement.sendKeys(text);
        driver.getKeyboard().sendKeys(text);
        driver.hideKeyboard();
    }

    //Read Text
    public String readText(WebElement webElement) {
        return webElement.getText();
    }

    //Explicit Wait
    public void waitUntilElementVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    //Explicit
    public void waitUntilElementToBeClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    //Swipe Right to Left
    public void swipeRightToLeft(int count) throws Exception {
        for (int i = 1; i <= count; i++) {
            {
                TouchAction ts = new TouchAction(driver);
                Thread.sleep(3000);
                Dimension size = driver.manage().window().getSize();
                int starty = size.height / 2;
                int startx = (int) (size.width * 0.90);
                int endx = (int) (size.width * 0.10);
                ts.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
                        .moveTo(PointOption.point(endx, starty)).release();
                ts.perform();
            }
        }

    }

    //Swipe the to one Element from Another Element
    public void swipeByElements(AndroidElement startElement, AndroidElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);

        new TouchAction(driver)
                .press(point(startX, startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();
    }

    public void swipeUp() throws Exception {

        {
            TouchAction ts = new TouchAction((PerformsTouchActions) driver);
            Thread.sleep(3000);
            Dimension size = driver.manage().window().getSize();
            int starty = (int) (size.height * 0.90);
            int endy = (int) (size.height * 0.10);
            int startx = size.width / 2;
            ts.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
                    .moveTo(PointOption.point(startx, endy)).release();
            ts.perform();
        }
    }

    public void smallSwipeUp() throws Exception {

        {
            TouchAction ts = new TouchAction((PerformsTouchActions) driver);
            Thread.sleep(3000);
            Dimension size = driver.manage().window().getSize();
            int starty = (int) (size.height * 0.70);
            int endy = (int) (size.height * 0.40);
            int startx = size.width / 2;
            ts.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
                    .moveTo(PointOption.point(startx, endy)).release();
            ts.perform();
        }
    }

    public void swipeDown() throws Exception {

        {
            TouchAction ts = new TouchAction((PerformsTouchActions) driver);
            Thread.sleep(3000);
            Dimension size = driver.manage().window().getSize();
            int endy = (int) (size.height * 0.90);
            int starty = (int) (size.height * 0.10);
            int startx = size.width / 2;
            ts.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
                    .moveTo(PointOption.point(startx, endy)).release();
            ts.perform();
        }
    }

    public void smallSwipeDown() throws Exception {

        {
            TouchAction ts = new TouchAction((PerformsTouchActions) driver);
            Thread.sleep(3000);
            Dimension size = driver.manage().window().getSize();
            int endy = (int) (size.height * 0.70);
            int starty = (int) (size.height * 0.40);
            int startx = size.width / 2;
            ts.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
                    .moveTo(PointOption.point(startx, endy)).release();
            ts.perform();
        }
    }


}

