package lab2;

import lab2.exception.ConvertException;
import lab2.model.Ticket;
import lab2.service.converter.TicketTextConverter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestTicketTextConverter {

    private Ticket ticket;
    private TicketTextConverter ticketTextConverter;

    {
        ticket = new Ticket(LocalDate.of(2019, 10, 20), 100);
        ticketTextConverter = new TicketTextConverter();
    }

    @Test
    public void serializeTicketTest() throws ConvertException {
        String expected = "2019-10-20##100";
        String actual = ticketTextConverter.serializeToString(ticket);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void deserializeTicketTest() throws ConvertException {
        String serializedString = "2019-10-20##100";
        Ticket actual = ticketTextConverter.deserializeString(serializedString);
        Assert.assertEquals(actual, ticket);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeTicketTest() throws ConvertException {
        String serializedString = "2019-10-20##sds";
        ticketTextConverter.deserializeString(serializedString);
    }

}

