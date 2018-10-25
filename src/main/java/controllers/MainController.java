package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.ProgressBar;
import mail.ExcelReader;
import mail.MailingTask;
import mail.Record;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;
import mail.XMLModifier;

import javax.mail.MessagingException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class MainController {
    @FXML
    private MenuItem openFromFileExcelItem;
    @FXML
    private MenuItem getOpenFromFileTxtItem;
    @FXML
    private ListView<String> recordListView;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private MenuItem sendToAllItem;
    @FXML
    private Menu sendMenu;
    @FXML
    private Label labelProgressBar;

    private Stage stage;
    private List<Record> recordList;

    private boolean isXMLWithRecordsLoaded = false;
    private boolean isEmailPlainTextLoaded = false;

    private void setUpListView(List<Record> recordList) {
        List<String> listSumUpRecords = recordList.stream().map(record -> record.getReceiver() + " : " + record.getEmailAddress())
                .collect(Collectors.toList());
        ObservableList<String> items = FXCollections.observableArrayList(listSumUpRecords);
        recordListView.setItems(items);
    }

    private void enableSendMenu() {
        sendMenu.setDisable(false);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void openFileChooserAndLoadToListFromXML() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("formats (*.xlsx, *.xls)", "*.xlsx", "*.xls"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        ExcelReader excelReader = new ExcelReader(selectedFile);

        recordList = excelReader.loadRecordsToList();

        isXMLWithRecordsLoaded = true;

        setUpListView(recordList);

        if (isEmailPlainTextLoaded && isXMLWithRecordsLoaded) enableSendMenu();

    }

    public void openFileChooserAndUpdateEmailDetailsXMLFromTxtFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("formats (*.txt)", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        XMLModifier xmlModifier = new XMLModifier();
        xmlModifier.updatePlainTextInEmailDetailsXMLFromTxtFile(selectedFile);

        isEmailPlainTextLoaded = true;

        if (isEmailPlainTextLoaded && isXMLWithRecordsLoaded) enableSendMenu();
    }


    public void sendToAll() {
        MailingTask mailingTask = new MailingTask();

        labelProgressBar.textProperty().unbind();
        labelProgressBar.textProperty().bind(mailingTask.messageProperty());

        progressBar.progressProperty().unbind();
        progressBar.progressProperty().bind(mailingTask.progressProperty());

        mailingTask.setRecordList(recordList);

        mailingTask.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, //
                t -> {
                    labelProgressBar.textProperty().unbind();
                    labelProgressBar.setText("Sending finished");
                });

            new Thread(mailingTask).start();


    }

}
