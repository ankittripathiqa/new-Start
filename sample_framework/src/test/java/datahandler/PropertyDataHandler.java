package datahandler;

import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyDataHandler {
    public static Properties config = null;

    public static Properties loadconfigurationfile(String name) {
        try {
            config = new Properties();
            String filePath = System.getProperty("user.dir")
                    + "//src//test//resources//configs//" + name;
            System.out.println("Properties File" + filePath);
            if (isFilePath(filePath)) {
                FileInputStream fileinp = new FileInputStream(filePath);
                config.load(fileinp);
                System.out.println("Fetching data from the path" + filePath
                        + name);
            } else {
                System.out.println("Not able to fetch data from config property file:"
                        + name);
            }
        } catch (IOException strInputOutputException) {
            System.out
                    .println("IOException occured in loading config configuration file"
                            + name);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Assert.fail("Some Exception occured in loading config properties file");
        }
        return config;
    }

    public static boolean isFilePath(String filePathvalue) {
        try {
            if ((filePathvalue).trim() == "") {
                return false;
            } else {
                File fs = new File(filePathvalue);
                boolean value = fs.exists();
                if (value) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception ex) {
            return false;
        }
    }
}
