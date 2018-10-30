import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

public class Main extends Application {
    private static final String XLSX_FILE_PATH = "mail_list.xlsx";
    private static final String WINDOW_TITLE = "Auto Mail";
    private static final String MAIN_WINDOW_FXML_PATH = "mail/automailwindow.fxml";

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
        BasicConfigurator.configure();
        launch(args);

    }
}
