package game.garfield.util;

import java.util.List;

/**
 * Represents a 2D vector with X and Y components.
 * Provides methods for accessing and modifying the vector's components.
 */
public class Vector {

    /**
     * The X-component of the vector.
     */
    private float distanceX;
    
    /**
     * The Y-component of the vector.
     */
    private float distanceY;


    /**
     * Constructs a Vector object using a list of two float values.
     * 
     * @param vect A list containing the X and Y components of the vector.
     *             The first element represents the X, and the second
     *             represents the Y.
     */
    public Vector(List<Float> vect) {
        this.distanceX = vect.get(0);
        this.distanceY = vect.get(1);
    }

    /**
     * Constructs a Vector object using explicit X and Y components.
     * 
     * @param distanceX The X of the vector.
     * @param distanceY The Y of the vector.
     */
    public Vector(float distanceX,float distanceY){
        this.distanceX=distanceX;
        this.distanceY=distanceY;
    }

    /**
     * Retrieves the vector component based on the index.
     * 
     * @param i The index of the component (0 for X, 1 for Y).
     * @return The value of the requested component.
     */
    public Float getVectorIndex(int i){
        return (i==0)?distanceX:distanceY;
    }

    /**
     * Updates the vector component at the specified index.
     * 
     * @param i The index of the component to update (0 for X, 1 for Y).
     * @param e The new value for the specified component.
     */
    public void setVectorIndex(int i,float e){
        if (i==0) distanceX=e; else distanceY=e;
    }
    
}
