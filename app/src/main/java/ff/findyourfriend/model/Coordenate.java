package ff.findyourfriend.model;

/**
 * Created by laura on 06/04/2016.
 */
public class Coordenate {
    Double longitude;
    Double latitude;

    public Coordenate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "(" + getLatitude() + ", " + getLongitude() + ")";
    }
}
