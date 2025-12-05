package pl.put.poznan.BuildingInfo.data.structure;

import java.util.HashSet;
import java.util.Set;

public class Location {
    Set<Location> children;
    int id;
    String name;

    Location(int id) {
        this.id = id;
        children = new HashSet<>();
    }

    Location(int id, String name) {
        this.id = id;
        this.name = name;
        children = new HashSet<>();
    }
}
