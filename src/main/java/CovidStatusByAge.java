import java.util.ArrayList;

public class CovidStatus {
  private String ageRange;
  private ArrayList<Integer> positives;
  private int sum;

  public CovidStatus() {
    positives = new ArrayList<>();
  }

  public CovidStatus(String ageRange, ArrayList<Integer> positives, int sum) {
    this.ageRange = ageRange;
    this.positives = positives;
    this.sum = sum;
  }

  public String getAgeRange() {
    return ageRange;
  }

  public void setAgeRange(String ageRange) {
    this.ageRange = ageRange;
  }

  public ArrayList<Integer> getPositives() {
    return positives;
  }

  public void addPositives(Integer positive) {
    this.positives.add(positive);
  }

  public int getSum() {
    return sum;
  }

  public void setSum(int sum) {
    this.sum = sum;
  }

  @Override
  public String toString() {
    return "CovidStatus{" +
            "ageRange='" + ageRange + '\'' +
            ", positives=" + positives +
            ", sum=" + sum +
            '}';
  }
}
