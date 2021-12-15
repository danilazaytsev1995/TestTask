package DanilaZaytsev.TestTask.XmlDom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class XmlWriter {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public XmlWriter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void writeDbInXml() throws ParserConfigurationException, TransformerException {

        List<DepartmentInfo> dbList = jdbcTemplate.query("select * from dep_table",
                 new RowMapper<DepartmentInfo>() {
            @Override
            public DepartmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new DepartmentInfo(rs.getString("dep_code"),
                        rs.getString("dep_job"), rs.getString("description"));
            }
        });

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("dep_db");
        doc.appendChild(rootElement);

        for (int i = 0; i < dbList.size(); i++) {
            Element dep_table = doc.createElement("dep_table");
            rootElement.appendChild(dep_table);
            dep_table.setAttribute("dep_code", dbList.get(i).getDepCode());
            dep_table.setAttribute("dep_job", dbList.get(i).getDepJob());
            dep_table.setAttribute("description", dbList.get(i).getDescription());
        }

        try (FileOutputStream output =
                     new FileOutputStream("C:/Users/SigmaST3/Desktop/DanilaStudies/example777.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
