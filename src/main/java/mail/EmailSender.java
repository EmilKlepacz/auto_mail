package mail;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Properties;


public class EmailSender {

    private static final Logger log = Logger.getLogger(EmailSender.class);

    private Message message;
    private Properties props;
    private Session session;


    public void setProps(MailerDetails mailerDetails) {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailerDetails.getSmtpServerHost());
        props.put("mail.smtp.port", mailerDetails.getServerPort());
    }

    public void setSession(MailerDetails mailerDetails) {
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(mailerDetails.getUsername(), mailerDetails.getPassword());
                    }
                });
    }

    public void setMessage(EmailDetails emailDetails) {
        message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailDetails.getFrom()));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailDetails.getTo()));
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getPlainText());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmail() {
        try {

            log.info("Message send to " + Arrays.toString(message.getRecipients(Message.RecipientType.TO)));

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
