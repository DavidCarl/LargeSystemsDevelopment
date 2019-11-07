package contract.interfaces;

import contract.dto.*;

import java.util.Collection;
import java.util.Date;

public interface BeanInterface {
    Collection<FlightRoute> getFlightRoutes(Date start, Date end, String depIata, String destIata, boolean oneWay);
    Booking makeBooking(Collection<Long> flightIds, FFNCCIdenitfier ffncc, Collection<Passenger> passengers);
    Booking getBooking(PNRIdentifier pnr);
    boolean cancelBooking(PNRIdentifier pnr);
    String whoAmI(String message);
}