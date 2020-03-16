package utils;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ValidationHelper {

    public static void elementIsEqual(WebElement actual, String expected) {
        String actualvalue = actual.getText();
        Assert.assertEquals(actualvalue, expected, "Not matching");
    }

    public static void elementIsEqual(String actual, String expected) {
        Assert.assertEquals(actual, expected, "Not matching");
    }


}
