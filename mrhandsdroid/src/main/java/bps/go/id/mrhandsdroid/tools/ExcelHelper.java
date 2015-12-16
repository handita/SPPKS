package bps.go.id.mrhandsdroid.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import bps.go.id.mrhandsdroid.models.Konsistensi;
import bps.go.id.mrhandsdroid.models.Metadata;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by handi_000 on 7/1/2015.
 */
public class ExcelHelper {

    private InputStream inputStream;
    private String sheet;


    public ExcelHelper(InputStream inputStream) {
        this.inputStream = inputStream;
        this.sheet = sheet;
    }

    public static final int FIELD = 0;
    public static final int DESCRIPTION = 1;
    public static final int DATATYPE = 2;
    public static final int TYPE = 3;
    public static final int MAXLENGTH = 4;
    public static final int BLANK_NOTBLANK = 5;
    public static final int RANGE = 6;
    public static final int TRANSFORM_VALUE = 7;
    public static final int PAGE = 8;



    public static final int ID=0;
    public static final int RULE=1;
    public static final int PAGE_K=2;
    public static final int TYPE_K=3;
    public static final int LOAD=4;



    public List<Metadata> getMetadata(String sheetName) throws IOException, BiffException {
        List<Metadata> listMetadata = new ArrayList<Metadata>();

        Workbook w;

        w = Workbook.getWorkbook(inputStream);
        // Get the first sheet
        Sheet sheet = w.getSheet(sheetName);
        // Loop over first 10 column and lines
        int rowStart = 2;

        for (int i = rowStart; i < sheet.getRows(); i++) {
            Metadata metadata = new Metadata();
            metadata.field = sheet.getCell(FIELD, i).getContents();
            metadata.description = sheet.getCell(DESCRIPTION, i).getContents();
            metadata.dataType = sheet.getCell(DATATYPE, i).getContents();
            metadata.type = sheet.getCell(TYPE, i).getContents();
            metadata.maxLength = Integer.parseInt(sheet.getCell(MAXLENGTH, i).getContents());
            metadata.blankNotBlank = sheet.getCell(BLANK_NOTBLANK, i).getContents();
            metadata.range = sheet.getCell(RANGE, i).getContents();
            metadata.transformValue = sheet.getCell(TRANSFORM_VALUE, i).getContents();
            metadata.page = Integer.parseInt(sheet.getCell(PAGE, i).getContents());
            listMetadata.add(metadata);
        }


        return listMetadata;
    }

    public List<Konsistensi> getKonsistensi(String sheetName) throws IOException, BiffException {
        List<Konsistensi> listKonsistensi = new ArrayList<Konsistensi>();

        Workbook w;

        w = Workbook.getWorkbook(inputStream);
        // Get the first sheet
        Sheet sheet = w.getSheet(sheetName);
        // Loop over first 10 column and lines
        int rowStart = 2;

        for (int i = rowStart; i < sheet.getRows(); i++) {
            Konsistensi konsistensi = new Konsistensi();
            konsistensi.ID = Integer.parseInt(sheet.getCell(ID, i).getContents());
            konsistensi.rule = sheet.getCell(RULE, i).getContents();
            konsistensi.load = sheet.getCell(LOAD, i).getContents();
            konsistensi.page = sheet.getCell(PAGE_K, i).getContents();
            konsistensi.tipe = sheet.getCell(TYPE_K, i).getContents();
            listKonsistensi.add(konsistensi);
        }


        return listKonsistensi;
    }


}
