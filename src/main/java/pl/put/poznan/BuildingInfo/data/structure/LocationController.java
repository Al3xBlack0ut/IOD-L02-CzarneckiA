package pl.put.poznan.BuildingInfo.data.structure;

import java.util.ArrayList;


/**
 * LocationFinder - pomocniczna klasa wyszukująca obiekt Location w schemacie.
 *
 * @author PiotrRem
 */
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

/**
 * LocationDeleter - pomocniczna klasa usuwająca lokację o podanym id ze schematu.
 * Wraz z usuwaną relacją usuwane są wszystkie jej dzieci.
 *
 * @author PiotrRem
 */
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

/**
 * LocationController - klasa do obsługi hierarchicznego modelu danych:
 * (korzeń)->Buiding->Floor->Room
 * LocationController służy do uzyskiwania danych ze schematu, ich wstawiania, modyfikacji i usuwania.
 *
 * @author PiotrRem
 */
public class LocationController {
    Location AllBuildings; // pseudo-lokacja - w niej znajdują się wszystkie budynki

    /**
     * Konstruktor bezargumentowy. Tworzy pseudo-lokację, będącą 'korzeniem' schematu danych.
     * Jako children 'korzenia' figurują obiekty Building.
     * id 'korzenia' to 0, więc nie może to być id żadnej innej lokacji.
     */
    public LocationController() {
        AllBuildings = new Location(0, "root");
    }

    /**
     * Dodaje lokację do schematu. Na podstawie id nadrzędnego obiektu Location ustalany jest typ: Building, Floor, Room.
     * @param id - unikalny identyfikator
     * @param name - opcjonalna nazwa
     * @param parent_id - id obiektu Location, w którym ma się znaleźć dodawany obiekt. Nie może to być id obiektu Room. Dla Building = 0
     * @param area - powierzchnia Room
     * @param cube - kubatura Room
     * @param heating - energia ogrzewania Room
     * @param light - moc oświetlenia Room
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
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

    /**
     * Dodaje Location do schematu, ale nie wymusza podawania wielu (zbędnych) argumentów.
     * @param id - unikalny identyfikator
     * @param name - opcjonalna nazwa
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
    public boolean addLocation(int id, String name){
        return addLocation(id, name, 0, -1, -1, -1, -1);
    }

    /**
     * Dodaje Location do schematu, ale nie wymusza podawania wielu (zbędnych) argumentów. Name = null. Pola Room = -1. Parent_id = 0
     * @param id - unikalny identyfikator
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
    public boolean addLocation(int id){
        return addLocation(id, null, 0, -1, -1, -1, -1);
    }

    /**
     * Dodaje Location do schematu, ale nie wymusza podawania wielu (zbędnych) argumentów. Name = null. Pola Room = -1
     * @param id - unikalny identyfikator
     * @param parent_id - id obiektu Location, w którym ma się znaleźć dodawany obiekt. Nie może to być id obiektu Room. Dla Building = 0
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
    public boolean addLocation(int id, int parent_id){
        return addLocation(id, null, parent_id, -1, -1, -1, -1);
    }

    /**
     * Dodaje Location do schematu, ale nie wymusza podawania wielu (zbędnych) argumentów. Pola Room = -1
     * @param id - unikalny identyfikator
     * @param name - opcjonalna nazwa
     * @param parent_id - id obiektu Location, w którym ma się znaleźć dodawany obiekt. Nie może to być id obiektu Room. Dla Building = 0
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
    public boolean addLocation(int id, String name, int parent_id){
        return addLocation(id, name, parent_id, -1, -1, -1, -1);
    }

    /**
     * Usuwa Location wraz z wszystkimi znajdującym się w niej Floor i Room.
     * @param id - identyfikator obiektu do usunięcia
     * @return true - jeżeli usunięcie obiektu powiedzie się, false - w przeciwnym razie
     */
    public boolean removeLocation(int id){
        LocationDeleter locationDeleter = new LocationDeleter(id, AllBuildings);
        return locationDeleter.deleteLocationById();
    }

    /**
     * Zwraca pole name obiektu Location
     * @param id - identyfikator obiektu
     * @return nazwa Lokacji lub null, jeżeli Location o podanym id nie istnieje lub nazwa nie została podana
     */
    public String getLocationName(int id){
        LocationFinder locationFinder = new LocationFinder(AllBuildings, id);
        Location location = locationFinder.findLocationById();
        return location.name;
    }

    /**
     * Ustawia pole name obiektu Location
     * @param id - identyfikator obiektu
     * @param name - nazwa do nadania
     * @return true - jeżeli zostanie nadana; false - w przeciwnym razie (np. gdy Location o takim id nie istnieje)
     */
    public boolean setLocationName(int id, String name){
        LocationFinder locationFinder = new LocationFinder(AllBuildings, id);
        Location location = locationFinder.findLocationById();
        if(location == null) return false;
        location.name = name;
        return true;
    }
}