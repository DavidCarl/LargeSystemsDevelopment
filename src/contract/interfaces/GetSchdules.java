package contract.interfaces;

import contract.dto.Airport;
import contract.dto.Schedule;

import java.util.Collection;

public interface GetSchdules {
    Collection<Schedule> getSchedules(Airport arrAp, Airport depAp);
}
