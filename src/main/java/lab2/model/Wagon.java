package lab2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lab2.model.Ticket;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Wagon implements Serializable {
    private String title;
    private List<Ticket> tickets;
    private Integer numberFree;

    @JsonCreator
    public Wagon(@JsonProperty("title") String title,@JsonProperty("ticket") List<Ticket> ticket,@JsonProperty("numberFree") Integer numberFree){
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
