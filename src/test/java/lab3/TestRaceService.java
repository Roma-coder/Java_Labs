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
        wagon2 = new Wagon("Premium 2", null, 2);
        wagon3 = new Wagon("Standard 3", null, 2);
        wagon4 = new Wagon("Wagon4", null, 6);
        wagon5 = new Wagon("Premium 5", null, 8);

        Race race = new Race.Builder()
                .build();
        race.addWagon(wagon1);
        race.addWagon(wagon2);
        race.addWagon(wagon3);
        race.addWagon(wagon4);
        race.addWagon(wagon5);

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
        Set<Object> expected = Set.of(new Object[]{ wagon1, wagon4});
        Set<Wagon> actual = raceService.searchByWagonTitle("Wagon");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSearchByWagonTitles() {
        Set<Object> expected = Set.of(new Object[]{ wagon1, wagon2, wagon4, wagon5});
        Set<Wagon> actual = raceService.searchByWagonTitles("Wagon", "Premium");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSearchWagonsWithNumberFree() {
        Set<Object> expected = Set.of(new Object[]{ wagon5, wagon1 });
        Set<Wagon> actual = raceService.searchWagonsWithNumberFree(8);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testGetAllWagonsTitles() {
        Set<Object> expected = Set.of(new Object[]{ "Premium 5", "Standard 3", "Premium 2", "Wagon4", "Wagon1"});
        Set<String> actual = raceService.getAllWagonsTitles();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSearchFreeWagons() {
        Set<Object> expected = Set.of(new Object[]{ wagon1, wagon2, wagon3, wagon4, wagon5 });
        Set<Wagon> actual = raceService.searchFreeWagons();
        Assert.assertEquals(actual, expected);
    }

}
