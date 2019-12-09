package lab6.service;

import lab6.model.Race;
import lab6.model.Wagon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RaceService {

    private Race race;

    public RaceService(Race race) {
        this.race = race;
    }

    public ArrayList<Wagon> sortWagonsByNumberFree() {
        ArrayList<Wagon> result = new ArrayList<>(race.getWagons());
        result.sort(Comparator.comparing(Wagon::getNumberFree));
        return result;
    }

    public Set<Wagon> searchByWagonTitle(String title) {
        return race.getWagons().stream()
                .filter((w) -> w.getTitle().contains(title))
                .collect(Collectors.toSet());
    }

    public Set<Wagon> searchByWagonTitles(String ... titles) {
        Set<Wagon> result = new HashSet<>();
        for (String title : titles) {
            Set<Wagon> searchByTitleResult = searchByWagonTitle(title);
            result.addAll(searchByTitleResult);
        }
        return result;
    }

    public Set<Wagon> searchWagonsWithNumberFree(Integer numberFree) {
        return race.getWagons().stream()
                .filter((w) -> w.getNumberFree() >= numberFree)
                .collect(Collectors.toSet());
    }

    public Set<String> getAllWagonsTitles() {
        return race.getWagons().stream()
                .map(Wagon::getTitle)
                .collect(Collectors.toSet());
    }

    public Set<Wagon> searchFreeWagons() {
        return race.getWagons().stream()
                .filter((w) -> w.getNumberFree() > 0)
                .collect(Collectors.toSet());
    }

}

