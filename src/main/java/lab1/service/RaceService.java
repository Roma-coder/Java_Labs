package lab1.service;

import lab1.model.Race;
import lab1.model.Wagon;

import java.util.ArrayList;
import java.util.Comparator;

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

}

