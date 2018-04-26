package wegepunkte.sabel.com.wegepunkte;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Wegepunkt {

    private Date timestamp;
    private double lat;
    private double longt;

    public Wegepunkt() {
    }

    public Wegepunkt(Date timestamp, double lat, double longt) {
        this.timestamp = timestamp;
        this.lat = lat;
        this.longt = longt;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wegepunkt that = (Wegepunkt) o;
        return Double.compare(that.lat, lat) == 0 &&
                Double.compare(that.longt, longt) == 0 &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(timestamp, lat, longt);
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return
                simpleDateFormat.format(timestamp) +
                "\t" + lat +
                "\t" + longt;
    }
}
