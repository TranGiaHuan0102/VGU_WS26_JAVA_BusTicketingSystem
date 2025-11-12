package com.database;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.InputStream;

public class ConfigLoader {
    private String url;
    private String username;
    private String password;
    
    public ConfigLoader() {
        loadConfig();
    }
    
    private void loadConfig() {
        try {
            // Load XML from resources folder
            InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("com/tokuden/xml/config.properties.xml");
            
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            
            // Get database element
            NodeList databaseList = doc.getElementsByTagName("database");
            Element database = (Element) databaseList.item(0);
            
            // Extract values
            this.url = database.getElementsByTagName("url").item(0).getTextContent();
            this.username = database.getElementsByTagName("username").item(0).getTextContent();
            this.password = database.getElementsByTagName("password").item(0).getTextContent();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Getters
    public String getUrl() { return url; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
