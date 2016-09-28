/*
 * TCSS 305 - Winter 2015
 * Assignment 3 - Easy Street
 */

package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents a human.
 * 
 * @author Damiene Stewart
 * @version 22 January 2015
 */
public final class Human extends AbstractVehicle {

    /**
     * Death time constant for magic_number warning.
     */
    private static final int DEATH_TIME = 50;
    
    /**
     * Records the Human's initial terrain.
     */
    private final Terrain myInitialTerrain;
    
    /**
     * Constructs a new Human.
     * 
     * @param theX the assigned X position.
     * @param theY the assigned Y position.
     * @param theDir the assigned Direction.
     * @param theTerrain the assigned Terrain.
     */
    public Human(final int theX, final int theY, final Direction theDir,
                 final Terrain theTerrain) {
        super(theX, theY, theDir, DEATH_TIME);
        
        myInitialTerrain = theTerrain;
    }
    
    /**
     * Humans ignore lights and stay on their initial terrain.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        
        if (theTerrain == myInitialTerrain || (theTerrain == Terrain.STREET
                                        || theTerrain == Terrain.LIGHT)
                                        && (myInitialTerrain == Terrain.STREET 
                                        || myInitialTerrain == Terrain.LIGHT)) {
            canPass = true;
        }
        
        return canPass;
    }

    /**
     * Humans stay on their initial terrain.
     * 
     * {@inheritDoc}
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final List<Direction> directionOptions = new ArrayList<Direction>();
        
        final boolean startedOnStreetOrLight = myInitialTerrain == Terrain.LIGHT 
                                        || myInitialTerrain == Terrain.STREET;
        
        // Set up.
        final Map<Direction, Terrain> neighbors;
        neighbors = new HashMap<Direction, Terrain>(theNeighbors);
        neighbors.remove(Direction.CENTER);
        
        for (final Direction d : neighbors.keySet()) {
            if (startedOnStreetOrLight && (theNeighbors.get(d) == Terrain.LIGHT 
                                            || theNeighbors.get(d) == Terrain.STREET)) {
                directionOptions.add(d);
            } else if (myInitialTerrain == theNeighbors.get(d)) {
                directionOptions.add(d);
            }
        }
        
        final Random rand = new Random();
        final int randomInt = rand.nextInt(directionOptions.size());
        
        return directionOptions.get(randomInt);
    }
}
