package ua.com.myhtmlagregatorclient.util;

import ua.com.myhtmlagregatorclient.model.Provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {

    public static Properties getMyProperties() {
        Properties properties2 = new Properties();
        InputStream inputStream = null;

        try {
            String filename = "app.properties";
            inputStream = Provider.class.getClassLoader().getResourceAsStream(filename);
            if (inputStream == null) {
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }

            //load a properties file from class path, inside static method
            properties2.load(inputStream);

            /*//get the property value and print it out
            System.out.println(properties2.getProperty("vacancies.path"));*/

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties2;
    }
}
