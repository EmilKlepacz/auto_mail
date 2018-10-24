package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import mail.ExcelReader;
import mail.Record;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;

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

    private Stage stage;
    private List<Record> recordList;
    private ExcelReader excelReader;

    private void setUpListView(List<Record> recordList){
        List<String> listSumUpRecords = recordList.stream().map(record -> record.getReceiver() + " : " + record.getEmailAddress())
                .collect(Collectors.toList());
        ObservableList<String> items = FXCollections.observableArrayList(listSumUpRecords);
        recordListView.setItems(items);
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

        excelReader = new ExcelReader(selectedFile);

        recordList = excelReader.loadRecordsToList();

        setUpListView(recordList);

    }
}
