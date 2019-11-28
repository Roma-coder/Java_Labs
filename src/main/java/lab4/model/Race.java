package lab4.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import lab4.service.UniqueWagonsTitles;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@JsonDeserialize(builder = Race.Builder.class)
public class Race implements Serializable {
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    private String departurePoint;

    @NotNull(message = "cannot be null")
    @UniqueWagonsTitles(message = "cannot contain wagons with the same titles")
    private List<Wagon> wagons;

    @NotNull(message = "cannot be null")
    private Integer numberTrain;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @NotNull(message = "cannot be null")
    private LocalTime startTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @NotNull(message = "cannot be null")
    private LocalTime finishTime;

    @NotNull(message = "cannot be null")
    private Route route;

    @NotNull(message = "cannot be null")
    private Periodicity periodicity;


    public enum Periodicity {
        Regular,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
    }

    private Race() {

    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public List<Wagon> getWagons() {
        return wagons;
    }

    public Integer getNumberTrain() {
        return numberTrain;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public Route getRoute() {
        return route;
    }

    public Periodicity getPeriodicity() { return periodicity; }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public boolean addWagon(Wagon wagon){
        List<String> titles = wagons.stream()
                .map(Wagon::getTitle)
                .collect(Collectors.toList());

        if (titles.contains(wagon.getTitle()))
            return false;

        return wagons.add(wagon);
    }

    public boolean removeWagon(Wagon wagon) {
        if (!wagons.contains(wagon))
            return false;

        return wagons.remove(wagon);
    }

    public void setNumberTrain(Integer numberTrain) {
        this.numberTrain = numberTrain;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public void validate() throws IllegalStateException {
        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Race>> constraintViolations = validator.validate(this);

        if (constraintViolations.size() > 0) {
            Set<String> exceptionDetails = new HashSet<>();
            for (ConstraintViolation<Race> violation : constraintViolations) {
                exceptionDetails.add(violation.getPropertyPath().toString() + " " + violation.getMessage());
            }
            throw new IllegalStateException(exceptionDetails.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Race race = (Race) o;

        if (departurePoint != null ? !departurePoint.equals(race.departurePoint) : race.departurePoint != null)
            return false;
        if (wagons != null ? !wagons.equals(race.wagons) : race.wagons != null) return false;
        if (numberTrain != null ? !numberTrain.equals(race.numberTrain) : race.numberTrain != null) return false;
        if (startTime != null ? !startTime.equals(race.startTime) : race.startTime != null) return false;
        if (finishTime != null ? !finishTime.equals(race.finishTime) : race.finishTime != null) return false;
        if (route != null ? !route.equals(race.route) : race.route != null) return false;
        return periodicity == race.periodicity;
    }

    @Override
    public int hashCode() {
        int result = departurePoint != null ? departurePoint.hashCode() : 0;
        result = 31 * result + (wagons != null ? wagons.hashCode() : 0);
        result = 31 * result + (numberTrain != null ? numberTrain.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + (periodicity != null ? periodicity.hashCode() : 0);
        return result;
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        Race race;


        public Builder() {
            race = new Race();
            race.wagons = new ArrayList<>();
        }

        public Builder setDeparturePoint(String departurePoint) {
            race.departurePoint = departurePoint;
            return this;
        }

        public Builder setNumberTrain(Integer numberTrain) {
            race.numberTrain = numberTrain;
            return this;
        }
        @JsonDeserialize(using = LocalTimeDeserializer.class)
        public Builder setStartTime(LocalTime startTime) {
            race.startTime = startTime;
            return this;
        }
        @JsonDeserialize(using = LocalTimeDeserializer.class)
        public Builder setFinishTime(LocalTime finishTime) {
            race.finishTime = finishTime;
            return this;
        }

        public Builder setRoute(Route route) {
            race.route = route;
            return this;
        }

        public Builder setPeriodicity(Periodicity periodicity) {
            race.periodicity = periodicity;
            return this;
        }

        public Race build() {
            return race;
        }

    }

}
