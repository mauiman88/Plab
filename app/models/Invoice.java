package models;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.ebean.Model;
import play.libs.Json;
import utils.JSONUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table( name = "invoice")
public class Invoice {

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

    @Id
    public Long id;

    @ManyToOne
    public Desk desk;

    public Date invoiceDate;

    public String name;

    @OneToOne(cascade = CascadeType.ALL)
    public Order order;

}
