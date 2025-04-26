package com.rootcode.library.utils;

import java.util.ResourceBundle;


public class MessageUtils {


    /**
     * Gets message from properties file and returns it.
     *
     * @param key Property key.
     * @return String.
     */
    public static String getMessage(String key) {
        return ResourceBundle.getBundle("properties/message").getString(key);
    }
}
