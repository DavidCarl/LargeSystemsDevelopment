package contract.dto;

import java.util.Collection;

public class Booking {
    private PNRIdentifier pnr;
    private double price;
    private long eTicketId;
    private Collection<Ticket> tickets;

    public Booking(PNRIdentifier pnr, double price, long eTicketId, Collection<Ticket> tickets) {
        this.pnr = pnr;
        this.price = price;
        this.eTicketId = eTicketId;
        this.tickets = tickets;
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

    public long geteTicketId() {
        return eTicketId;
    }

    public void seteTicketId(long eTicketId) {
        this.eTicketId = eTicketId;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
