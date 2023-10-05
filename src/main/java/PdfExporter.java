import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PdfExporter {
  public static void exportToPdf(List<CovidStatusByAge> covidStatusByAgeList, List<String> colNames, String filename) throws FileNotFoundException {
	try{
	  PdfFont krFontNormal = PdfFontFactory.createFont("Pretendard-Medium.otf", PdfEncodings.IDENTITY_H);
	  PdfFont krFontLight = PdfFontFactory.createFont("Pretendard-Light.otf", PdfEncodings.IDENTITY_H);

	  PdfDocument pdf = new PdfDocument(new PdfWriter(filename));
	  Document document = new Document(pdf);

	  // 제목 추가
	  Paragraph title = new Paragraph("나이별 코로나 바이러스 감염 현황").setTextAlignment(TextAlignment.CENTER).setFontSize(14);
	  document.add(title.setFont(krFontNormal));

	  //테이블 설정
	  float[] columnWidths = {80, 100, 100, 100};
	  Table table = new Table(UnitValue.createPercentArray(columnWidths));

	  // 테이블 생성
	  for (String header: colNames){
		Cell cell = new Cell().add(new Paragraph(header).setFont(krFontNormal));
		cell.setTextAlignment(TextAlignment.CENTER);
		table.addHeaderCell(cell);
	  }

	  Style normal = new Style();
	  normal.setFont(krFontLight).setFontSize(9);
	  for (CovidStatusByAge data: covidStatusByAgeList){
		String ageRange = data.getAgeRange();
		table.addCell(new Cell().add(new Paragraph(ageRange).addStyle(normal)).setTextAlignment(TextAlignment.CENTER));
		String confirmed = String.valueOf(data.getConfirmed()) + " (" + String.valueOf(data.getConfirmedRate()) + ")";
		table.addCell(new Cell().add(new Paragraph(confirmed).addStyle(normal)).setTextAlignment(TextAlignment.RIGHT));
		String dead = String.valueOf(data.getDead()) + " ("+String.valueOf(data.getDeadRate()) +")";
		table.addCell(new Cell().add(new Paragraph(dead).addStyle(normal)).setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell().add(new Paragraph(String.valueOf(data.getFatalityRate())).addStyle(normal)).setTextAlignment(TextAlignment.RIGHT));
	  }

	  document.add(table);
	  document.close();


	}catch(Exception e){
	  e.printStackTrace();
	}

  }
}
