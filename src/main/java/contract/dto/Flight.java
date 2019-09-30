package contract.dto;

import java.util.Date;

public class Flight {
    private long id;
    private Date depDate;
    private Date arrDate;
    private Airplane airplane;
    private Airport depAirport;
    private Airport arrAirport;

    public Flight(long id, Date depDate, Date arrDate, Airplane airplane, Airport depAirport, Airport arrAirport) {
        this.id = id;
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.airplane = airplane;
        this.depAirport = depAirport;
        this.arrAirport = arrAirport;
    }

    public Flight() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public Date getArrDate() {
        return arrDate;
    }

    public void setArrDate(Date arrDate) {
        this.arrDate = arrDate;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airport getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(Airport depAirport) {
        this.depAirport = depAirport;
    }

    public Airport getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(Airport arrAirport) {
        this.arrAirport = arrAirport;
    }
}
