package lab3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Wagon implements Serializable {
    @NotNull
    private String title;
    private List<Ticket> tickets;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wagon wagon = (Wagon) o;

        return  Objects.equals(title, wagon.title);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;

        return result;
    }
}
