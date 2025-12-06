package pl.put.poznan.BuildingInfo.data.structure;

/**
 * Building - klasa dziedzicząca po Location. Jej pole children zawiera obiektu klasy Floor.
 *
 * @author PiotrRem
 */
public class Building extends Location {

    Building(int id, String name) {
        super(id, name);
        this.type = LocationType.Building;
    }
}
