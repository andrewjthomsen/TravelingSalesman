public class City {
    private final int x;
    private final int y;

    // constructor
    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Method for calculating distance between two cities
    public double getDistanceFrom(City c) {
        return Math.sqrt(Math.pow((this.x - c.x), 2) + Math.pow((this.y - c.y), 2));
    }
}
