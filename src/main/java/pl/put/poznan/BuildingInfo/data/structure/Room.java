package pl.put.poznan.BuildingInfo.data.structure;

/**
 * Room - klasa dziedzicząca po Location. Występuje w polu children obiektów Floor.
 *
 * @author PiotrRem
 */
public class Room extends Location {
    float area; // powierzchnia w m^2
    float cube; // kubatura w m^3
    float heating; // poziom zużycia energii ogrzewania
    float light; // łączna moc oświetlenia


    Room(int id, String name) {
        super(id, name);
    }

    Room(int id) {
        super(id);
    }

    Room(int id, String name, float area, float cube, float heating, float light) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }
}
