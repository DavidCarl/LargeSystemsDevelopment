package contract.dto;

import java.util.Collection;

public class Booking {
    private PNRIdentifier pnr;
    private double price;
    private FFNCCIdenitfier ffncc;
    private Collection<Ticket> tickets;
    private FlightRoute flightRoute;
    private FlightRoute returnRoute;

    public Booking(PNRIdentifier pnr, double price, FFNCCIdenitfier ffncc, Collection<Ticket> tickets, FlightRoute flightRoute, FlightRoute returnRoute) {
        this.pnr = pnr;
        this.price = price;
        this.ffncc = ffncc;
        this.tickets = tickets;
        this.flightRoute = flightRoute;
        this.returnRoute = returnRoute;
    }

    public Booking() {
    }

    public PNRIdentifier getPnr() {
        return pnr;
    }

    public void setPnr(PNRIdentifier pnr) {
        this.pnr = pnr;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public FFNCCIdenitfier getFfncc() {
        return ffncc;
    }

    public void setFfncc(FFNCCIdenitfier ffncc) {
        this.ffncc = ffncc;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public FlightRoute getFlightRoute() {
        return flightRoute;
    }

    public void setFlightRoute(FlightRoute flightRoute) {
        this.flightRoute = flightRoute;
    }

    public FlightRoute getReturnRoute() {
        return returnRoute;
    }

    public void setReturnRoute(FlightRoute returnRoute) {
        this.returnRoute = returnRoute;
    }
}
