package lab2;

import lab2.exception.ConvertException;
import lab2.model.Race;
import lab2.model.Route;
import lab2.model.Ticket;
import lab2.model.Wagon;
import lab2.service.converter.JsonConverter;
import lab2.service.converter.XmlConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TestXmlConverter {
    private Ticket ticket;

    private XmlConverter<Race> RaceXmlConverter;
    private Race race;

    private Wagon wagon;


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


    {
        route1 = new Route("Чернівці", "Львів");
        route2 = new Route("Львів", "Київ");

        Ticket ticket1 = new Ticket(LocalDate.of(2019, 10, 20), 100);
        Ticket ticket2 = new Ticket(LocalDate.of(2019, 11, 22), 120);
        Ticket ticket3 = new Ticket(LocalDate.of(2019, 8, 12), 60);
        Ticket ticket4 = new Ticket(LocalDate.of(2019, 9, 23), 80);

        tickets1 = new ArrayList<>();
        tickets1.add(ticket1);
        tickets1.add(ticket2);

        tickets2 = new ArrayList<>();
        tickets2.add(ticket3);
        tickets2.add(ticket4);

        wagon1 = new Wagon("Вагон 1", tickets1, 100);
        wagon2 = new Wagon("Вагон 2", tickets1, 20);
        wagon3 = new Wagon("Вагон 3", tickets2, 20);
        wagon4 = new Wagon("Вагон 4", tickets2, 30);

        wagons1 = new ArrayList<>();
        wagons1.add(wagon1);
        wagons1.add(wagon2);

        wagons2 = new ArrayList<>();
        wagons2.add(wagon3);
        wagons2.add(wagon4);

        race = new Race.Builder()
                .setStartTime(LocalTime.of(16, 30))
                .setFinishTime(LocalTime.of(17, 30))
                .setDeparturePoint("Station 1")
                .setPeriodicity(Race.Periodicity.Friday)
                .setWagons(wagons1)
                .setNumberTrain(2)
                .setRoute(route1)
                .build();
    }

    @BeforeTest
    public void beforeTest() {
        RaceXmlConverter = new XmlConverter<>(Race.class);
    }

    @Test
    public void serializeToStringTest() throws ConvertException {
        String expected = "<Race><departurePoint>Station 1</departurePoint><wagons><wagons><title>Вагон 1</title><numberFree>100</numberFree><tickets><tickets><date>2019-10-20</date><ticketPrice>100</ticketPrice></tickets><tickets><date>2019-11-22</date><ticketPrice>120</ticketPrice></tickets></tickets></wagons><wagons><title>Вагон 2</title><numberFree>20</numberFree><tickets><tickets><date>2019-10-20</date><ticketPrice>100</ticketPrice></tickets><tickets><date>2019-11-22</date><ticketPrice>120</ticketPrice></tickets></tickets></wagons></wagons><numberTrain>2</numberTrain><startTime>16:30</startTime><finishTime>17:30</finishTime><route><from>Чернівці</from><to>Львів</to></route><periodicity>Friday</periodicity></Race>";
        String actual = RaceXmlConverter.serializeToString(race);
        Assert.assertEquals(actual, expected);
    }



    @Test
    public void deserializeStringTest() throws ConvertException {
        String xmlString = "<Race><departurePoint>Station 1</departurePoint><wagons><wagons><title>Вагон 1</title><numberFree>100</numberFree><tickets><tickets><date>2019-10-20</date><ticketPrice>100</ticketPrice></tickets><tickets><date>2019-11-22</date><ticketPrice>120</ticketPrice></tickets></tickets></wagons><wagons><title>Вагон 2</title><numberFree>20</numberFree><tickets><tickets><date>2019-10-20</date><ticketPrice>100</ticketPrice></tickets><tickets><date>2019-11-22</date><ticketPrice>120</ticketPrice></tickets></tickets></wagons></wagons><numberTrain>2</numberTrain><startTime>16:30</startTime><finishTime>17:30</finishTime><route><from>Чернівці</from><to>Львів</to></route><periodicity>Friday</periodicity></Race>";
        Race actual = RaceXmlConverter.deserializeString(xmlString);
        Assert.assertEquals(actual, race);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeStringTest() throws ConvertException {
        String xmlString = "<Race><departurePoint>Station 1</departurePoint><wagons><wagons><title>Вагон 1</title><numberFree>100</numberFree><tickets><tickets><date>2019jjk-10-20</date><ticketPrice>100</ticketPrice></tickets><tickets><date>2019-11-22</date><ticketPrice>120</ticketPrice></tickets></tickets></wagons><wagons><title>Вагон 2</title><numberFree>20</numberFree><tickets><tickets><date>2019-10-20</date><ticketPrice>100</ticketPrice></tickets><tickets><date>2019-11-22</date><ticketPrice>120</ticketPrice></tickets></tickets></wagons></wagons><numberTrain>2</numberTrain><startTime>16:30</startTime><finishTime>17:30</finishTime><route><from>Чернівці</from><to>Львів</to></route><periodicity>Friday</periodicity></Race>";
        RaceXmlConverter.deserializeString(xmlString);
    }
}
