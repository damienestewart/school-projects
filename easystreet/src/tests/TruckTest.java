/**
 * TCSS 305 - Winter 2015
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Damiene Stewart
 * @version 22 January 2015
 */
public class TruckTest {

    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    /**
     * Stores the truck object being used for tests.
     */
    private Truck myTruck;
    
    /**
     * Sets up Truck for each test.
     */
    @Before
    public void setUp() {
        myTruck = new Truck(20, 21, Direction.NORTH);
    }

    /**
     * Test method for {@link model.Truck#canPass(model.Terrain, model.Light)}.
     */
    @Test
    public void testCanPass() {
        for (final Light l : Light.values()) {
            for (final Terrain t : Terrain.values()) {
                if (t == Terrain.STREET || t == Terrain.LIGHT) {
                    assertTrue("Trucks should be able to pass " + t
                               + ", with light " + l,
                               myTruck.canPass(t, l));
                } else {
                    assertFalse("Trucks should not be able to pass" + t,
                                myTruck.canPass(t, l));
                }
            }
        }
    }

    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnlyValidTerrainNorth() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.WALL);
        
        for (final Terrain t : Terrain.values()) {
            neighbors.put(Direction.NORTH, t);
            
            if (t == Terrain.LIGHT || t == Terrain.STREET) {
                assertTrue("Did not choose valid terrain!", 
                           myTruck.chooseDirection(neighbors).equals(Direction.NORTH));
            } else {
                assertFalse("Did not choose valid terrain!", 
                           myTruck.chooseDirection(neighbors).equals(Direction.NORTH));
            }
        }   
    }
    
    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnlyValidTerrainWest() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.WALL);
        
        for (final Terrain t : Terrain.values()) {
            neighbors.put(Direction.WEST, t);
            
            if (t == Terrain.LIGHT || t == Terrain.STREET) {
                assertTrue("Did not choose valid terrain!", 
                           myTruck.chooseDirection(neighbors).equals(Direction.WEST));
            } else {
                assertFalse("Did not choose valid terrain!", 
                           myTruck.chooseDirection(neighbors).equals(Direction.WEST));
            }
        }   
    }
    
    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnlyValidTerrainEast() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.WALL);
        
        for (final Terrain t : Terrain.values()) {
            neighbors.put(Direction.EAST, t);
            
            if (t == Terrain.LIGHT || t == Terrain.STREET) {
                assertTrue("Did not choose valid terrain!", 
                           myTruck.chooseDirection(neighbors).equals(Direction.EAST));
            } else {
                assertFalse("Did not choose valid terrain!", 
                           myTruck.chooseDirection(neighbors).equals(Direction.EAST));
            }
        }   
    }
    
    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionNoCenter() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        boolean hasReturnedCenter = false;
        final Random rand = new Random();
        int i = 0;
        while (i < TRIES_FOR_RANDOMNESS) {
            i++;
            neighbors.put(Direction.NORTH, 
                          Terrain.values()[rand.nextInt(Terrain.values().length)]);
            neighbors.put(Direction.SOUTH, 
                          Terrain.values()[rand.nextInt(Terrain.values().length)]);
            neighbors.put(Direction.EAST, 
                          Terrain.values()[rand.nextInt(Terrain.values().length)]);
            neighbors.put(Direction.WEST, 
                          Terrain.values()[rand.nextInt(Terrain.values().length)]);
            neighbors.put(Direction.CENTER, 
                          Terrain.values()[rand.nextInt(Terrain.values().length)]);
            
            if (myTruck.chooseDirection(neighbors) == Direction.CENTER) {
                hasReturnedCenter = true;
            }   
        }
        
        assertFalse("Should never return center!", hasReturnedCenter);
    }
     
    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionRandom() {
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        neighbors.put(Direction.SOUTH, Terrain.WALL);
        
        int i = 0;
        boolean hasSeenNorth = false;
        boolean hasSeenWest = false;
        boolean hasSeenEast = false;
        
        while (i < TRIES_FOR_RANDOMNESS) {
            i++;
            
            if (myTruck.chooseDirection(neighbors) == Direction.NORTH) {
                hasSeenNorth = true;
            }
            
            if (myTruck.chooseDirection(neighbors) == Direction.WEST) {
                hasSeenWest = true;
            }
            
            if (myTruck.chooseDirection(neighbors) == Direction.EAST) {
                hasSeenEast = true;
            }
        }
        
        assertTrue("This method is not random!", hasSeenNorth && hasSeenWest
                   && hasSeenEast);
        
    }
    
    /**
     * Test method for {@link model.Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionReverse() {   
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.WALL);
            
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        assertEquals("Truck did not reverse.", 
                     Direction.SOUTH, myTruck.chooseDirection(neighbors));
        
        neighbors.put(Direction.SOUTH, Terrain.LIGHT);
        assertEquals("Truck did not reverse.", 
                     Direction.SOUTH, myTruck.chooseDirection(neighbors));
        
        neighbors.put(Direction.WEST, Terrain.STREET);
        
        for (final Terrain t : Terrain.values()) {
            neighbors.put(Direction.SOUTH, t);
            
            assertNotEquals("Truck should not reverse.", 
                     Direction.SOUTH, myTruck.chooseDirection(neighbors));
        }       
    }
}
