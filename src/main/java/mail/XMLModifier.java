package mail;

import org.apache.log4j.BasicConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.log4j.Logger;

public class XMLModifier {
    private static final String XML_EMAIL_DETAILS_PATH = "emaildetails.xml";
    private static final Logger logger = Logger.getLogger(XMLModifier.class);

    private String getPlainTextFromTxtFile(File txtFile){
        StringBuilder stringBuilder = new StringBuilder();
        try (Stream<String> lines = Files.lines(txtFile.toPath(), Charset.defaultCharset())) {
            lines.forEach(line -> stringBuilder.append(line).append("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public void updatePlainTextInEmailDetailsXMLFromTxtFile(File txtFile){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(XML_EMAIL_DETAILS_PATH);

            Node plainTextNode = document.getElementsByTagName("plainText").item(0);

            plainTextNode.setTextContent(getPlainTextFromTxtFile(txtFile));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File(XML_EMAIL_DETAILS_PATH));
            transformer.transform(domSource, streamResult);

            logger.info("Email Plain Text updated");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }

    }
}
