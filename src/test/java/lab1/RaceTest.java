package lab1;

import lab1.model.Race;
import lab1.model.Route;
import lab1.model.Ticket;
import lab1.model.Wagon;
import lab1.service.RaceService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class RaceTest {


    private Route route1;
    private Route route2;

    private ArrayList<Ticket> tickets1;
    private ArrayList<Ticket> tickets2;

    private Wagon wagon1;
    private Wagon wagon2;
    private Wagon wagon3;
    private Wagon wagon4;

    private ArrayList<Wagon> wagons1;
    private ArrayList<Wagon> wagons2;

    private Race race1;
    private Race race2;

    {

        route1 = new Route("Чернівці", "Львів");
        route2 = new Route("Львів", "Київ");

        Ticket ticket1 = new Ticket(LocalDate.of(2019, 10, 20), 100);
        Ticket ticket2 = new Ticket(LocalDate.of(2019, 11, 22), 120);
        Ticket ticket3 = new Ticket(LocalDate.of(2019, 8, 12), 60);
        Ticket ticket4 = new Ticket(LocalDate.of(2019, 9, 23), 80);

        tickets1 = new ArrayList<Ticket>();
        tickets1.add(ticket1);
        tickets1.add(ticket2);

        tickets2 = new ArrayList<Ticket>();
        tickets2.add(ticket3);
        tickets2.add(ticket4);

        wagon1 = new Wagon("Вагон 1", tickets1, 100);
        wagon2 = new Wagon("Вагон 2", tickets1, 20);
        wagon3 = new Wagon("Вагон 3", tickets2, 20);
        wagon4 = new Wagon("Вагон 4", tickets2, 30);

        wagons1 = new ArrayList<Wagon>();
        wagons1.add(wagon1);
        wagons1.add(wagon2);

        wagons2 = new ArrayList<Wagon>();
        wagons2.add(wagon3);
        wagons2.add(wagon4);
    }

    @BeforeMethod
    public void createRaces() {
        race1 = new Race.Builder()
                .setDeparturePoint("Station 1")
                .setWagons(wagons1)
                .setNumberTrain(1)
                .setStartTime(LocalTime.of(14, 0))
                .setFinishTime(LocalTime.of(16, 30))
                .setRoute(route1)
                .setPeriodicity(Race.Periodicity.Regular)
                .build();

        race2 = new Race.Builder()
                .setDeparturePoint("Station 2")
                .setWagons(wagons2)
                .setNumberTrain(2)
                .setStartTime(LocalTime.of(12, 5))
                .setFinishTime(LocalTime.of(14, 20))
                .setRoute(route2)
                .setPeriodicity(Race.Periodicity.Friday)
                .build();
    }

    @Test
    public void builderTest() {
        Race race = new Race.Builder()
                .setDeparturePoint("Station 1")
                .setWagons(wagons1)
                .setNumberTrain(1)
                .setStartTime(LocalTime.of(14, 0))
                .setFinishTime(LocalTime.of(16, 30))
                .setRoute(route1)
                .setPeriodicity(Race.Periodicity.Regular)
                .build();
    }

    @Test
    public void sortWagonsByNumberFreeTest() {
        RaceService raceService = new RaceService(race1);

        Object[] actual = raceService.sortWagonsByNumberFree().toArray();
        Object[] expected = { wagon2, wagon1 };

        Assert.assertEquals(actual, expected);
    }

}

