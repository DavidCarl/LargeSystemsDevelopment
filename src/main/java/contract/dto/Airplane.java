package contract.dto;

public class Airplane {
    private int capacity;
    private String iata; // number of the carrier

    public Airplane(int capacity, String iata) {
        this.capacity = capacity;
        this.iata = iata;
    }

    public Airplane() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }
}
