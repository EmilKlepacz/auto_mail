import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String XLSX_FILE_PATH = "mail_list.xlsx";
    private static final String WINDOW_TITLE = "Auto Mail";
    private static final String MAIN_WINDOW_FXML_PATH = "mail/main_window.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_WINDOW_FXML_PATH));
        Parent root = loader.load();
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(new Scene(root));

        MainController mainController = loader.getController();
        mainController.setStage(primaryStage);


        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

//        ExcelReader excelReader = new ExcelReader(XLSX_FILE_PATH);
//        List<Record> recordList = excelReader.loadRecordsToList();
//
//        AutoMailer autoMailer = new AutoMailer();
//        autoMailer.sendToAllRecordsFromList(recordList);

    }
}
