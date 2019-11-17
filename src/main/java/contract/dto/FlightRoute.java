package contract.dto;

import java.util.Collection;

public class FlightRoute {
    private boolean directFlight;
    private Collection<Flight> flights;

    public FlightRoute(boolean directFlight, Collection<Flight> flights) {
        this.directFlight = directFlight;
        this.flights = flights;
    }

    public FlightRoute() {
    }

    public boolean isDirectFlight() {
        return directFlight;
    }

    public void setDirectFlight(boolean directFlight) {
        this.directFlight = directFlight;
    }

    public Collection<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Collection<Flight> flights) {
        this.flights = flights;
    }
}
