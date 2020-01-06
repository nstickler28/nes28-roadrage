package model;

import java.util.Map;

/**
 * Class for the truck.
 * 
 * @author Nathan Stickler
 * @version 10/18/19
 *
 */
public class Truck extends AbstractVehicle {

    /** Death time for a truck. */
    private static final int DEATH_TIME = 0;
    
    /**
     * Constructor for the Truck.
     * 
     * @param theX The x coordinate of the truck.
     * @param theY The y coordinate of the truck.
     * @param theDirection The direction of the truck.
     */
    public Truck(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            canPass = true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight != Light.RED) {
            canPass = true;
        }
        return canPass;
    }
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction randTruckDir = Direction.random();
        if (canPass(Terrain.STREET, Light.GREEN)) {
            if (canTruckReverse(theNeighbors)) {
                randTruckDir = getDirection().reverse();              
            } else {
                while (theNeighbors.get(randTruckDir) != Terrain.STREET
                       && theNeighbors.get(randTruckDir) != Terrain.CROSSWALK
                       && theNeighbors.get(randTruckDir) != Terrain.LIGHT
                       || randTruckDir == getDirection().reverse()) {
                    randTruckDir = Direction.random();
                }
            }
        }
        return randTruckDir;
    }
    
    /**
     * Helper method for truck to see whether it can reverse.
     * 
     * @param theNeighbors the map set.
     * @return canReverse True if the truck can reverse, else false.
     */
    private boolean canTruckReverse(final Map<Direction, Terrain> theNeighbors) {
        final Direction truckDir = getDirection();
        
        return theNeighbors.get(getDirection()) != Terrain.STREET
                        && theNeighbors.get(truckDir) != Terrain.LIGHT
                        && theNeighbors.get(truckDir.right()) != Terrain.LIGHT
                        && theNeighbors.get(truckDir.left()) != Terrain.LIGHT
                        && theNeighbors.get(truckDir) != Terrain.CROSSWALK
                        && theNeighbors.get(truckDir.right()) != Terrain.CROSSWALK
                        && theNeighbors.get(truckDir.left()) != Terrain.CROSSWALK
                        && (theNeighbors.get(truckDir.right()) != Terrain.STREET)
                        && (theNeighbors.get(truckDir.left()) != Terrain.STREET);
    }
}
