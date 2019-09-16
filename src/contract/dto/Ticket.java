package contract.dto;

public class Ticket {
    private String passengarName;
    private Flight flight;

    public Ticket(String name, Flight flight) {
        this.passengarName = name;
        this.flight = flight;
    }

    public Ticket() {
    }

    public String getPassengarName() {
        return passengarName;
    }

    public void setPassengarName(String passengarName) {
        this.passengarName = passengarName;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
