package pl.put.poznan.BuildingInfo.data.structure;

public class Room extends Location {
    float area;
    float cube;
    float heating;
    float light;


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
