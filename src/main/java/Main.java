import java.io.File;
import java.util.List;

public class Main {
    private static final String XLSX_FILE_PATH = "mail_list.xlsx";

    public static void main(String[] args) {

        ExcelReader excelReader = new ExcelReader(XLSX_FILE_PATH);
        List<Record> recordList = excelReader.loadRecordsToList();

        AutoMailer autoMailer = new AutoMailer();
        autoMailer.sendToAllRecordsFromList(recordList);

    }

}
