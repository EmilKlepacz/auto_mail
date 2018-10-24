package mail;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MailerDetails {
    private String SmtpServerHost;
    private int serverPort;
    private String username;
    private String password;

    public int getServerPort() {
        return serverPort;
    }

    @XmlElement
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getUsername() {
        return username;
    }

    @XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpServerHost() {

        return SmtpServerHost;
    }

    @XmlElement
    public void setSmtpServerHost(String smtpServerHost) {
        SmtpServerHost = smtpServerHost;
    }
}
