package lab2;

import lab2.model.Route;
import lab2.model.Race;
import lab2.model.Ticket;
import lab2.exception.ConvertException;
import lab2.model.Wagon;
import lab2.service.converter.JsonConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class TestJsonConverter {

    private Ticket ticket;

    private JsonConverter<Race> RaceJsonConverter;
    private Race race;

    private Wagon wagon;


    private Route route1;
    private Route route2;

    private ArrayList<lab2.model.Ticket> tickets1;
    private ArrayList<lab2.model.Ticket> tickets2;

    private lab2.model.Wagon wagon1;
    private lab2.model.Wagon wagon2;
    private lab2.model.Wagon wagon3;
    private lab2.model.Wagon wagon4;

    private ArrayList<lab2.model.Wagon> wagons1;
    private ArrayList<lab2.model.Wagon> wagons2;


    {
        route1 = new lab2.model.Route("Чернівці", "Львів");
        route2 = new lab2.model.Route("Львів", "Київ");

        lab2.model.Ticket ticket1 = new lab2.model.Ticket(LocalDate.of(2019, 10, 20), 100);
        lab2.model.Ticket ticket2 = new lab2.model.Ticket(LocalDate.of(2019, 11, 22), 120);
        lab2.model.Ticket ticket3 = new lab2.model.Ticket(LocalDate.of(2019, 8, 12), 60);
        lab2.model.Ticket ticket4 = new lab2.model.Ticket(LocalDate.of(2019, 9, 23), 80);

        tickets1 = new ArrayList<>();
        tickets1.add(ticket1);
        tickets1.add(ticket2);

        tickets2 = new ArrayList<>();
        tickets2.add(ticket3);
        tickets2.add(ticket4);

        wagon1 = new lab2.model.Wagon("Вагон 1", tickets1, 100);
        wagon2 = new lab2.model.Wagon("Вагон 2", tickets1, 20);
        wagon3 = new lab2.model.Wagon("Вагон 3", tickets2, 20);
        wagon4 = new lab2.model.Wagon("Вагон 4", tickets2, 30);

        wagons1 = new ArrayList<lab2.model.Wagon>();
        wagons1.add(wagon1);
        wagons1.add(wagon2);

        wagons2 = new ArrayList<lab2.model.Wagon>();
        wagons2.add(wagon3);
        wagons2.add(wagon4);



    }

    @BeforeTest
    public void beforeTest() {
        RaceJsonConverter = new JsonConverter<>(Race.class);
    }
    @BeforeMethod
    public void createRaces() {
        race = new Race.Builder()
                .setStartTime(LocalTime.of(15,30))
                .setFinishTime(LocalTime.of(16, 30))
                .setRoute(route1)
                .setDeparturePoint("Station 1")
                .setWagons(wagons1)
                .setNumberTrain(1)
                .setPeriodicity(Race.Periodicity.Regular)
                .build();

    }

    @Test
    public void builderTest() {
        lab2.model.Race race = new lab2.model.Race.Builder()
                .setDeparturePoint("Station 1")
                .setWagons(wagons1)
                .setNumberTrain(1)
                .setStartTime(LocalTime.of(14, 0))
                .setFinishTime(LocalTime.of(16, 30))
                .setRoute(route1)
                .setPeriodicity(lab2.model.Race.Periodicity.Regular)
                .build();
    }


    @Test
    public void serializeToStringTest() throws ConvertException {
        String expected = "{\"departurePoint\":\"Station 1\",\"wagons\":[{\"title\":\"Вагон 1\",\"numberFree\":100,\"tickets\":[{\"date\":\"2019-10-20\",\"ticketPrice\":100},{\"date\":\"2019-11-22\",\"ticketPrice\":120}]},{\"title\":\"Вагон 2\",\"numberFree\":20,\"tickets\":[{\"date\":\"2019-10-20\",\"ticketPrice\":100},{\"date\":\"2019-11-22\",\"ticketPrice\":120}]}],\"numberTrain\":1,\"startTime\":\"15:30\",\"finishTime\":\"16:30\",\"route\":{\"from\":\"Чернівці\",\"to\":\"Львів\"},\"periodicity\":\"Regular\"}";
        String actual = RaceJsonConverter.serializeToString(race);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeStringTest() throws ConvertException {
        String jsonString = "{\"departurePoint\":\"Station 1\",\"wagons\":[{\"title\":\"Вагон 1\",\"tickets\":[{\"date\":\"2019-10-20\",\"ticketPrice\":100},{\"date\":\"2019-11-22\",\"ticketPrice\":120}],\"numberFree\":100},{\"title\":\"Вагон 2\",\"tickets\":[{\"date\":\"2019-10-20\",\"ticketPrice\":100},{\"date\":\"2019-11-22\",\"ticketPrice\":120}],\"numberFree\":20}],\"numberTrain\":1,\"startTime\":\"15:30\",\"finishTime\":\"16:30\",\"route\":{\"from\":\"Чернівці\",\"to\":\"Львів\"},\"periodicity\":\"Regular\"}";
        Race actual = RaceJsonConverter.deserializeString(jsonString);
        Assert.assertEquals(actual, race);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeStringTest() throws ConvertException {
        String jsonString = "{\"departurePoint\":\"Station 1\"wagons\":[{\"title\":\"Вагон 1\",\"tickets\":[{\"date\":\"2019-10-20\",\"ticketPrice\":100},{\"date\":\"2019-11-22\",\"ticketPrice\":120}],\"numberFree\":100},{\"title\":\"Вагон 2\",\"tickets\":[{\"date\":\"2019-10-20\",\"ticketPrice\":100},{\"date\":\"2019-11-22\",\"ticketPrice\":120}],\"numberFree\":20}],\"numberTrain\":1,\"startTime\":\"15:30\",\"finishTime\":\"16:30\",\"route\":{\"from\":\"Чернівці\",\"to\":\"Львів\"},\"periodicity\":\"Regular\"}";
        RaceJsonConverter.deserializeString(jsonString);
    }

}
