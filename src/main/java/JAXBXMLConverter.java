import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBXMLConverter {

    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;

    public EmailDetails convertToEmailDetailsFromXML(File file) {
        EmailDetails emailDetails = null;
        try {
            jaxbContext = JAXBContext.newInstance(EmailDetails.class);

            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            emailDetails = (EmailDetails) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return emailDetails;
    }

    public MailerDetails convertToMailerDetailsFromXML(File file) {
        MailerDetails mailerDetails = null;
        try {
            jaxbContext = JAXBContext.newInstance(MailerDetails.class);

            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            mailerDetails = (MailerDetails) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return mailerDetails;
    }
}
