package pl.put.poznan.BuildingInfo.data.structure;

import java.util.ArrayList;

/**
 * LocationSelecter - pomocnicza klasa wykonująca operację selekcji na zbiorze danych.
 *
 * @author PiotrRem
 */
class LocationSelecter{
    Location rootLocation;
    int id;

    LocationSelecter(Location location, int id) {
        this.rootLocation = location;
        this.id = id;
    }

    float getArea(){
        Location found =  rootLocation.findLocationById(id);
        if(found == null) return -1;
        return found.getArea();
    }

    float getCube(){
        Location found =  rootLocation.findLocationById(id);
        if(found == null) return -1;
        return found.getCube();
    }

    float getHeating(){
        Location found =  rootLocation.findLocationById(id);
        if(found == null) return -1;
        return found.getHeating();
    }

    float getLight(){
        Location found =  rootLocation.findLocationById(id);
        if(found == null) return -1;
        return found.getLight();
    }

    String getLocationName(){
        Location location = rootLocation.findLocationById(id);
        if(location == null) return null;
        return location.name;
    }
}

/**
 * LocationAdder - pomocnicza klasa wykonująca operację INSERT na zbiorze danych.
 *
 * @author PiotrRem
 */
class LocationAdder{
    int id;
    Location rootLocation;

    LocationAdder(Location location, int id) {
        this.rootLocation = location;
        this.id = id;
    }

    boolean addLocation(int id, String name, int parent_id, float area, float cube, float heating, float light){
        if(rootLocation.checkIfExists(id)) return false;

        Location location = rootLocation.findLocationById(parent_id);
        if(location == null) return false;
        return location.addSublocation(id, name, area, cube, heating, light);
    }

}

/**
 * LocationDeleter - pomocniczna klasa usuwająca obiekt Location o podanym id ze schematu.
 * Wraz z usuwaną relacją usuwane są wszystkie jej dzieci.
 *
 * @author PiotrRem
 */
class LocationDeleter{
    int id;
    Location rootLocation;
    LocationDeleter(int id, Location location) {
        this.id = id;
        this.rootLocation = location;
    }

    boolean deleteLocationById(){
        Location found =  rootLocation.findLocationById(id);
        if(found==null)  return false;
        Location parent = rootLocation.findParentLocationById(id);
        if(parent == null) return false;
        parent.removeSublocation(id);
        return true;
    }
}

/**
 * LocationUpdater - pomocnicza klasa wykonująca operację modyfikacji na zbiorze danych.
 *
 * @author PiotrRem
 */
class LocationUpdater{
    int id;
    Location rootLocation;

    LocationUpdater(Location location, int id) {
        this.rootLocation = location;
        this.id = id;
    }

    boolean setArea(float value){
        if(value < 0) return false;
        Location found = rootLocation.findLocationById(id);
        if(found==null) return false;
        return found.setArea(value);
    }

    boolean setCube(float value){
        if(value < 0) return false;
        Location found = rootLocation.findLocationById(id);
        if(found==null) return false;
        return found.setCube(value);
    }

    boolean setHeating(float value){
        if(value < 0) return false;
        Location found = rootLocation.findLocationById(id);
        if(found==null) return false;
        return found.setHeating(value);
    }

    boolean setLight(float value){
        if(value < 0) return false;
        Location found = rootLocation.findLocationById(id);
        if(found==null) return false;
        return found.setLight(value);
    }

    boolean setName(String value){
        Location found = rootLocation.findLocationById(id);
        if(found == null) return false;
        found.setName(value);
        return true;
    }

    boolean setId(int value){
        if(rootLocation.checkIfExists(value)) return false;
        Location found = rootLocation.findLocationById(id);
        if(found == null) return false;
        found.id = value;
        return true;
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
        LocationAdder  locationAdder = new LocationAdder(AllBuildings, id);
        return locationAdder.addLocation(id, name, parent_id, area, cube, heating, light);
    }

    /**
     * Dodaje Location do schematu, ale nie wymusza podawania wielu (zbędnych) argumentów.
     * @param id - unikalny identyfikator
     * @param name - opcjonalna nazwa
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
    public boolean addLocation(int id, String name){
        LocationAdder  locationAdder = new LocationAdder(AllBuildings, id);
        return locationAdder.addLocation(id, name, 0, -1, -1, -1, -1);
    }

    /**
     * Dodaje Location do schematu, ale nie wymusza podawania wielu (zbędnych) argumentów. Name = null. Pola Room = -1. Parent_id = 0
     * @param id - unikalny identyfikator
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
    public boolean addLocation(int id){
        LocationAdder  locationAdder = new LocationAdder(AllBuildings, id);
        return locationAdder.addLocation(id, null, 0, -1, -1, -1, -1);
    }

    /**
     * Dodaje Location do schematu, ale nie wymusza podawania wielu (zbędnych) argumentów. Name = null. Pola Room = -1
     * @param id - unikalny identyfikator
     * @param parent_id - id obiektu Location, w którym ma się znaleźć dodawany obiekt. Nie może to być id obiektu Room. Dla Building = 0
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
    public boolean addLocation(int id, int parent_id){
        LocationAdder  locationAdder = new LocationAdder(AllBuildings, id);
        return locationAdder.addLocation(id, null, parent_id, -1, -1, -1, -1);
    }

    /**
     * Dodaje Location do schematu, ale nie wymusza podawania wielu (zbędnych) argumentów. Pola Room = -1
     * @param id - unikalny identyfikator
     * @param name - opcjonalna nazwa
     * @param parent_id - id obiektu Location, w którym ma się znaleźć dodawany obiekt. Nie może to być id obiektu Room. Dla Building = 0
     * @return true - jeżeli dodanie obiektu do schematu powiedzie się; false - w przeciwnym razie.
     */
    public boolean addLocation(int id, String name, int parent_id){
        LocationAdder  locationAdder = new LocationAdder(AllBuildings, id);
        return locationAdder.addLocation(id, name, parent_id, -1, -1, -1, -1);
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
    public String getName(int id){
        LocationSelecter locationSelecter = new LocationSelecter(AllBuildings, id);
        return locationSelecter.getLocationName();
    }

    /**
     * Ustawia pole name obiektu Location
     * @param id - identyfikator obiektu
     * @param name - nazwa do nadania
     * @return true - jeżeli nazwa zostanie nadana; false - w przeciwnym razie (np. gdy Location o takim id nie istnieje)
     */
    public boolean setName(int id, String name){
        LocationUpdater locationUpdater = new LocationUpdater(AllBuildings, id);
        return locationUpdater.setName(name);
    }

    /**
     * Zmienia id Location na inne.
     * @param old_id - stary identyfikator Location
     * @param new_id - nowy identyfikator Location
     * @return true - jeżeli zmiana id się powiedzie; false - w przeciwnym razie
     */
    public boolean updateId(int old_id, int new_id){
        LocationUpdater locationUpdater = new LocationUpdater(AllBuildings, old_id);
        return locationUpdater.setId(new_id);
    }

    /**
     * Ustawia pole area w obiekcie Room.
     * @param id - id obiektu Room
     * @param area - wartość area do ustawienia
     * @return true - jeżeli zmiana id się powiedzie; false - w przeciwnym razie (np. gdy podano id obiektu Floor lub Building, ale podano niestniejące id)
     */
    public boolean setArea(int id, float area){
        LocationUpdater locationUpdater = new LocationUpdater(AllBuildings, id);
        return locationUpdater.setArea(area);
    }

    /**
     * Zwraca pole area obiektu Location. Dla Floor zwracana jest suma pól area wszystkich podrzędnych Room; dla Building zwracana jest suma pól area wszystkich podrzędnych Floor.
     * @param id - identyfikator Location
     * @return - powierzchnia obiektu Location o podanym id.
     */
    public float getArea(int id){
        LocationSelecter locationSelecter = new LocationSelecter(AllBuildings, id);
        return locationSelecter.getArea();
    }

    /**
     * Ustawia pole cube w obiekcie Room.
     * @param id - id obiektu Room
     * @param cube - wartość cube do ustawienia
     * @return true - jeżeli zmiana id się powiedzie; false - w przeciwnym razie (np. gdy podano id obiektu Floor lub Building, ale podano niestniejące id)
     */
    public boolean setCube(int id, float cube){
        LocationUpdater locationUpdater = new LocationUpdater(AllBuildings, id);
        return locationUpdater.setCube(cube);
    }

    /**
     * Zwraca pole cube obiektu Location. Dla Floor zwracana jest suma pól cube wszystkich podrzędnych Room; dla Building zwracana jest suma pól cube wszystkich podrzędnych Floor.
     * @param id - identyfikator Location
     * @return - kubatura obiektu Location o podanym id.
     */
    public float getCube(int id){
        LocationSelecter locationSelecter = new LocationSelecter(AllBuildings, id);
        return locationSelecter.getCube();
    }

    /**
     * Ustawia pole heating w obiekcie Room.
     * @param id - id obiektu Room
     * @param heating - wartość heating do ustawienia
     * @return true - jeżeli zmiana id się powiedzie; false - w przeciwnym razie (np. gdy podano id obiektu Floor lub Building, ale podano niestniejące id)
     */
    public boolean setHeating(int id, float heating){
        LocationUpdater locationUpdater = new LocationUpdater(AllBuildings, id);
        return locationUpdater.setHeating(heating);
    }

    /**
     * Zwraca pole heating obiektu Location. Dla Floor zwracana jest suma pól heating wszystkich podrzędnych Room; dla Building zwracana jest suma pól heating wszystkich podrzędnych Floor.
     * @param id - identyfikator Location
     * @return - poziom zużycia energii obiektu Location o podanym id.
     */
    public float getHeating(int id){
        LocationSelecter locationSelecter = new LocationSelecter(AllBuildings, id);
        return locationSelecter.getHeating();
    }

    /**
     * Ustawia pole light w obiekcie Room.
     * @param id - id obiektu Room
     * @param light - wartość light do ustawienia
     * @return true - jeżeli zmiana id się powiedzie; false - w przeciwnym razie (np. gdy podano id obiektu Floor lub Building, ale podano niestniejące id)
     */
    public boolean setLight(int id, float light){
        LocationUpdater locationUpdater = new LocationUpdater(AllBuildings, id);
        return locationUpdater.setLight(light);
    }

    /**
     * Zwraca pole light obiektu Location. Dla Floor zwracana jest suma pól light wszystkich podrzędnych Room; dla Building zwracana jest suma pól light wszystkich podrzędnych Floor.
     * @param id - identyfikator Location
     * @return - łączna moc oświetlenia obiektu Location o podanym id.
     */
    public float getLight(int id){
        LocationSelecter locationSelecter = new LocationSelecter(AllBuildings, id);
        return locationSelecter.getLight();
    }
}