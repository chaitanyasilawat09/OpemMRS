package utility;

import java.io.IOException;
import java.util.Properties;

public enum ApplicationProperties {

    INSTANCE;

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public String getUrl() {
        return properties.getProperty("url");
    }
    public String getPageTitle() {
        return properties.getProperty("pageTitle");
    }

    public String getUserName() {
        return properties.getProperty("userName");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getGivenName() {
        return properties.getProperty("givenName");
    }

    public String getFamilyName() {
        return properties.getProperty("familyName");
    }

    public String getGender() {
        return properties.getProperty("gender");
    }

    public String getBirthDate() {
        return properties.getProperty("birthDate");
    }
    public String getBirthMonth() {
        return properties.getProperty("birthMonth");
    }
    public String getBirthYear() {
        return properties.getProperty("birthYear");
    }
    public String getAddress1() {
        return properties.getProperty("address1");
    }
    public String getAddress2() {
        return properties.getProperty("address2");
    }
    public String getCity() {
        return properties.getProperty("city");
    }
    public String getState() {
        return properties.getProperty("state");
    }
    public String getCountry() {
        return properties.getProperty("country");
    }
    public String getPhoneNo() {
        return properties.getProperty("phoneNo");
    }
    public String getRelationShip() {
        return properties.getProperty("relationShip");
    }
    public String getRelativeName() {
        return properties.getProperty("relativeName");
    }
    public String getPostalCode() {
        return properties.getProperty("postalCode");
    }

}
