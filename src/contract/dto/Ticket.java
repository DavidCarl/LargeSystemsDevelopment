package contract.dto;

public class Ticket {
    private String name;
    private Flight flight;

    public Ticket(String name, Flight flight) {
        this.name = name;
        this.flight = flight;
    }

    public Ticket() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
