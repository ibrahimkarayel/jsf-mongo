package com.pironix.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author ikarayel
 */
public class ApplicationUtilities {
    static {

        Properties properties = new Properties();
        try {
            properties.load(ApplicationUtilities.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read application.properties", e);
        }
    }

}
