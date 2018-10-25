package mail;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Record {
    private String schoolName;
    private String receiver;
    private String telephoneNumber;
    private String tickets;
    private String repertoire;
    private String emailAddress;
    private LocalDate showDate;
    private String url;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private LocalDateTime sendDateTime;

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getRepertoire() {
        return repertoire;
    }

    public void setRepertoire(String repertoire) {
        this.repertoire = repertoire;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDateTime getSendDateTime() {
        return sendDateTime;
    }

    public void setSendDateTime(LocalDateTime sendDateTime) {
        this.sendDateTime = sendDateTime;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }


    @Override
    public String toString() {
        return "Record{" +
                "schoolName='" + schoolName + '\'' +
                ", receiver='" + receiver + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", tickets='" + tickets + '\'' +
                ", repertoire='" + repertoire + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", showDate=" + showDate +
                ", url='" + url + '\'' +
                ", city='" + city + '\'' +
                ", sendDateTime=" + sendDateTime +
                '}';
    }
}
