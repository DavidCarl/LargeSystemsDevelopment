package contract.interfaces;

import contract.dto.Booking;
import contract.dto.PNRIdentifier;

public interface GetBooking {
    Booking getBooking(PNRIdentifier pnr);
}
