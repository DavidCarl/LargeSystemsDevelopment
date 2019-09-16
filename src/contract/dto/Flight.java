package contract.dto;

import java.util.Date;

public class Flight {
    private Date depDate;
    private Date arrDate;
    private Airplane airplane;

    public Flight(Date depDate, Date arrDate, Airplane airplane) {
        this.depDate = depDate;
        this.arrDate = arrDate;
        this.airplane = airplane;
    }

    public Flight() {
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
}
