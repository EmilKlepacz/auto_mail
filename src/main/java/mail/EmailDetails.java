package mail;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class EmailDetails {
    private String from;

    public String getFrom() {
        return from;
    }

    private String to;
    private String subject;
    private String plainText;

    @XmlElement
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    @XmlElement
    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    @XmlElement
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPlainText() {
        return plainText;
    }

    @XmlElement
    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    @Override
    public String toString() {
        return "EmailDetails{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", plainText='" + plainText + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDetails that = (EmailDetails) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(plainText, that.plainText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(from, to, subject, plainText);
    }


}
