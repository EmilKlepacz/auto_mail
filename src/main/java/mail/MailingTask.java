package mail;

import javafx.concurrent.Task;

import javax.mail.MessagingException;
import java.io.File;
import java.util.List;

public class MailingTask extends Task<Object> {

    private static final String XML_EMAIL_DETAILS_PATH = "emaildetails.xml";
    private static final String XML_MAILER_DETAILS_PATH = "mailerdetails.xml";

    private EmailDetails emailDetails;
    private MailerDetails mailerDetails;
    private EmailSender emailSender;

    private List<Record> recordList;

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

    public void setRecordList(List<Record> recordList){
        this.recordList = recordList;
    }

    @Override
    protected Object call(){

        setUpDefaultMailerDetailsFromXML();
        setUpDefaultEmailDetailsFromXML();
        setUpEmailSender();

        String emailDefaultPlainText = emailDetails.getPlainText();

        if(!recordList.isEmpty()){
            int count = recordList.size();
            int sentMessages = 0;

            for (Record record: recordList){
                emailDetails.setSubject("Relacja z przedstawienia " + record.getRepertoire() +
                        " z dnia " + record.getShowDate()
                        + " - " + record.getCity()
                        + " dla P. " + record.getReceiver()
                        + " (" + record.getSchoolName() + ")" );

                emailDetails.setPlainText(emailDefaultPlainText + "\n\n link do filmu: " + record.getUrl());
                emailDetails.setTo(record.getEmailAddress());

                emailSender.setMessage(emailDetails);


                try {
                    emailSender.sendEmail();
                    this.updateMessage("Sending to: " +  record.getEmailAddress());
                    sentMessages++;
                } catch (MessagingException e) {
                    e.printStackTrace();
                    this.updateMessage(e.getMessage());
                }


                this.updateProgress(sentMessages,count);
            }
        }
        return true;
    }

}

