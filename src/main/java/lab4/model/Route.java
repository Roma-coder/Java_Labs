package lab4.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Route {
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String from;

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String to;

    @JsonCreator
    public Route(@JsonProperty("from") String from,@JsonProperty("to") String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void validate() throws IllegalStateException {
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Route>> constraintViolations = validator.validate(this);

        if (constraintViolations.size() > 0) {
            Set<String> exceptionDetails = new HashSet<>();
            for (ConstraintViolation<Route> violation : constraintViolations) {
                exceptionDetails.add(violation.getPropertyPath().toString() + " " + violation.getMessage());
            }
            throw new IllegalStateException(exceptionDetails.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (!Objects.equals(from, route.from)) return false;
        return Objects.equals(to, route.to);
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
