package lab4;

import lab4.model.Ticket;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TicketTest {

    private Ticket validTicket;
    private Ticket notValidTicket;

    {
        validTicket = new Ticket(LocalDate.now(), 10);
        notValidTicket = new Ticket(null, 500);
    }

    @Test
    public void testRaceValidate() {
        validTicket.validate();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testRaceValidateNegative() {
        notValidTicket.validate();
    }

}
