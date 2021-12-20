package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    Properties prop = new Properties();
    FileInputStream fis = null;
    private String currentDirectory = System.getProperty("user.dir");
    private static String AdminUsername;
    private static String AdminPassword;

    {
        try {
            fis = new FileInputStream(currentDirectory + "\\src\\main\\java\\resources\\Data.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Default admin info stored in data.properties file
    public String getDefaultAdminUsername() {
        String defaultAdminUsername = prop.getProperty("defaultAdminUsername");
        if (defaultAdminUsername != null) return defaultAdminUsername;
        else throw new RuntimeException("Default Admin Username is not specified in properties file");
    }

    public String getDefaultAdminPassword() {
        String defaultAdminPassword = prop.getProperty("defaultAdminPassword");
        if (defaultAdminPassword != null) return defaultAdminPassword;
        else throw new RuntimeException("Default Admin Password is not specified in properties file");
    }

    public void setAdminUsername(String adminUsername) {
        AdminUsername = adminUsername;
    }

    public void setAdminPassword(String adminPassword) {
        AdminPassword = adminPassword;
    }

    public String getAdminUsername() {
        return AdminUsername;
    }

    public String getAdminPassword() {
        return AdminPassword;
    }
/*
    public String getAdminUsername() {
        String adminUsername = prop.getProperty("adminUsername");
        if (adminUsername != null) return adminUsername;
        else throw new RuntimeException("Admin Username is not specified in properties file");
    }

    public String getAdminPassword() {
        String adminPassword = prop.getProperty("adminPassword");
        if (adminPassword != null) return adminPassword;
        else throw new RuntimeException("Admin Password is not specified in properties file");
    }
*/
}
