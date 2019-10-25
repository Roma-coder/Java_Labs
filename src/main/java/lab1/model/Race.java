package lab1.model;

import java.time.LocalTime;
import java.util.List;

public class Race {
    private String departurePoint;
    private List<Wagon> wagons;
    private Integer numberTrain;
    private LocalTime startTime;
    private LocalTime finishTime;
    private Route route;
    private Periodicity periodicity;

    public enum Periodicity {
        Regular,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
    }

    private Race() {

    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public Integer getNumberTrain() {
        return numberTrain;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public Route getRoute() {
        return route;
    }

    public Periodicity getPeriodicity() { return periodicity; }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public void setWagons(List<Wagon> wagons) {
        this.wagons = wagons;
    }

    public void setNumberTrain(Integer numberTrain) {
        this.numberTrain = numberTrain;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public static class Builder {

        Race race;

        public Builder() {
            race = new Race();
        }

        public Builder setDeparturePoint(String departurePoint) {
            race.departurePoint = departurePoint;
            return this;
        }

        public Builder setWagons(List<Wagon> wagons) {
            race.wagons = wagons;
            return this;
        }

        public Builder setNumberTrain(Integer numberTrain) {
            race.numberTrain = numberTrain;
            return this;
        }

        public Builder setStartTime(LocalTime startTime) {
            race.startTime = startTime;
            return this;
        }

        public Builder setFinishTime(LocalTime finishTime) {
            race.finishTime = finishTime;
            return this;
        }

        public Builder setRoute(Route route) {
            race.route = route;
            return this;
        }

        public Builder setPeriodicity(Periodicity periodicity) {
            race.periodicity = periodicity;
            return this;
        }

        public Race build() {
            return race;
        }

    }

}
