
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class ExcelExporter {
    public static void exportToExel(List<CovidStatusByAge> covidStatusByAgeList, List<String> colNames, String filename) throws FileNotFoundException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("연령별");
        //Todo sheet 이름 한글로 쓸 경우 깨지는 문제 해결

        Row headerRow = sheet.createRow(0);
        for (int i=0; i< colNames.size(); i++){
            headerRow.createCell(i).setCellValue(colNames.get(i));
        }
        int rowNum = 1;
        for(CovidStatusByAge data: covidStatusByAgeList){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data.getAgeRange());

            // 확진자는 확진자 수(확진율) 형태로 넣는다
            String confirmed = String.valueOf(data.getConfirmed()) + "(" + String.valueOf(data.getConfirmedRate())+")";
            row.createCell(1).setCellValue(confirmed);

            String dead = String.valueOf(data.getDead()) + "(" + String.valueOf(data.getDeadRate())+")";
            row.createCell(2).setCellValue(dead);

            row.createCell(3).setCellValue(data.getFatalityRate());
        }

        // 파일에 쓰기
        try (OutputStream fileOut = new FileOutputStream(filename)) {
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
