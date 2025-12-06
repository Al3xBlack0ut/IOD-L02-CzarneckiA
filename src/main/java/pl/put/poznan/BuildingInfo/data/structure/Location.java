package pl.put.poznan.BuildingInfo.data.structure;

import java.util.HashSet;
import java.util.Set;

/**
 * Location - podstawowa struktura danych. Location jest zarówno Building, Floor, jak i Room.
 *
 * @author PiotrRem
 *
 */
public class Location {
    Set<Location> children; // Inne Location, z których składa się macierzysty Location.
    int id; // unikalny identyfikator
    String name; // opcjonalna nazwa. Gdy nie ma nazwy name=null

    float area; // powierzchnia w m^2
    float cube; // kubatura w m^3
    float heating; // poziom zużycia energii ogrzewania
    float light; // łączna moc oświetlenia

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
