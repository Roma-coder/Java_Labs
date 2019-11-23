package lab3;

import lab3.model.Race;
import lab3.model.Wagon;
import lab3.service.RaceService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class TestRaceService {

    private RaceService raceService;
    private Wagon wagon1;
    private Wagon wagon2;
    private Wagon wagon3;
    private Wagon wagon4;
    private Wagon wagon5;

    {
        wagon1 = new Wagon("Wagon1", null, 10);
        wagon2 = new Wagon("Wagon2", null, 0);
        wagon3 = new Wagon("Wagon3", null, 2);
        wagon4 = new Wagon("Wagon1", null, 6);
        wagon5 = new Wagon("Wagon1", null, 8);

        List<Wagon> wagons = new LinkedList<>();
        wagons.add(wagon1);
        wagons.add(wagon2);
        wagons.add(wagon3);
        wagons.add(wagon4);
        wagons.add(wagon5);

        Race race = new Race.Builder()
                .setWagons(wagons)
                .build();

        raceService = new RaceService(race);
    }

    @Test
    public void testSortWagonsByNumberFree() {
        Object[] expected = { wagon2, wagon3, wagon4, wagon5, wagon1 };
        Object[] actual = raceService.sortWagonsByNumberFree().toArray();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSearchByWagonTitle() {
        Set<Object> expected = Set.of(new Object[]{ wagon1, wagon4, wagon5 });
        Set<Wagon> actual = raceService.searchByWagonTitle("Wagon1");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSearchWagonsWithNumberFree() {
        Set<Object> expected = Set.of(new Object[]{ wagon3 });
        Set<Wagon> actual = raceService.searchWagonsWithNumberFree(2);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetAllWagonsTitles() {
        Set<Object> expected = Set.of(new Object[]{ "Wagon1", "Wagon2", "Wagon3" });
        Set<String> actual = raceService.getAllWagonsTitles();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSearchFreeWagons() {
        Set<Object> expected = Set.of(new Object[]{ wagon1, wagon3, wagon4, wagon5 });
        Set<Wagon> actual = raceService.searchFreeWagons();
        Assert.assertEquals(actual, expected);
    }

}
