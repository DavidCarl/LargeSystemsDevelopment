package contract.dto;

import java.io.Serializable;
import java.util.Date;

public class Passenger implements Serializable {
    private String firstName;
    private String lastName;
    private Date dob;

    public Passenger(String firstName, String lastName, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public Passenger() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
