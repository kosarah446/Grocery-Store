package edu.gatech.cs6310;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AngryBird {
    private int id;
    private Set<String> possibleLocations;
    private String currentLocation;
    private static double globalProbability;

    public AngryBird(int id) {
        this.id = id;
        this.currentLocation = null;
        this.possibleLocations = new HashSet<>();
    }
    public static double probabilityFormula(double numBirds) {
        double decimal = AngryBird.getGlobalProbability() / 100.0;
        // Probability of 1 bird missing a drone
        double singleMiss = 1 - decimal;
        // Probability of all birds missing
        double allMiss = Math.pow(singleMiss, numBirds);
        // Actual probability of a bird attack
        System.out.println("Probability of attack: " + (1 - allMiss));
        return 1 - allMiss;
    }
    public static int attack(double chance) {
        // Threshold
        if(chance > 0.70) {
            Random rand = new Random();
            // Value of dmg inflicted
            return rand.nextInt(10) + 1;
        } else {
            return 0;
        }
    }
    public String getCurrentLocation() {
        return this.currentLocation;
    }

    public static double getGlobalProbability() {
        return globalProbability;
    }

    public static void setGlobalProbability(int globalProbability) {
        AngryBird.globalProbability = globalProbability;
    }

    public void setCurrentLocation(String currentLocation) {
        if (this.possibleLocations.contains(currentLocation)) {
            this.currentLocation = currentLocation;
        } else {
            this.currentLocation = null;
        }
    }

    public void addPossibleLocation(String location) {
        this.possibleLocations.add(location);
    }

    public Set<String> getPossibleLocations() {
        return this.possibleLocations;
    }

    public static void assignAngryBirdsToRandomLocation(List<AngryBird> angryBirds) {
        Random rand = new Random();

        for (AngryBird bird : angryBirds) {
            Set<String> possibleLocations = bird.getPossibleLocations();

            bird.setCurrentLocation(null);

            // Skip to next bird if there are no possible locations or if a location has already been assigned
            if (possibleLocations == null || possibleLocations.isEmpty() || bird.getCurrentLocation() != null) {
                continue;
            }

            // Select a random location from the set of possible locations
            int size = possibleLocations.size();
            int item = rand.nextInt(size);
            String randomLocation = possibleLocations.toArray(new String[0])[item];

            // Assign the bird to the random location
            bird.setCurrentLocation(randomLocation);
        }
    }

}