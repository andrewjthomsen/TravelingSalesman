import java.util.ArrayList;
import java.util.Vector;

public class TravelingSalesman {
    private static final int numCities = 5;
    private static final ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        // Create vector with city coordinates.
        Vector<City> cities = new Vector<>();
        cities.add(new City(1, 1));
        cities.add(new City(1, 3));
        cities.add(new City(4, 1));
        cities.add(new City(5, 3));
        cities.add(new City(3, 5));

        // Fill vector with distances
        Vector<Vector<Double>> distanceVect = fillDistances(cities);
        print2DVect(distanceVect);
        // Finding the optimal path
        optimalPath(distanceVect);
    }

    // Fills a 2D vector with distances between each city
    public static Vector<Vector<Double>> fillDistances(Vector<City> cities) {
        // Add buffer row for optimal path
        cities.add(0, null);
        // Create 2D to hold distances
        Vector<Vector<Double>> distance2D = new Vector<>();
        Vector<Double> firstRow = new Vector<>();
        // Add buffer entries for first row of 2D vector
        for (int i = 0; i < numCities; i++) {
            firstRow.add(0.0);
        }
        distance2D.add(firstRow);
        // Calculate distance between each city and add to vector
        for (int i = 1; i <= numCities; i++) {
            Vector<Double> temp = new Vector<>();
            temp.add(0.0);
            for (int j = 1; j <= numCities; j++) {
                temp.add(cities.get(i).getDistanceFrom(cities.get(j)));
            }
            distance2D.add(temp);
        }
        return distance2D;
    }

    // Prints 2DVEct with headers
    public static void print2DVect(Vector<Vector<Double>> distanceVect) {
        System.out.println("    __C1___C2___C3___C4___C5_");
        // for loops to print 2D array
        for (int i = 1; i <= numCities; i++) {
            System.out.print("C" + i + " | ");
            for (int j = 1; j <= numCities; j++) {
                System.out.printf("%.2f ", distanceVect.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();
    }

    // Finds optimal path between cities
    public static void optimalPath(Vector<Vector<Double>> distanceVect) {
        // Keeps track of which cities have been visited
        boolean[] visited = new boolean[numCities + 1];
        // First city is a known start point
        visited[1] = true;
        // Add first city to ArrayList as starting point
        list.add(1);
        int currentCity;
        int destination = 1;
        int otherCity;
        double min;
        // Flag for minimum distance found (min)
        boolean minFlag = false;
        // Print starting city
        System.out.print("Optimal Path: City1 -> ");
        // Continue while there are still cities to visit
        while (!list.isEmpty()) {
            // Set currentCity to top of list
            currentCity = list.get(list.size() - 1);
            // Set comparisonCity
            otherCity = 1;
            // Set minimum to maximum possible value
            min = Integer.MAX_VALUE;
            // Loop through every city
            while (otherCity <= numCities) {
                // if distance between currentCity and otherCity != 0, and not visited yet
                if (distanceVect.get(currentCity).get(otherCity) > 1 && !visited[otherCity]) {
                    // If new distance is less than current min distance
                    if (min > distanceVect.get(currentCity).get(otherCity)) {
                        // Update min and set destination to city, reset min flag
                        min = distanceVect.get(currentCity).get(otherCity);
                        destination = otherCity;
                        minFlag = true;
                    }
                }
                // Increment otherCity
                otherCity++;
            }
            // If min distance is found, complete that leg of the journey and add to list
            if (minFlag) {
                visited[destination] = true;
                list.add(destination);
                // Print next destination
                System.out.print("City" + destination + " -> ");
                minFlag = false;
                continue;
            }
            // Otherwise remove from list
            list.remove(list.size() - 1);
        }
        // Always return to where you started
        System.out.println("City1");
    }

}
