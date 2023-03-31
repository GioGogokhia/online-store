package by.issoft.store.xmlParser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {
    public static Map<String, String> getSortingMap(){
        Map<String, String> sortingMap = new LinkedHashMap<>();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File("JavaSchool/store/src/main/resources/config.xml"));

            NodeList sortingList = document.getElementsByTagName("sort").item(0).getChildNodes();

            for(int i = 0; i < sortingList.getLength(); i++){

                if (sortingList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    sortingMap.put(sortingList.item(i).getNodeName(), sortingList.item(i).getTextContent());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return sortingMap;
    }

}
