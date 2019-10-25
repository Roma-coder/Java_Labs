package lab1.model;

import java.util.List;
import java.util.Objects;

public class Wagon {
    private String title;
    private List<Ticket> tickets;
    private Integer numberFree;

    public Wagon(String title, List<Ticket> ticket, Integer numberFree){
        this.title=title;
        this.tickets=tickets;
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

    public void setTickets(List<Ticket> wagons) {
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
