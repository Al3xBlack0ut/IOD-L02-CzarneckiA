package pl.put.poznan.BuildingInfo.data.structure;

import java.util.ArrayList;

class LocationFinder{
    Location location;
    int id;
    int found_depth;

    LocationFinder(Location location, int id) {
        this.location = location;
        this.id = id;
        this.found_depth = 0;
    }

    private Location findLocationByIdUtil(int id,  Location location, int depth){
        if(id == location.id){
            found_depth = depth;
            return location;
        }
        else if(location.children == null) return null;

        for(Location child : location.children){
            Location found = findLocationByIdUtil(id, child, depth+1);
            if(found != null) return found;
        }
        return null;
    }

    Location findLocationById(){
        return findLocationByIdUtil(id, location, 0);
    }

    boolean checkIfExists(){
        if(findLocationByIdUtil(id, location, 0) == null) return false;
        else return true;
    }
}

class LocationDeleter{
    Location location;
    int id;
    LocationDeleter(int id, Location location) {
        this.location = location;
        this.id = id;
    }

    private void deleteCascade(Location location){
        for (Location child : new ArrayList<>(location.children)) {
            deleteCascade(child);
        }
        location.children.clear();
    }

    private boolean deleteLocationByIdUtil(Location location){
        for (Location child : new ArrayList<>(location.children)) {
            if (child.id == id) {
                deleteCascade(child);
                location.children.remove(child);
                return true;
            }
            if (deleteLocationByIdUtil(child)) return true;
        }
        return false;
    }

    boolean deleteLocationById(){
        return deleteLocationByIdUtil(location);
    }
}

public class LocationController {
    Location AllBuildings;

    public LocationController() {
        AllBuildings = new Location(0, "root");
    }

    public boolean addLocation(int id, String name, int parent_id, float area, float cube, float heating, float light){
        LocationFinder locationFinder = new LocationFinder(AllBuildings, id);
        if(locationFinder.checkIfExists()) return false;

        locationFinder = new LocationFinder(AllBuildings, parent_id);
        Location location = locationFinder.findLocationById();
        if(location == null) return false;
        else if(locationFinder.found_depth == 0) location.children.add(new Building(id, name));
        else if(locationFinder.found_depth == 1) location.children.add(new Floor(id, name));
        else if(locationFinder.found_depth == 2) location.children.add(new Room(id, name, area, cube, heating, light));
        else return false;
        return true;
    }


    public boolean addLocation(int id, String name){
        return addLocation(id, name, 0, -1, -1, -1, -1);
    }

    public boolean addLocation(int id){
        return addLocation(id, null, 0, -1, -1, -1, -1);
    }

    public boolean addLocation(int id, int parent_id){
        return addLocation(id, null, parent_id, -1, -1, -1, -1);
    }

    public boolean addLocation(int id, String name, int parent_id){
        return addLocation(id, name, parent_id, -1, -1, -1, -1);
    }

    public boolean removeLocation(int id){
        LocationDeleter locationDeleter = new LocationDeleter(id, AllBuildings);
        return locationDeleter.deleteLocationById();
    }

    public String getLocationName(int id){
        LocationFinder locationFinder = new LocationFinder(AllBuildings, id);
        Location location = locationFinder.findLocationById();
        return location.name;
    }

    public boolean setLocationName(int id, String name){
        LocationFinder locationFinder = new LocationFinder(AllBuildings, id);
        Location location = locationFinder.findLocationById();
        if(location == null) return false;
        location.name = name;
        return true;
    }
}