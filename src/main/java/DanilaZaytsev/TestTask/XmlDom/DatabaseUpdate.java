package DanilaZaytsev.TestTask.XmlDom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Scanner;

public class DatabaseUpdate {

    // private static final String FILENAME = "C:/Users/SigmaST3/Desktop/DanilaStudies/example.xml";

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        String path = console.nextLine();
        String FILENAME = path;
        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            // optional, but recommended
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <staff>
            NodeList list = doc.getElementsByTagName("dep_table");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    // String id = element.getAttribute("id");

                    // get text
                    String dep_code = element.getElementsByTagName("dep_code").item(0).getTextContent();
                    String dep_job = element.getElementsByTagName("dep_job").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();

                    // NodeList salaryNodeList = element.getElementsByTagName("salary");
                    // String salary = salaryNodeList.item(0).getTextContent();

                    // get salary's attribute
                    // String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                    // System.out.println("Current Element :" + node.getNodeName());
                    // System.out.println("Staff Id : " + id);
                    System.out.println("DepCode : " + dep_code);
                    System.out.println("DepJob : " + dep_job);
                    System.out.println("Description : " + description);
                    // System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

}
