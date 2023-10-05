import java.util.ArrayList;

public class CovidStatusController {
  ArrayList<CovidStatusByAge>  covidStatusAgeList;

  public CovidStatusController() {
    this.covidStatusAgeList=new ArrayList<>();
  }

  public ArrayList<CovidStatusByAge> getCovidStatusAgeList() {
    return covidStatusAgeList;
  }

  public void addAgeRanges(CovidStatusByAge StatusByAge) {
    this.covidStatusAgeList.add(StatusByAge);
  }


}
