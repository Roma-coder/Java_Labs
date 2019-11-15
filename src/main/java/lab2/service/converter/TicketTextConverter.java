package lab2.service.converter;

import lab2.exception.ConvertException;
import lab2.model.Ticket;
import lab2.service.Converter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TicketTextConverter implements Converter<Ticket> {

    private final String FIELDS_SEPARATOR = "##";
    private final Integer FIELDS_COUNT = 2;

    private Object[] getTicketFields(Ticket ticket) {
        return new Object[] {
                ticket.getDate(), ticket.getTicketPrice()
        };
    }

    @Override
    public String serializeToString(Ticket ticket) throws ConvertException {
        try {
            Object[] ticketFields = getTicketFields(ticket);

            List<String> stringFields = Arrays.stream(ticketFields)
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return String.join(FIELDS_SEPARATOR, stringFields);
        }
        catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    @Override
    public Ticket deserializeString(String serializedString) throws ConvertException {
        try {
            String[] stringFields = serializedString.split(FIELDS_SEPARATOR);

            if (stringFields.length != FIELDS_COUNT) {
                throw new Exception("Invalid format of string!");
            }

            return new Ticket(LocalDate.parse(stringFields[0]), Integer.parseInt(stringFields[1]));
        }
        catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }
}

