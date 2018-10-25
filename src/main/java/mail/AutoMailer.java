package mail;

import javafx.concurrent.Task;

import java.io.File;
import java.util.List;

public class AutoMailer {

    private static final String XML_EMAIL_DETAILS_PATH = "emaildetails.xml";
    private static final String XML_MAILER_DETAILS_PATH = "mailerdetails.xml";

    private EmailDetails emailDetails;
    private MailerDetails mailerDetails;
    private EmailSender emailSender;

    private void setUpDefaultEmailDetailsFromXML(){
        JAXBXMLConverter jaxbxmlConverter = new JAXBXMLConverter();
        emailDetails = jaxbxmlConverter.convertToEmailDetailsFromXML(new File(XML_EMAIL_DETAILS_PATH));
    }

    private void setUpDefaultMailerDetailsFromXML(){
        JAXBXMLConverter jaxbxmlConverter = new JAXBXMLConverter();
        mailerDetails = jaxbxmlConverter.convertToMailerDetailsFromXML(new File(XML_MAILER_DETAILS_PATH));
    }

    private void setUpEmailSender(){
        emailSender = new EmailSender();
        emailSender.setProps(mailerDetails);
        emailSender.setSession(mailerDetails);
    }


//    public void sendToAllRecordsFromList(List<Record> recordList){
//
//        setUpDefaultMailerDetailsFromXML();
//        setUpDefaultEmailDetailsFromXML();
//        setUpEmailSender();
//
//        String emailDefaultPlainText = emailDetails.getPlainText();
//
//        for (Record record: recordList){
//            emailDetails.setSubject("Relacja z przedstawienia " + record.getRepertoire() +
//                    " z dnia " + record.getShowDate()
//                    + " dla P. " + record.getReceiver()
//                    + " (" + record.getSchoolName() + ")" );
//
//            emailDetails.setPlainText(emailDefaultPlainText + "\n\n link do filmu: " + record.getUrl());
//            emailDetails.setTo(record.getEmailAddress());
//
//            emailSender.setMessage(emailDetails);
//            emailSender.sendEmail();
//        }
//    }
}
