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
    void testAddLocation4(){
        assertTrue(locationController.addLocation(1, "Stołówka", 0, 1.0f, 2.0f, 3.0f, 4.0f));
        assertTrue(locationController.addLocation(2, "Stołówka", 0, 1.0f, 2.0f, 0.0f, 0.0f));
        assertFalse(locationController.addLocation(3, "Stołówka", 0, 1.0f, 2.0f, 0.0f, -1.0f));
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
        assertEquals("Sejm", locationController.getName(1));
    }

    @Test
    void testGetLocationName2(){
        assertTrue(locationController.addLocation(1, "Sejm"));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, "Sala plenarna", 2));
        assertEquals("Sala plenarna", locationController.getName(3));
    }

    @Test
    void testGetName3(){
        assertTrue(locationController.addLocation(1));
        assertNull(locationController.getName(1));
    }

    @Test
    void testSetName1(){
        assertTrue(locationController.addLocation(1, "Sejm"));
        assertTrue(locationController.setName(1, "Cyrk"));
        assertEquals("Cyrk", locationController.getName(1));
    }

    @Test
    void testSetName2(){
        assertTrue(locationController.addLocation(1, "Sejm"));
        assertFalse(locationController.setName(2, "Cyrk"));
    }

    @Test
    void testUpdateId1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.updateId(1, 2));
    }

    @Test
    void testUpdateId2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2));
        assertFalse(locationController.updateId(1, 2));
    }

    @Test
    void testUpdateId3(){
        assertTrue(locationController.addLocation(1));
        assertFalse(locationController.updateId(1, 0));
    }

    @Test
    void testUpdateId4(){
        assertFalse(locationController.updateId(4, 5));
    }

    @Test
    void testSetArea1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertFalse(locationController.setArea(0, 10.0f));
        assertFalse(locationController.setArea(1, 10.0f));
        assertFalse(locationController.setArea(2, 10.0f));
        assertTrue(locationController.setArea(3, 10.0f));
        assertFalse(locationController.setArea(4, 10.0f));
    }

    @Test
    void testSetArea2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertTrue(locationController.setArea(3, 10.0f));
        assertTrue(locationController.setArea(3, 20.0f));
        assertFalse(locationController.setArea(3, -20.0f));
    }

    @Test
    void testSetCube1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertFalse(locationController.setCube(0, 10.0f));
        assertFalse(locationController.setCube(1, 10.0f));
        assertFalse(locationController.setCube(2, 10.0f));
        assertTrue(locationController.setCube(3, 10.0f));
        assertFalse(locationController.setCube(4, 10.0f));
    }

    @Test
    void testSetCube2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertTrue(locationController.setCube(3, 10.0f));
        assertTrue(locationController.setCube(3, 20.0f));
        assertFalse(locationController.setCube(3, -20.0f));
    }

    @Test
    void testSetHeating1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertFalse(locationController.setHeating(0, 10.0f));
        assertFalse(locationController.setHeating(1, 10.0f));
        assertFalse(locationController.setHeating(2, 10.0f));
        assertTrue(locationController.setHeating(3, 10.0f));
        assertFalse(locationController.setHeating(4, 10.0f));
    }

    @Test
    void testSetHeating2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertTrue(locationController.setHeating(3, 10.0f));
        assertTrue(locationController.setHeating(3, 20.0f));
        assertFalse(locationController.setHeating(3, -20.0f));
    }

    @Test
    void testSetLight1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertFalse(locationController.setLight(0, 10.0f));
        assertFalse(locationController.setLight(1, 10.0f));
        assertFalse(locationController.setLight(2, 10.0f));
        assertTrue(locationController.setLight(3, 10.0f));
        assertFalse(locationController.setLight(4, 10.0f));
    }

    @Test
    void testSetLight2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertTrue(locationController.setLight(3, 10.0f));
        assertTrue(locationController.setLight(3, 20.0f));
        assertFalse(locationController.setLight(3, -20.0f));
    }

    @Test
    void testGetArea1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertTrue(locationController.setArea(3, 10.0f));
        assertEquals(-1.0f, locationController.getArea(4));
        assertEquals(10.0f, locationController.getArea(3));
        assertEquals(10.0f, locationController.getArea(2));
        assertEquals(10.0f, locationController.getArea(1));
        assertEquals(10.0f, locationController.getArea(0));
    }

    @Test
    void testGetArea2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(10, 1));
        assertTrue(locationController.addLocation(11, 10));
        assertTrue(locationController.addLocation(12, 10));
        assertTrue(locationController.addLocation(13, 10));
        assertTrue(locationController.addLocation(20, 1));
        assertTrue(locationController.addLocation(21, 20));
        assertTrue(locationController.addLocation(22, 20));
        assertTrue(locationController.addLocation(30, 1));
        assertTrue(locationController.addLocation(100, 0));
        assertTrue(locationController.addLocation(110, 100));
        assertTrue(locationController.addLocation(111, 110));

        assertFalse(locationController.setArea(1, 10.0f));
        assertFalse(locationController.setArea(10, 1.0f));
        assertTrue(locationController.setArea(11, 5.5f));
        assertTrue(locationController.setArea(12, 1.5f));
        assertTrue(locationController.setArea(13, 20.0f));
        assertFalse(locationController.setArea(20, 20.0f));
        assertTrue(locationController.setArea(21, 12.5f));
        assertTrue(locationController.setArea(22, 5.0f));
        assertFalse(locationController.setArea(30, 30.0f));
        assertFalse(locationController.setArea(100, 30.0f));
        assertFalse(locationController.setArea(110, 30.0f));
        assertTrue(locationController.setArea(111, 9.0f));

        assertEquals(53.5f,  locationController.getArea(0));
        assertEquals(44.5f,  locationController.getArea(1));
        assertEquals(27.0f,  locationController.getArea(10));
        assertEquals(5.5f,  locationController.getArea(11));
        assertEquals(1.5f,  locationController.getArea(12));
        assertEquals(20.0f,  locationController.getArea(13));
        assertEquals(17.5f,  locationController.getArea(20));
        assertEquals(12.5f,  locationController.getArea(21));
        assertEquals(5.0f,  locationController.getArea(22));
        assertEquals(0.0f,  locationController.getArea(30));
        assertEquals(9.0f,  locationController.getArea(100));
        assertEquals(9.0f,  locationController.getArea(110));
        assertEquals(9.0f,  locationController.getArea(111));
    }

    @Test
    void testGetArea3(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 1));
        assertTrue(locationController.addLocation(4, 2));
        assertEquals(0.0f, locationController.getArea(0));
        assertEquals(0.0f, locationController.getArea(1));
        assertEquals(0.0f, locationController.getArea(2));
        assertEquals(0.0f, locationController.getArea(3));
        assertEquals(0.0f, locationController.getArea(4));
    }

    @Test
    void testGetCube1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertTrue(locationController.setCube(3, 10.0f));
        assertEquals(-1.0f, locationController.getCube(4));
        assertEquals(10.0f, locationController.getCube(3));
        assertEquals(10.0f, locationController.getCube(2));
        assertEquals(10.0f, locationController.getCube(1));
        assertEquals(10.0f, locationController.getCube(0));
    }

    @Test
    void testGetCube2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(10, 1));
        assertTrue(locationController.addLocation(11, 10));
        assertTrue(locationController.addLocation(12, 10));
        assertTrue(locationController.addLocation(13, 10));
        assertTrue(locationController.addLocation(20, 1));
        assertTrue(locationController.addLocation(21, 20));
        assertTrue(locationController.addLocation(22, 20));
        assertTrue(locationController.addLocation(30, 1));
        assertTrue(locationController.addLocation(100, 0));
        assertTrue(locationController.addLocation(110, 100));
        assertTrue(locationController.addLocation(111, 110));

        assertFalse(locationController.setCube(1, 10.0f));
        assertFalse(locationController.setCube(10, 1.0f));
        assertTrue(locationController.setCube(11, 5.5f));
        assertTrue(locationController.setCube(12, 1.5f));
        assertTrue(locationController.setCube(13, 20.0f));
        assertFalse(locationController.setCube(20, 20.0f));
        assertTrue(locationController.setCube(21, 12.5f));
        assertTrue(locationController.setCube(22, 5.0f));
        assertFalse(locationController.setCube(30, 30.0f));
        assertFalse(locationController.setCube(100, 30.0f));
        assertFalse(locationController.setCube(110, 30.0f));
        assertTrue(locationController.setCube(111, 9.0f));

        assertEquals(53.5f,  locationController.getCube(0));
        assertEquals(44.5f,  locationController.getCube(1));
        assertEquals(27.0f,  locationController.getCube(10));
        assertEquals(5.5f,  locationController.getCube(11));
        assertEquals(1.5f,  locationController.getCube(12));
        assertEquals(20.0f,  locationController.getCube(13));
        assertEquals(17.5f,  locationController.getCube(20));
        assertEquals(12.5f,  locationController.getCube(21));
        assertEquals(5.0f,  locationController.getCube(22));
        assertEquals(0.0f,  locationController.getCube(30));
        assertEquals(9.0f,  locationController.getCube(100));
        assertEquals(9.0f,  locationController.getCube(110));
        assertEquals(9.0f,  locationController.getCube(111));
    }

    @Test
    void testGetCube3(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 1));
        assertTrue(locationController.addLocation(4, 2));
        assertEquals(0.0f, locationController.getCube(0));
        assertEquals(0.0f, locationController.getCube(1));
        assertEquals(0.0f, locationController.getCube(2));
        assertEquals(0.0f, locationController.getCube(3));
        assertEquals(0.0f, locationController.getCube(4));
    }

    @Test
    void testGetHeating1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertTrue(locationController.setHeating(3, 10.0f));
        assertEquals(-1.0f, locationController.getHeating(4));
        assertEquals(10.0f, locationController.getHeating(3));
        assertEquals(10.0f, locationController.getHeating(2));
        assertEquals(10.0f, locationController.getHeating(1));
        assertEquals(10.0f, locationController.getHeating(0));
    }

    @Test
    void testGetHeating2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(10, 1));
        assertTrue(locationController.addLocation(11, 10));
        assertTrue(locationController.addLocation(12, 10));
        assertTrue(locationController.addLocation(13, 10));
        assertTrue(locationController.addLocation(20, 1));
        assertTrue(locationController.addLocation(21, 20));
        assertTrue(locationController.addLocation(22, 20));
        assertTrue(locationController.addLocation(30, 1));
        assertTrue(locationController.addLocation(100, 0));
        assertTrue(locationController.addLocation(110, 100));
        assertTrue(locationController.addLocation(111, 110));

        assertFalse(locationController.setHeating(1, 10.0f));
        assertFalse(locationController.setHeating(10, 1.0f));
        assertTrue(locationController.setHeating(11, 5.5f));
        assertTrue(locationController.setHeating(12, 1.5f));
        assertTrue(locationController.setHeating(13, 20.0f));
        assertFalse(locationController.setHeating(20, 20.0f));
        assertTrue(locationController.setHeating(21, 12.5f));
        assertTrue(locationController.setHeating(22, 5.0f));
        assertFalse(locationController.setHeating(30, 30.0f));
        assertFalse(locationController.setHeating(100, 30.0f));
        assertFalse(locationController.setHeating(110, 30.0f));
        assertTrue(locationController.setHeating(111, 9.0f));

        assertEquals(53.5f,  locationController.getHeating(0));
        assertEquals(44.5f,  locationController.getHeating(1));
        assertEquals(27.0f,  locationController.getHeating(10));
        assertEquals(5.5f,  locationController.getHeating(11));
        assertEquals(1.5f,  locationController.getHeating(12));
        assertEquals(20.0f,  locationController.getHeating(13));
        assertEquals(17.5f,  locationController.getHeating(20));
        assertEquals(12.5f,  locationController.getHeating(21));
        assertEquals(5.0f,  locationController.getHeating(22));
        assertEquals(0.0f,  locationController.getHeating(30));
        assertEquals(9.0f,  locationController.getHeating(100));
        assertEquals(9.0f,  locationController.getHeating(110));
        assertEquals(9.0f,  locationController.getHeating(111));
    }

    @Test
    void testGetHeating3(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 1));
        assertTrue(locationController.addLocation(4, 2));
        assertEquals(0.0f, locationController.getHeating(0));
        assertEquals(0.0f, locationController.getHeating(1));
        assertEquals(0.0f, locationController.getHeating(2));
        assertEquals(0.0f, locationController.getHeating(3));
        assertEquals(0.0f, locationController.getHeating(4));
    }

    @Test
    void testGetLight1(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 2));
        assertTrue(locationController.setLight(3, 10.0f));
        assertEquals(-1.0f, locationController.getLight(4));
        assertEquals(10.0f, locationController.getLight(3));
        assertEquals(10.0f, locationController.getLight(2));
        assertEquals(10.0f, locationController.getLight(1));
        assertEquals(10.0f, locationController.getLight(0));
    }

    @Test
    void testGetLight2(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(10, 1));
        assertTrue(locationController.addLocation(11, 10));
        assertTrue(locationController.addLocation(12, 10));
        assertTrue(locationController.addLocation(13, 10));
        assertTrue(locationController.addLocation(20, 1));
        assertTrue(locationController.addLocation(21, 20));
        assertTrue(locationController.addLocation(22, 20));
        assertTrue(locationController.addLocation(30, 1));
        assertTrue(locationController.addLocation(100, 0));
        assertTrue(locationController.addLocation(110, 100));
        assertTrue(locationController.addLocation(111, 110));

        assertFalse(locationController.setLight(1, 10.0f));
        assertFalse(locationController.setLight(10, 1.0f));
        assertTrue(locationController.setLight(11, 5.5f));
        assertTrue(locationController.setLight(12, 1.5f));
        assertTrue(locationController.setLight(13, 20.0f));
        assertFalse(locationController.setLight(20, 20.0f));
        assertTrue(locationController.setLight(21, 12.5f));
        assertTrue(locationController.setLight(22, 5.0f));
        assertFalse(locationController.setLight(30, 30.0f));
        assertFalse(locationController.setLight(100, 30.0f));
        assertFalse(locationController.setLight(110, 30.0f));
        assertTrue(locationController.setLight(111, 9.0f));

        assertEquals(53.5f,  locationController.getLight(0));
        assertEquals(44.5f,  locationController.getLight(1));
        assertEquals(27.0f,  locationController.getLight(10));
        assertEquals(5.5f,  locationController.getLight(11));
        assertEquals(1.5f,  locationController.getLight(12));
        assertEquals(20.0f,  locationController.getLight(13));
        assertEquals(17.5f,  locationController.getLight(20));
        assertEquals(12.5f,  locationController.getLight(21));
        assertEquals(5.0f,  locationController.getLight(22));
        assertEquals(0.0f,  locationController.getLight(30));
        assertEquals(9.0f,  locationController.getLight(100));
        assertEquals(9.0f,  locationController.getLight(110));
        assertEquals(9.0f,  locationController.getLight(111));
    }

    @Test
    void testGetLight3(){
        assertTrue(locationController.addLocation(1));
        assertTrue(locationController.addLocation(2, 1));
        assertTrue(locationController.addLocation(3, 1));
        assertTrue(locationController.addLocation(4, 2));
        assertEquals(0.0f, locationController.getLight(0));
        assertEquals(0.0f, locationController.getLight(1));
        assertEquals(0.0f, locationController.getLight(2));
        assertEquals(0.0f, locationController.getLight(3));
        assertEquals(0.0f, locationController.getLight(4));
    }
}