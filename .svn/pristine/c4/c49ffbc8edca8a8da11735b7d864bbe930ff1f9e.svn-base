package model;

import java.util.Locale;

/**
 * Represents parent class for vehicles.
 * 
 * @author Nathan Stickler
 * @version 10/17/19
 * 
 */
public abstract class AbstractVehicle implements Vehicle {

    /** The X coordinate of the vehicle. */
    private int myX;
    /** The Y coordinate of the vehicle. */
    private int myY;
    /** The direction of the vehicle. */
    private Direction myDirection;
    /** The location of the vehicle image. */
    private final String myImageName;
    /** The starting X coordinate of the vehicle. */
    private final int myStartingX;
    /** The starting Y coordinate of the vehicle. */
    private final int myStartingY;
    /** The starting direction of the vehicle. */
    private final Direction myStartingDirection;
    /** Number of moves the vehicle stays dead. */
    private final int myDeathTime;
    /** Number of moves the vehicle has been dead. */
    private int myDeathTimer;
    
    /**
     * Constructs a vehicle with given x and y coordinates and direction.
     * 
     * @param theX the X coordinate of the vehicle.
     * @param theY the Y coordinate of the vehicle.
     * @param theDirection the Direction of the vehicle.
     * @param theDeathTime the number of moves the vehicle stays dead.
     */
    protected AbstractVehicle(final int theX, final int theY, 
                           final Direction theDirection, final int theDeathTime) {
     
        myX = theX;
        myY = theY;
        myStartingX = theX;
        myStartingY = theY;
        myDirection = theDirection;
        myStartingDirection = theDirection;
        myDeathTime = theDeathTime;
        myImageName = getClass().getSimpleName().toLowerCase(Locale.US);
        myDeathTimer = theDeathTime;
    }   
     
    @Override
    public void collide(final Vehicle theOther) {
        if ((isAlive() && theOther.isAlive())
                        && (myDeathTime > theOther.getDeathTime())) {
            myDeathTimer = 0;
        }
    }
    
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }
    
    @Override
    public String getImageFileName() {
        final StringBuilder sb = new StringBuilder();
        if (isAlive()) {
            sb.append(myImageName);
            sb.append(".gif");
        } else {
            sb.append(myImageName);
            sb.append("_dead.gif");
        }   
        return sb.toString();
    }
    
    @Override
    public Direction getDirection() {
        return myDirection;
    }
    
    @Override
    public int getX() {
        return myX;
    }
    
    @Override
    public int getY() {
        return myY;
    }
    
    @Override
    public boolean isAlive() {
        boolean isAlive = true;
        if (myDeathTime != myDeathTimer) {
            isAlive = false;
        }
        return isAlive;
    }
    
    @Override
    public void poke() {
        if (isAlive()) {
            myDirection = Direction.random();
        } else {
            myDeathTimer++;
        }
    }
    
    @Override
    public void reset() {
        myX = myStartingX;
        myY = myStartingY;
        myDirection = myStartingDirection;
        myDeathTimer = myDeathTime;
    }
    
    @Override
    public void setDirection(final Direction theDirection) {
        myDirection = theDirection;
    }
    
    @Override
    public void setX(final int theX) {
        myX = theX;
    }
    
    @Override
    public void setY(final int theY) {
        myY = theY;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        return sb.toString();
    }
}
