package com.SpringMongo.PropertyReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Property Reader class
 * 
 *
 */
public class PropertyReader {

    private final Properties configProp = new Properties();
    private static final Logger logger = Logger.getLogger(PropertyReader.class);

    /**
     * read property file
     * 
     * @param propertyName
     * @param path
     * 
     * @throws IOException
     */
    public PropertyReader(String propertyName, String path) {

        logger.info("In read property method");
        System.out.println("path + propertyName : " + path + propertyName);

        try {
            InputStream in;
            File file;

            if (path.equals("")) {
                in = this.getClass().getClassLoader().getResourceAsStream(propertyName);
            }
            else {
                file = new File(path + propertyName);
                in = new FileInputStream(file);
            }
            configProp.load(in);
        }
        catch (IOException e) {
            logger.error(e);

        }
        logger.info("returning from read property method");
    }

    /**
     * 
     * get property key
     * 
     * @param key
     * @return
     */
    public String getProperty(String key) {
        logger.info("Getting key");
        return configProp.getProperty(key);
    }

    /**
     * Append field name with message
     * 
     * @param key
     * @param fieldName
     * @return
     */
    public String getProperty(String key, String fieldName) {
        return MessageFormat.format(configProp.getProperty(key), fieldName);
    }

      
}
