package contract.dto;

import java.io.Serializable;

public class PNRIdentifier implements Serializable {

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
