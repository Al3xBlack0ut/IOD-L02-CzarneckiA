package pl.put.poznan.BuildingInfo.data.structure;

import java.util.*;

/**
 * Location - podstawowa struktura danych. Location jest zarówno Building, Floor, jak i Room.
 *
 * @author PiotrRem
 *
 */
public class Location {
    /**
     * LocationType - typ wyliczeniowy używany, by orientować się, jak nisko w hierarchii obiektów Location zeszły algorytmy przeszukujące.
     */
    enum LocationType{
        Root, Building, Floor, Room, INVALID;

        public LocationType next(){
            switch (this){
                case Root:
                    return LocationType.Building;
                case Building:
                    return LocationType.Floor;
                case Floor:
                    return  LocationType.Room;
                default:
                    return LocationType.INVALID;
            }
        }
    }

    protected LocationType type;
    Set<Location> children;                              // Inne Location, z których składa się macierzysty Location.
    int id;                                              // unikalny identyfikator
    protected String name;                               // opcjonalna nazwa. Gdy nie ma nazwy name=null

    protected float area;                                // powierzchnia w m^2
    protected float cube;                                // kubatura w m^3
    protected float heating;                             // poziom zużycia energii ogrzewania
    protected float light;                               // łączna moc oświetlenia

    Location() {this.children = new HashSet<>(); this.type = LocationType.Root;}
    Location(int id, String name){this.id = id;this.name = name; this.children = new HashSet<>();this.type = LocationType.Root;}

    protected Location findLocationByIdUtil(int sought_id,  Location location){
        if(sought_id == location.id){
            return location;
        }
        else if(location.children.isEmpty()) return null;
        for(Location child : location.children){
            Location found = findLocationByIdUtil(sought_id, child);
            if(found != null) return found;
        }
        return null;
    }

    protected Location findParentLocationByIdUtil(int sought_id,  Location location){
        for(Location child : location.children){
            if(child.id == sought_id) return this;
            Location found = findLocationByIdUtil(sought_id, child);
            if(found != null) return found;
        }
        return null;
    }

    Location findLocationById(int sought_id){
        return findLocationByIdUtil(sought_id, this);
    }

    Location findParentLocationById(int sought_id){
        return findParentLocationByIdUtil(sought_id, this);
    }

    boolean checkIfExists(int sought_id){
        if(findLocationByIdUtil(sought_id, this) == null) return false;
            else return true;
    }

    boolean addSublocation(int id, String name, float area, float cube, float heating, float light){
        switch (this.type){
            case Root:
                    this.children.add(new Building(id, name));
                    return true;
            case Building:
                    this.children.add(new Floor(id, name));
                    return true;
            case Floor:
                    this.children.add(new Room(id, name, area, cube, heating, light));
                    return true;
            default:
                return false;
        }
    }

    void setName(String name){
        this.name = name;
    }

    boolean setArea(float area){
        if(type!=LocationType.Room) return false;
        this.area = area;
        return true;
    }

    boolean setCube(float cube){
        if(type!=LocationType.Room) return false;
        this.cube = cube;
        return true;
    }

    boolean setHeating(float heating){
        if(type!=LocationType.Room) return false;
        this.heating = heating;
        return true;
    }

    boolean setLight(float light){
        if(type!=LocationType.Room) return false;
        this.light = light;
        return true;
    }

    protected Map<String, Float> getSublocationStatsCascade(Location location){
        Map<String, Float> result = new HashMap<>();
        result.put("area", 0.0f);
        result.put("cube", 0.0f);
        result.put("heating", 0.0f);
        result.put("light", 0.0f);

        if(location.children.isEmpty()){
            if(location.type == LocationType.Room){
                if(location.area > 0) result.put("area", location.area);
                if(location.cube > 0) result.put("cube", location.cube);
                if(location.heating > 0) result.put("heating", location.heating);
                if(location.light > 0) result.put("light", location.light);
            }
        }
        else {
            for (Location child : location.children) {
                Map<String, Float> partialResult = getSublocationStatsCascade(child);
                for(Map.Entry<String, Float> item : partialResult.entrySet()){
                    result.put(item.getKey(), item.getValue() + result.get(item.getKey()));
                }
            }
        }
        return result;
    }

    private void deleteCascade(Location location){
        for (Location child : new ArrayList<>(location.children)) {
            deleteCascade(child);
        }
        location.children.clear();
    }

    void removeSublocation(int id){
        for (Location child : new ArrayList<>(this.children)) {
            if(child.id == id){
                deleteCascade(child);
                children.remove(child);
                return;
            }
        }
    }

    float getArea(){
        Map<String, Float> stats = getSublocationStatsCascade(this);
        return  stats.get("area");
    }

    float getCube(){
        Map<String, Float> stats = getSublocationStatsCascade(this);
        return  stats.get("cube");
    }

    float getHeating(){
        Map<String, Float> stats = getSublocationStatsCascade(this);
        return  stats.get("heating");
    }

    float getLight(){
        Map<String, Float> stats = getSublocationStatsCascade(this);
        return  stats.get("light");
    }
}