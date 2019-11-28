package lab4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Ticket implements Serializable, Comparable<Ticket> {
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = "cannot be null")
    private LocalDate date;

    @NotNull(message = "cannot be null")
    @Min(1)
    @Max(100)
    private Integer ticketPrice;

    @JsonCreator
    public Ticket(@JsonProperty("date")LocalDate date,@JsonProperty("ticketPrice") Integer ticketPrice){
        this.date=date;
        this.ticketPrice=ticketPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void validate() throws IllegalStateException {
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Ticket>> constraintViolations = validator.validate(this);

        if (constraintViolations.size() > 0) {
            Set<String> exceptionDetails = new HashSet<>();
            for (ConstraintViolation<Ticket> violation : constraintViolations) {
                exceptionDetails.add(violation.getPropertyPath().toString() + " " + violation.getMessage());
            }
            throw new IllegalStateException(exceptionDetails.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (!Objects.equals(date, ticket.date)) return false;
        return Objects.equals(ticketPrice, ticket.ticketPrice);
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Ticket ticket) {
        return date.compareTo(ticket.getDate());
    }
}
