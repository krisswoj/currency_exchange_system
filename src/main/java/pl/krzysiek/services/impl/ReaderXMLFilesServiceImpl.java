package pl.krzysiek.services.impl;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pl.krzysiek.services.ReaderXMLFilesService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderXMLFilesServiceImpl implements ReaderXMLFilesService {

    @Override
    public List<ArrayList<String>> readXMLFiles(String xmlPath, String xmlID) {

        List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

        try {

            File fXmlFile = new File(xmlPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName(xmlID);

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                ArrayList<String> pozycja = new ArrayList<String>();

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;


                    if (eElement.getElementsByTagName("fromCurrency").getLength() != 0) {
                        pozycja.add(eElement.getElementsByTagName("fromCurrency").item(0).getTextContent());
                    }

                    if (eElement.getElementsByTagName("toCurrency").getLength() != 0) {
                        pozycja.add(eElement.getElementsByTagName("toCurrency").item(0).getTextContent());
                    }

                    list.add(pozycja);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (list);
    }
}