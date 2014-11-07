package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ujvaricsaba on 10/23/14.
 */

@Entity
@Table( name = "invoice")
public class Invoice {

    @Id
    public Long id;

    @ManyToOne
    public Desk desk;

    public Date invoiceDate;

    public String name;

    @OneToOne(cascade = CascadeType.ALL)
    public Order order;

    public static enum InvoiceState{
        UN_PAID("Unpaid"), PAID("Paid"), CANCELLATION("Cancellation");

        private final String name;

        InvoiceState (String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
}
