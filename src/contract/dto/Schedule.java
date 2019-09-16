package contract.dto;

import java.time.LocalTime;

public class Schedule {
    private Weekday depDay;
    private LocalTime depTimeUtc;
    private Weekday arrDay;
    private LocalTime arrTimeUtc;
    private Airplane airplane;

    public Schedule(Weekday depDay, LocalTime depTimeUtc, Weekday arrDay, LocalTime arrTimeUtc, Airplane airplane) {
        this.depDay = depDay;
        this.depTimeUtc = depTimeUtc;
        this.arrDay = arrDay;
        this.arrTimeUtc = arrTimeUtc;
        this.airplane = airplane;
    }

    public Schedule() {
    }

    public Weekday getDepDay() {
        return depDay;
    }

    public void setDepDay(Weekday depDay) {
        this.depDay = depDay;
    }

    public LocalTime getDepTimeUtc() {
        return depTimeUtc;
    }

    public void setDepTimeUtc(LocalTime depTimeUtc) {
        this.depTimeUtc = depTimeUtc;
    }

    public Weekday getArrDay() {
        return arrDay;
    }

    public void setArrDay(Weekday arrDay) {
        this.arrDay = arrDay;
    }

    public LocalTime getArrTimeUtc() {
        return arrTimeUtc;
    }

    public void setArrTimeUtc(LocalTime arrTimeUtc) {
        this.arrTimeUtc = arrTimeUtc;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}
