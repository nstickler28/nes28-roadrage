
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;
import org.junit.Test;

/**
 * Unit test for Truck class.
 * 
 * @author Nathan Stickler
 * @version 10/22/19
 */
public class TruckTest {

    /**
     *  The number of times to repeat a test to have a high probability
     *  that all random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 100;

    /** Test method for Truck constructor. */
    @Test
    public void testTruckConstructor() {
        final Truck t = new Truck(10, 11, Direction.NORTH);

        assertEquals("Truck x coordinate not initialized correctly!", 10, t.getX());
        assertEquals("Truck y coordinate not initialized coorectly!", 11, t.getY());
        assertEquals("Truck direction not initialized correctly!", Direction.NORTH,
                     t.getDirection());
        assertEquals("Truck death time not initialized correctly!", 0, t.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", t.isAlive());
    }

    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(10, 11, Direction.NORTH);

        t.setX(12);
        assertEquals("Truck setX failed!", 12, t.getX());
        t.setY(13);
        assertEquals("Truck setY failed!", 13, t.getY());
        t.setDirection(Direction.SOUTH);
        assertEquals("Human setDirection failed!", Direction.SOUTH, t.getDirection());
    }
 
    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        for (final Terrain destinationTerrain : Terrain.values()) {
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.CROSSWALK) {
                    if (currentLightCondition == Light.RED) {
                        assertFalse("Truck should NOT be able to pass CROSSWALK"
                                    + ", with light " + currentLightCondition,
                                    truck.canPass(destinationTerrain, currentLightCondition));
                    } else {
                        assertTrue("Truck should be able to pass" + destinationTerrain
                                   + ", with light " + currentLightCondition,
                                   truck.canPass(destinationTerrain, currentLightCondition));
                    }
                } else if (destinationTerrain == Terrain.STREET) {
                    assertTrue("Truck should be able to pass STREET" + ", with light "
                               + currentLightCondition,
                               truck.canPass(destinationTerrain, currentLightCondition));
                }
            }
        }
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByStreetAndLight() {
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        neighbors.put(Direction.SOUTH, Terrain.LIGHT);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            final Direction d = truck.chooseDirection(neighbors);
            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) {
                seenSouth = true;
            }
        }
        
        assertTrue("Truck chooseDirection() fails to select randomly "
                   + "among all possible valid choices!",
                   seenWest && seenNorth && seenEast);
        assertFalse("Truck chooseDirection() reversed direction when not necessary!",
                    seenSouth);
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionOnStreetMustReverse() {
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.LIGHT && t != Terrain.CROSSWALK) {
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.NORTH, t);
                
                final Truck truck = new Truck(0, 0, Direction.NORTH);
                
                assertEquals("Truck chooseDirection() failed "
                             + "when reverse was the only valid choice!",
                             Direction.SOUTH, truck.chooseDirection(neighbors));
            }
        }
    }
}
