package com.tokuden.sql;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class load_locations {
    public static void main(String args[]) throws Exception{
        ConfigLoader loader = new ConfigLoader();
        
        String url = loader.getUrl();
        String username = loader.getUsername();
        String password = loader.getPassword();
        
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            createLocationsTable(conn);
            
            writeLocationsTable(conn);
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
    }

    private static void writeLocationsTable(Connection conn) throws ParserConfigurationException, SQLException, DOMException, IOException, SAXException {
        var insertSql = "INSERT INTO Locations (Location_name) VALUES (?)";
        var preparedStatement = conn.prepareStatement(insertSql);
        
        InputStream inputStream = load_locations.class.getClassLoader().getResourceAsStream("com/tokuden/xml/locations.xml");
        
        if (inputStream == null){
            throw new IOException("locations.xml not found");
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(inputStream);
        
        NodeList locationsList = doc.getElementsByTagName("location");
        
        for (int temp = 0; temp < locationsList.getLength(); temp++){
            Node location_node = locationsList.item(temp);
            if (location_node.getNodeType() == Node.ELEMENT_NODE){
                Element location_element = (Element) location_node;
                String location = (String) location_element.getTextContent();
                
                preparedStatement.setString(1, location);
                preparedStatement.execute();
            }
        }
        
        System.out.println("Successfully loaded table with data!");
    }

    private static void createLocationsTable(Connection conn) throws SQLException, IOException {
        InputStream sqlStream = load_locations.class.getClassLoader().getResourceAsStream("com/tokuden/sql/createLocations.sql");
        
        if (sqlStream == null) {
        throw new IOException("SQL file not found!");
        }
    
        // Read file content
        String createLocationsStatement = new String(sqlStream.readAllBytes());

        // Execute
        var statement = conn.createStatement();
        
        statement.execute(createLocationsStatement);
        System.out.println("Locations TABLE CREATED!");
    }
}
