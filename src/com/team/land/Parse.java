package com.team.land;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Parse {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("pog.xml");

            String str = "Yesterday in Kazan :";
            int tema;

            NodeList nodeList = doc.getElementsByTagName("FORECAST");

            for(int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                tema = Integer.parseInt(element.getElementsByTagName("TEMPERATURE").item(0).getAttributes().getNamedItem("max").getNodeValue());
                System.out.println(str + tema);
                if(i == 0) {
                    str = "Today in Kazan :";
                }
                if(i == 1) {
                    str = "Tomorow in Kazan :";
                }
                if(i == 2) {
                    str = "Day after tomorrow :";
                }

            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
