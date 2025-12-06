package pl.put.poznan.BuildingInfo.data.structure;

/**
 * Room - klasa dziedzicząca po Location. Występuje w polu children obiektów Floor.
 *
 * @author PiotrRem
 */
public class Room extends Location {

    Room(int id, String name, float area, float cube, float heating, float light) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
        this.type = LocationType.Room;
    }
}
