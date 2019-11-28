package lab4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Wagon implements Serializable {
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String title;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private List<Ticket> tickets;

    @NotNull(message = "cannot be null")
    @Min(value = 0, message = "cannot be less then 0")
    @Max(value = 20, message = "cannot be more than 20")
    private Integer numberFree;

    @JsonCreator
    public Wagon(@JsonProperty("title") String title, @JsonProperty("ticket") List<Ticket> ticket, @JsonProperty("numberFree") Integer numberFree){
        this.title=title;
        this.tickets=ticket;
        this.numberFree=numberFree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Integer getNumberFree() {
        return numberFree;
    }

    public void setNumberFree(Integer numberFree) {
        this.numberFree = numberFree;
    }

    public void validate() throws IllegalStateException {
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Wagon>> constraintViolations = validator.validate(this);

        if (constraintViolations.size() > 0) {
            Set<String> exceptionDetails = new HashSet<>();
            for (ConstraintViolation<Wagon> violation : constraintViolations) {
                exceptionDetails.add(violation.getPropertyPath().toString() + " " + violation.getMessage());
            }
            throw new IllegalStateException(exceptionDetails.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wagon wagon = (Wagon) o;

        if (!Objects.equals(title, wagon.title)) return false;
        if (!Objects.equals(tickets, wagon.tickets)) return false;
        return Objects.equals(numberFree, wagon.numberFree);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
        result = 31 * result + (numberFree != null ? numberFree.hashCode() : 0);
        return result;
    }
}
