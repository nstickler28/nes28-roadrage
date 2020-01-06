package model;

import java.util.Map;

/**
 * Class for the human.
 * 
 * @author Nathan Stickler
 * @version 10/20/19
 */
public class Human extends AbstractVehicle {

    /** Death time for the human. */
    private static final int DEATH_TIME = 45;
     
    /**
     * Constructor for the human.
     * 
     * @param theX The x coordinate of the human.
     * @param theY The y coordinate of the human.
     * @param theDirection The direction of the human.
     */
    public Human(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain == Terrain.GRASS) {
            canPass = true;
        } else if (theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN) {
            canPass = true;
        }
        return canPass;
    }
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction humanDir = Direction.random();
        final Direction originalDirection = getDirection();
        final Terrain[] humanTerrain = new Terrain[] {
                       theNeighbors.get(getDirection()),
                       theNeighbors.get(originalDirection.left()),
                       theNeighbors.get(originalDirection.right())};
       
        if (isGrass(humanTerrain) && !isCrosswalk(humanTerrain)) {
            while (theNeighbors.get(humanDir) != Terrain.GRASS
                           || humanDir == getDirection().reverse()) {
                humanDir = Direction.random();
            }
        } else if (isCrosswalk(humanTerrain)) {
            while (theNeighbors.get(humanDir) != Terrain.CROSSWALK) {
                humanDir = Direction.random();
            }
        } else {
            humanDir = originalDirection.reverse();
        }
        return humanDir;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks for grass in three directions: straight, left, right.
     * 
     * @param theTerrain The neighboring terrain.
     * @return True if grass is in any of the three directions.
     */
    private boolean isGrass(final Terrain[] theTerrain) {
        boolean isGrass = false;
        if (theTerrain[0] == Terrain.GRASS || theTerrain[1] == Terrain.GRASS
                        || theTerrain[2] == Terrain.GRASS) {
            isGrass = true;
        }
        return isGrass;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks for a crosswalk in three directions: straight, left, right.
     * 
     * @param theTerrain The neighboring terrain.
     * @return True if a crosswalk is in any of the three directions.
     */
    private boolean isCrosswalk(final Terrain[] theTerrain) {
        boolean isCrosswalk = false;
        if (theTerrain[0] == Terrain.CROSSWALK || theTerrain[1] == Terrain.CROSSWALK
                        || theTerrain[2] == Terrain.CROSSWALK) {
            isCrosswalk = true;
        }
        return isCrosswalk;
    }
}
