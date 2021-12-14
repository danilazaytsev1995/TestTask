package DanilaZaytsev.TestTask.Application;

import DanilaZaytsev.TestTask.XmlDom.XmlWriter;
import ch.qos.logback.classic.Logger;
import liquibase.pro.packaged.X;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;


@SpringBootApplication
public class XmlDomConsoleApplication implements CommandLineRunner {

    private final XmlWriter xmlWriter;
    @Autowired
    public XmlDomConsoleApplication(XmlWriter xmlWriter) {
        this.xmlWriter = xmlWriter;
    }

    private static Logger LOG = (Logger) LoggerFactory
            .getLogger(XmlDomConsoleApplication.class);

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(XmlDomConsoleApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws ParserConfigurationException, TransformerException {
        LOG.info("EXECUTING : command line runner");
        xmlWriter.writeDbInXml();

        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }
}
