package lab4;

import lab4.model.Ticket;
import lab4.model.Wagon;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WagonTest {

    private Wagon validWagon;
    private Wagon notValidWagon;

    {
        Ticket ticket = new Ticket(LocalDate.now(), 10);
        List<Ticket> tickets = new ArrayList();
        tickets.add(ticket);

        validWagon = new Wagon("Some wagon", tickets, 5);
        notValidWagon = new Wagon(null, new ArrayList<>(), 0);
    }

    @Test
    public void testRaceValidate() {
        validWagon.validate();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testRaceValidateNegative() {
        notValidWagon.validate();
    }

}
