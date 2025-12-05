package pl.put.poznan.BuildingInfo.data.structure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.BuildingInfo.data.structure.LocationController;

import static org.junit.jupiter.api.Assertions.*;

class LocationControllerTest {
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        locationController = new LocationController();
    }

    @AfterEach
    void tearDown() {
        locationController = null;
    }

    @Test
    void testAddLocation1(){
        assertTrue(locationController.addLocation(1));
        assertFalse(locationController.AllBuildings.children.isEmpty());
    }

    @Test
    void testAddLocation2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2));
        assertTrue(locationController.addLocation(3, 0));
        assertTrue(locationController.addLocation(4, 1));
        assertFalse(locationController.addLocation(4, 1));
        assertFalse(locationController.addLocation(5, 10));
    }

    @Test
    void testAddLocation3(){
        assertTrue(locationController.addLocation(1, 0));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertFalse(locationController.addLocation(4, 3));
    }

    @Test
    void testRemoveLocation1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.removeLocation(1));
    }

    @Test
    void testRemoveLocation2(){
        assertTrue(locationController.addLocation(1));
        assertFalse(locationController.removeLocation(3));
    }

    @Test
    void testRemoveLocation3(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 1));
        assertTrue(locationController.addLocation(4, 2));
        assertTrue(locationController.removeLocation(1));
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2));
        assertTrue(locationController.addLocation(3));
        assertTrue(locationController.addLocation(4));
    }

    @Test
    void testRemoveLocation4(){
        assertFalse(locationController.addLocation(0));
    }

    @Test
    void testGetLocationName1(){
        assertTrue(locationController.addLocation(1, "Sejm"));
        assertEquals("Sejm", locationController.getLocationName(1));
    }

    @Test
    void testGetLocationName2(){
        assertTrue(locationController.addLocation(1, "Sejm"));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, "Sala plenarna", 2));
        assertEquals("Sala plenarna", locationController.getLocationName(3));
    }

    @Test
    void testGetLocationName3(){
        assertTrue(locationController.addLocation(1));
        assertNull(locationController.getLocationName(1));
    }

    @Test
    void testSetLocationName1(){
        assertTrue(locationController.addLocation(1, "Sejm"));
        assertTrue(locationController.setLocationName(1, "Cyrk"));
        assertEquals("Cyrk", locationController.getLocationName(1));
    }

    @Test
    void testSetLocationName2(){
        assertTrue(locationController.addLocation(1, "Sejm"));
        assertFalse(locationController.setLocationName(2, "Cyrk"));
    }
}