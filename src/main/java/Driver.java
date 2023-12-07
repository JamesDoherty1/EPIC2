class Driver {
    private String name;
    private String carType;
    private String licensePlate;
    private double rating;
    private double distance;

    public Driver(String name, String carType, String licensePlate, double rating, double distance) {
        this.name = name;
        this.carType = carType;
        this.licensePlate = licensePlate;
        this.rating = rating;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " - " + carType + " - " + licensePlate + " - Rating: " + rating + " - Distance: " + distance;
    }
}