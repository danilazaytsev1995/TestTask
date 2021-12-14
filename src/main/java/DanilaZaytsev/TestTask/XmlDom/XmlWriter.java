package DanilaZaytsev.TestTask.XmlDom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

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
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlWriter {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public XmlWriter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void writeDbInXml() throws ParserConfigurationException, TransformerException {

        List<DepartmentInfo> dbList = jdbcTemplate.query("select * from dep_table", new RowMapper<DepartmentInfo>() {
            @Override
            public DepartmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new DepartmentInfo(rs.getInt("id"), rs.getString("dep_code"),
                        rs.getString("dep_job"), rs.getString("description"));
            }
        });

        // List<String> dbList = jdbcTemplate.queryForList("select * from dep_table", String.class);

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();

        for (int i = 0; i < dbList.size(); i++) {
            System.out.println(dbList.get(i));
        }

        /*Element rootElement = doc.createElement("company");
        doc.appendChild(rootElement);

        doc.createElement("staff");
        rootElement.appendChild(doc.createElement("staff"));*/

        //...create XML elements, and others...

        // write dom document to a file
        try (FileOutputStream output =
                     new FileOutputStream("C:/Users/SigmaST3/Desktop/DanilaStudies/example1.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // write doc to output stream
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
