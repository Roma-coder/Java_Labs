package lab1.model;

import java.time.LocalDate;
import java.util.Objects;

public class Ticket {
    private LocalDate date;
    private Integer ticketPrice;

    public Ticket(LocalDate date, Integer ticketPrice){
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
}
