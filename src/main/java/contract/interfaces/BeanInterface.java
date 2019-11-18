package contract.interfaces;

import contract.dto.*;

import javax.ejb.Remote;
import java.util.Collection;
import java.util.Date;

@Remote
public interface BeanInterface {
    // Entering search critireas will return flight offers. If oneWay then no Date end should be provided.
    Collection<FlightOffer> getFlightOffers(Date start, Date end, String depIata, String destIata, boolean oneWay);
    // Make a booking on the chosen flight suggestion.
    Booking makeBooking(FlightOffer offer, FFNCCIdenitfier ffncc, Collection<Passenger> passengers);
    // Retrieve booking info from Passenger Name Record
    Booking getBooking(PNRIdentifier pnr);
    // Cancel booking with Passenger Name Record
    boolean cancelBooking(PNRIdentifier pnr);
    // Hello backend method
    String whoAmI(String message);
}