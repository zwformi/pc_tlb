package com.yunrer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Created by bill on 2017/4/6.
 */
public class PropertiesReadUtil {
    private Properties reader;
    public PropertiesReadUtil(String filePath) throws IOException {

        InputStream stream;
        stream=PropertiesReadUtil.class.getResourceAsStream(filePath);
        reader=new Properties();
        reader.load(stream);
        stream.close();
    }
    public Iterator<String> getKeysIterator(){
        return reader.stringPropertyNames().iterator();
    }

    public Set<String> getKeysSet(){
        return reader.stringPropertyNames();
    }

    public String getProperty(String key){
        return reader.getProperty(key);
    }

}