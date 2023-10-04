import java.util.ArrayList;

public class CovidStatusByAge {
  private String ageRange;
  private int confirmed;
  private double confirmedRate;
  private int dead;
  private double deadRate;
  private double fatalityRate;

  public CovidStatusByAge() {
  }

  public CovidStatusByAge(String ageRange, int confirmed, double confirmedRate, int dead, double deadRate, double fatalityRate) {
    this.ageRange = ageRange;
    this.confirmed = confirmed;
    this.confirmedRate = confirmedRate;
    this.dead = dead;
    this.deadRate = deadRate;
    this.fatalityRate = fatalityRate;
  }

  public String getAgeRange() {
    return ageRange;
  }

  public void setAgeRange(String ageRange) {
    this.ageRange = ageRange;
  }

  public int getConfirmed() {
    return confirmed;
  }

  public void setConfirmed(int confirmed) {
    this.confirmed = confirmed;
  }

  public double getConfirmedRate() {
    return confirmedRate;
  }

  public void setConfirmedRate(double confirmedRate) {
    this.confirmedRate = confirmedRate;
  }

  public int getDead() {
    return dead;
  }

  public void setDead(int dead) {
    this.dead = dead;
  }

  public double getDeadRate() {
    return deadRate;
  }

  public void setDeadRate(double deadRate) {
    this.deadRate = deadRate;
  }

  public double getFatalityRate() {
    return fatalityRate;
  }

  public void setFatalityRate(double fatalityRate) {
    this.fatalityRate = fatalityRate;
  }

  @Override
  public String toString() {
    return  "{연령대: " + ageRange +
            ", 확진자: " + confirmed +
            ", 확진율: " + confirmedRate +
            ", 사망자: " + dead +
            ", 사망률: " + deadRate +
            ", 치명률: " + fatalityRate +
            '}';
  }
}
