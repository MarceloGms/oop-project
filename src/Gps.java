import java.io.Serializable;

/**
 * Representa as coordenadas gps.
 * @author Marcelo Gomes
 * @author Pedro Brites Santos
 * @version 1.0
 */
public class Gps implements Serializable {
    private double latitude;
    private double longitude;

    /**
     * Construtor Gps.
     * @param latitude recebe a latitude das coordenadas.
     * @param longitude recebe a longitude das coordenadas.
     */
    public Gps(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter latitude.
     * @return latitude das coordenadas.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter latitude.
     * @param latitude recebe a latitude a definir das coordenadas.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter longitude.
     * @return longitude das coordenadas.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter longitude.
     * @param longitude recebe a longitude a definir das coordenadas.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
