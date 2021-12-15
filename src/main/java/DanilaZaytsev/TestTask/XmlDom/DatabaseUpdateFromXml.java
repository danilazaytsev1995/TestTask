package DanilaZaytsev.TestTask.XmlDom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

@Component
public class DatabaseUpdateFromXml {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public DatabaseUpdateFromXml(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static ArrayList<DepartmentInfo> departmentInfos = new ArrayList<>();

    private static final String DEPTABLE = "dep_table";

    public void DbUpdate() throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("C:/Users/SigmaST3/Desktop/DanilaStudies/example2.xml"));

        collectInformation(document, DEPTABLE);
        jdbcTemplate.execute("DELETE FROM dep_table");
        for (int i = 0; i < departmentInfos.size(); i++) {

            jdbcTemplate.update("INSERT INTO dep_table (dep_code, dep_job, description) VALUES(?, ?, ?);",
                    departmentInfos.get(i).getDepCode(),
                    departmentInfos.get(i).getDepJob(),
                    departmentInfos.get(i).getDescription());
        }
    }

    private static void collectInformation(Document document, final String element) {
        NodeList elements = document.getElementsByTagName(element);

        for (int i = 0; i < elements.getLength(); i++) {
            NamedNodeMap attributes = elements.item(i).getAttributes();
            String depCode = attributes.getNamedItem("dep_code").getNodeValue();
            String depJob = attributes.getNamedItem("dep_job").getNodeValue();
            String description = attributes.getNamedItem("description").getNodeValue();
            departmentInfos.add(new DepartmentInfo(depCode, depJob, description));
        }
    }
}
