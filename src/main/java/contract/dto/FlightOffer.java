package contract.dto;

public class FlightOffer {
    private double price;
    private boolean oneWay;
    private FlightRoute outRoute;
    private FlightRoute returnRoute;

    public FlightOffer(double price, boolean oneWay, FlightRoute outRoute, FlightRoute returnRoute) {
        this.price = price;
        this.oneWay = oneWay;
        this.outRoute = outRoute;
        this.returnRoute = returnRoute;
    }

    public FlightOffer() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public FlightRoute getOutRoute() {
        return outRoute;
    }

    public void setOutRoute(FlightRoute outRoute) {
        this.outRoute = outRoute;
    }

    public FlightRoute getReturnRoute() {
        return returnRoute;
    }

    public void setReturnRoute(FlightRoute returnRoute) {
        this.returnRoute = returnRoute;
    }
}
