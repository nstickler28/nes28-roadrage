package model;

import java.util.Map;

/**
 * Class for the bicycle.
 * 
 * @author Nathan Stickler 
 * @version 10/19/19
 */
public class Bicycle extends AbstractVehicle {

    /** Death time for the bicycle. */
    private static final int DEATH_TIME = 35;
    
    /** 
     * Constructor for the taxi. 
     * 
     * @param theX The x coordinate of the taxi.
     * @param theY The y coordinate of the taxi.
     * @param theDirection The direction of the taxi.
     */
    public Bicycle(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain == Terrain.TRAIL || theTerrain == Terrain.STREET) {
            canPass = true;
        } else if ((theTerrain == Terrain.LIGHT && theLight == Light.GREEN)
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN)) {
            canPass = true;
        }
        return canPass;
    }
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction bikeDir = getDirection();
        final Terrain[] bikeTerrain = new Terrain[] {
                        theNeighbors.get(getDirection()),
                        theNeighbors.get(getDirection().left()),
                        theNeighbors.get(getDirection().right())};
        if (isTrail(bikeTerrain)) {
            while (theNeighbors.get(bikeDir) != Terrain.TRAIL
                            || bikeDir == getDirection().reverse()) {
                bikeDir = Direction.random();
            }
        } else if (validTerrain(bikeTerrain[0])) {
            bikeDir = getDirection();
        } else if (validTerrain(bikeTerrain[1])) {
            bikeDir = bikeDir.left();
        } else if (validTerrain(bikeTerrain[2])) {
            bikeDir = bikeDir.right();
        } else {
            bikeDir = bikeDir.reverse();
        }
        return bikeDir;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks for a train in three directions: straight, left, right.
     * 
     * @param theTerrain An array of the neighboring terrain.
     * @return True if a trail is present in any of the directions, else false.
     */
    private boolean isTrail(final Terrain[] theTerrain) {
        boolean isTrail = false;
        if (theTerrain[0] == Terrain.TRAIL || theTerrain[1] == Terrain.TRAIL
                        || theTerrain[2] == Terrain.TRAIL) {
            isTrail = true;
        }
        return isTrail;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks terrain t o see if it is a valid terrain.
     * 
     * @param theTerrain The neighboring terrain.
     * @return True if the terrain is valid, else false.
     */
    private boolean validTerrain(final Terrain theTerrain) {
        boolean validTerrain = false;
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT
                        || theTerrain == Terrain.CROSSWALK) {
            validTerrain = true;
        }
        return validTerrain;
    }
}
