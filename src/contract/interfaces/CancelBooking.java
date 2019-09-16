package contract.interfaces;

import contract.dto.PNRIdentifier;

public interface CancelBooking {
    boolean cancelBooking(PNRIdentifier pnr);
}
