package contract.dto;

public class PNRIdentifier {

    private long pnr;

    public PNRIdentifier() {
    }

    public PNRIdentifier(long pnr) {
        this.pnr = pnr;
    }

    public long getPnr() {
        return pnr;
    }

    public void setPnr(long pnr) {
        this.pnr = pnr;
    }
}
