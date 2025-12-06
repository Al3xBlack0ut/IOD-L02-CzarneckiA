package pl.put.poznan.BuildingInfo.data.structure;

/**
 * Floor - klasa dziedicząca po Location. Zawiera się w innym Location, tzn. Building.
 * Floor zawiera jako pole children Set obiektów typu Room.
 *
 * @author PiotrRem
 */
public class Floor extends Location {
    Floor(int id, String name) {
        super(id, name);
        this.type = LocationType.Floor;
    }
}
