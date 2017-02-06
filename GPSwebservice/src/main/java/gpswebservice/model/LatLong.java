package gpswebservice.model;

public class LatLong {
    private final double lattitude;
    private final double longinitude;

    public LatLong(double lattitude, double longinitude) {
        this.lattitude = lattitude;
        this.longinitude = longinitude;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLonginitude() {
        return longinitude;
    }
}
