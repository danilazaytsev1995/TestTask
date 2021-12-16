package DanilaZaytsev.TestTask.Controller;

import DanilaZaytsev.TestTask.XmlDom.DatabaseUpdateFromXml;
import DanilaZaytsev.TestTask.XmlDom.XmlWriter;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class XmlDomConsoleApplication implements CommandLineRunner {

    private final XmlWriter xmlWriter;
    private final DatabaseUpdateFromXml databaseUpdateFromXml;

    @Autowired
    public XmlDomConsoleApplication(XmlWriter xmlWriter, DatabaseUpdateFromXml databaseUpdateFromXml) {
        this.xmlWriter = xmlWriter;
        this.databaseUpdateFromXml = databaseUpdateFromXml;
    }

    private static Logger LOG = (Logger) LoggerFactory
            .getLogger(XmlDomConsoleApplication.class);

    @Override
    public void run(String... args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        LOG.info("EXECUTING : command line runner");

        Scanner console = new Scanner(System.in);
        System.out.println("Введите одну из двух команд: " +
                "\"write\" - для записи базы данных в .xml файл или " +
                "\"update\" для обновления базы данных из .xml файла.");
        String option = console.nextLine();
        if (option.equalsIgnoreCase("write")) {
            xmlWriter.writeDbInXml();
            System.out.println("Файл успешно создан.");
        } else if (option.equalsIgnoreCase("update")) {
            databaseUpdateFromXml.DbUpdate();
            System.out.println("База данных успешно обновлена.");
        } else {
            System.out.println("Вы ввели неверную команду, попробуйте еще раз.");
            run();
        }

        for (int i = 0; i < args.length; ++i) {
            LOG.info("args[{}]: {}", i, args[i]);
        }
    }
}
