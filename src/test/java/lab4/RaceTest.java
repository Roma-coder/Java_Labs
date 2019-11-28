package lab4;

import lab4.model.Route;
import lab4.model.Wagon;
import lab4.model.Race;
import org.testng.annotations.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RaceTest {

    private Race validRace;
    private Race notValidRace;

    {
        Route route = new Route("Чернівці", "Львів");

        Wagon wagon1 = new Wagon("Wagon1", null, 10);
        Wagon wagon2 = new Wagon("Wagon2", null, 0);
        Wagon wagon3 = new Wagon("Wagon3", null, 2);

        validRace = new Race.Builder()
                .setDeparturePoint("Station 1")
                .setNumberTrain(1)
                .setStartTime(LocalTime.of(14, 0))
                .setFinishTime(LocalTime.of(16, 30))
                .setRoute(route)
                .setPeriodicity(Race.Periodicity.Regular)
                .build();

        validRace.addWagon(wagon1);
        validRace.addWagon(wagon2);
        validRace.addWagon(wagon3);




        notValidRace = new Race.Builder()
                .setDeparturePoint("Station 1")
                .setNumberTrain(null)
                .setStartTime(LocalTime.of(14, 0))
                .setFinishTime(LocalTime.of(16, 30))
                .setRoute(route)
                .setPeriodicity(Race.Periodicity.Regular)
                .build();

        notValidRace.addWagon(wagon1);
        notValidRace.addWagon(wagon2);
        notValidRace.addWagon(wagon3);
    }

    @Test
    public void testRaceValidate() {
        validRace.validate();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testRaceValidateNegative() {
        notValidRace.validate();
    }


}
