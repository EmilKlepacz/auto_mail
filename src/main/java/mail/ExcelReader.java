package mail;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {
    private static final Logger logger = Logger.getLogger(ExcelReader.class);

    private Workbook workbook;

    private boolean isCellDateFormatted(Cell cell){
        if(cell.getCellTypeEnum() == org.apache.poi.ss.usermodel.CellType.NUMERIC
                && DateUtil.isCellDateFormatted(cell)){
            return true;
        }
        return false;
    }

    private String convertCellDateFrmattedToString(Cell cell){
        Date cellValueDate = cell.getDateCellValue();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(cellValueDate);

    }

    public ExcelReader(File file) {
        try {
            workbook = WorkbookFactory.create(file);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public List<Record> loadRecordsToList() {
        Sheet sheet = workbook.getSheetAt(0);
        List<Record> recordList = new ArrayList<>();
        List<String> cellsInCurrentRow = new ArrayList<>();

        DataFormatter dataFormatter = new DataFormatter();

        Iterator<Row> rowIterator = sheet.rowIterator();

        // skip first row in file
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            Record record = new Record();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                String cellValue;
                if (isCellDateFormatted(cell)){
                    cellValue = convertCellDateFrmattedToString(cell);
                }else{
                    cellValue = dataFormatter.formatCellValue(cell);
                }

                cellsInCurrentRow.add(cellValue);
            }
            record.setSchoolName(cellsInCurrentRow.get(0));
            record.setReceiver(cellsInCurrentRow.get(1));
            record.setTelephoneNumber(cellsInCurrentRow.get(2));
            record.setTickets(cellsInCurrentRow.get(3));
            record.setRepertoire(cellsInCurrentRow.get(4));
            record.setEmailAddress(cellsInCurrentRow.get(5));


            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            record.setShowDate(LocalDate.parse(cellsInCurrentRow.get(6), format));

            record.setUrl(cellsInCurrentRow.get(7));

            record.setCity(cellsInCurrentRow.get(8));

            cellsInCurrentRow.clear();
            recordList.add(record);
        }


        logger.info("Records loaded from XML");
        return recordList;
    }


}
