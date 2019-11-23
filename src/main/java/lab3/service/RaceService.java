package lab3.service;

import lab3.model.Race;
import lab3.model.Wagon;

import java.util.ArrayList;
import java.util.Comparator;
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
                .filter((w) -> w.getTitle().equals(title))
                .collect(Collectors.toSet());
    }

    public Set<Wagon> searchWagonsWithNumberFree(Integer numberFree) {
        return race.getWagons().stream()
                .filter((w) -> w.getNumberFree().equals(numberFree))
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

