package model;

import java.util.Map;

/**
 * Class for the ATV.
 * 
 * @author Nathan Stickler
 * @version 10/18/19
 *
 */
public class Atv extends AbstractVehicle {

    /** Death time for the ATV. */
    private static final int DEATH_TIME = 25;
     
    /**
     * Constructor for the ATV class.
     * 
     * @param theX The x coordinate of the ATV.
     * @param theY The y coordinate of the ATV.
     * @param theDirection The direction of the ATV.
     */
    public Atv(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain != Terrain.WALL) {
            canPass = true;
        }
        return canPass;
    }
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction atvDir = Direction.random();
        while (theNeighbors.get(atvDir) == Terrain.WALL
                        || atvDir == getDirection().reverse()) {
            atvDir = Direction.random();
        }
        return atvDir;
    }
}
