package model;

import java.util.Map;

/**
 * Class for the taxi.
 * 
 * @author Nathan Stickler
 * @version 10/18/19
 *
 */
public class Taxi extends AbstractVehicle {
    
    /** Death time for the taxi. */
    private static final int DEATH_TIME = 15;
    
    /** Number of clock ticks before Taxi can drive through red crosswalk. */
    private static final int SKIP_TIME = 3;
    
    /** Counter for number of clock ticks taxi will wait at red crosswalks. */
    private int myTaxiTimer;

    /**
     * Constructor for the taxi.
     * 
     * @param theX The x coordinate of the taxi.
     * @param theY The y coordinate of the taxi.
     * @param theDirection The direction of the taxi.
     */
    public Taxi(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.CROSSWALK) {
            canPass = true;
        }
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED
                        && myTaxiTimer <= SKIP_TIME) {
            canPass = false;
            myTaxiTimer++;
        }
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED
                        && myTaxiTimer > SKIP_TIME) {
            canPass = true;
            myTaxiTimer = 0;
        } else if (theTerrain == Terrain.LIGHT && theLight != Light.RED) {
            canPass = true;
            myTaxiTimer = 0;
        }
        return canPass;
    } 
    
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction taxiDir = getDirection();
        final Terrain[] taxiTerrain = new Terrain[] {
                        theNeighbors.get(getDirection()),
                        theNeighbors.get(getDirection().left()),
                        theNeighbors.get(getDirection().right())};
        if (validTerrain(taxiTerrain[0])) {
            taxiDir = getDirection();
        } else if (validTerrain(taxiTerrain[1])) {
            taxiDir = taxiDir.left();
        } else if (validTerrain(taxiTerrain[2])) {
            taxiDir = taxiDir.right();
        } else {
            taxiDir = taxiDir.reverse();
        }
        return taxiDir;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks the terrain to see if it is a valid terrain type.
     * 
     * @param theTerrain The terrain.
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
