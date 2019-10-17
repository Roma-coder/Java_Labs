package lab1;

import java.util.List;

public class Race {
    private String departurePoint;
    private List<Wagon> wagons;
    private Integer numberTrain;
    private String startTime;
    private String finishTime;
    private Route route;

    public enum Periodicity{
        Regular,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
    }

    public static class Builder
    {

    }

}
