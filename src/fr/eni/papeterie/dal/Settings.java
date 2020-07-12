package fr.eni.papeterie.dal;

import java.util.Properties;

public class Settings {
    private static Properties properties;
    static {
        try {
            properties = new Properties();
            properties.load(Settings.class.getResourceAsStream("settings.properties"));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     *
     * @param key String | Intitulé de la propriété.
     * @return String | Valeur de la propriété.
     */
    public static String getProperty(String key){
        return properties.getProperty(key,null);
    }
}
