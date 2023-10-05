import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CovidStatusClassTest {
    public static void main(String[] args) {
        CovidStatusController controller = new CovidStatusController();

        String targetUrl = "https://ncov.kdca.go.kr/bdBoardList_Real.do";

        try {
            Document doc = Jsoup.connect(targetUrl).get();
            Element ageTable = doc.select(".data_table").get(4);
            Elements rows = ageTable.getElementsByTag("tr");

            saveDataFromWeb(controller, rows);
            ArrayList<String> colNames = getColNames(rows.get(0));

            ExcelExporter.exportToExel(controller.getCovidStatusAgeList(), colNames, "통계.xlsx");
            PdfExporter.exportToPdf(controller.getCovidStatusAgeList(), colNames, "통계.pdf");

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void saveDataFromWeb(CovidStatusController controller, Elements rows){

        //연령별 데이터 저장
        for (int i=1; i <rows.size(); i++){

            Pattern pattern = Pattern.compile("(\\d+(?:,\\d{3})*).*\\(([0-9]+\\.[0-9]+)\\)");
            CovidStatusByAge covidStatusByAge = new CovidStatusByAge();

            //
            Elements cols=rows.get(i).children();
            String ageRange = Objects.requireNonNull(cols.first()).text();
            covidStatusByAge.setAgeRange(ageRange);

            //확진자 수
            String confirmed = cols.get(1).text();
            Matcher matcher = pattern.matcher(confirmed);

            if (matcher.find()){
                int confirmedNum = Integer.parseInt(matcher.group(1).replace(",", ""));
                covidStatusByAge.setConfirmed(confirmedNum);

                double confirmedRate = Double.parseDouble(matcher.group(2).replace(",", ""));
                covidStatusByAge.setConfirmedRate(confirmedRate);
            }

            //  사망자
            String dead = cols.get(2).text();
            matcher = pattern.matcher(dead);

            if (matcher.find()){
                int deadNum = Integer.parseInt(matcher.group(1).replace(",", ""));
                covidStatusByAge.setDead(deadNum);

                double deadRate = Double.parseDouble(matcher.group(2).replace(",", ""));
                covidStatusByAge.setDeadRate(deadRate);
            }

            //치명률
            // 스트림 사용하면 간결해지나?
            String fatalRateStr = cols.get(3).text();
            if(fatalRateStr.equals("-")){
                fatalRateStr = "0";
            }
            double fatalRate = Double.parseDouble(fatalRateStr);
            covidStatusByAge.setFatalityRate(fatalRate);

            //데이터 저장.
            controller.addAgeRanges(covidStatusByAge);
        }// for(row)

    }

    public static ArrayList<String> getColNames(Element headerRow){
        ArrayList<String> colNames = new ArrayList<>();
        String targetUrl = "https://ncov.kdca.go.kr/bdBoardList_Real.do";

        Elements headCols = headerRow.getElementsByTag("th");


        //표 제목 얻기.

        for (Element col : headCols) {
          colNames.add(col.text());
        }

        return colNames;
    }
}
