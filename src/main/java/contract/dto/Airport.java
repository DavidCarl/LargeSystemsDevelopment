package contract.dto;

import java.util.TimeZone;

public class Airport {
    private TimeZone timeZone;
    private String iata;
    private String name;

    public Airport(TimeZone timeZone, String iata, String name) {
        this.timeZone = timeZone;
        this.iata = iata;
        this.name = name;
    }

    public Airport() {
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
