package DanilaZaytsev.TestTask.Controller;

import DanilaZaytsev.TestTask.XmlDom.DatabaseUpdateFromXml;
import DanilaZaytsev.TestTask.XmlDom.XmlWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class XmlController implements CommandLineRunner {

    private final XmlWriter xmlWriter;
    private final DatabaseUpdateFromXml databaseUpdateFromXml;
    @Autowired
    public XmlController(XmlWriter xmlWriter, DatabaseUpdateFromXml databaseUpdateFromXml) {
        this.xmlWriter = xmlWriter;
        this.databaseUpdateFromXml = databaseUpdateFromXml;
    }

    @Override
    public void run(String... args) throws ParserConfigurationException, TransformerException, IOException, SAXException {

        xmlWriter.writeDbInXml();
        databaseUpdateFromXml.DbUpdate();
    }
}
