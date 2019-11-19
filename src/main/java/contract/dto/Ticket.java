package contract.dto;

import java.io.Serializable;

public class Ticket implements Serializable {
    private Passenger passenger;
    private Flight flight;

    public Ticket(Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
    }

    public Ticket() {
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
