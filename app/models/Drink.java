package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by ujvaricsaba on 11/7/14.
 */
@Entity
@Table(name = "drinks")
public class Drink extends Model {
    @Id
    public Long id;

    public String name;

    @Column(scale = 4, precision = 15)
    public BigDecimal price;

    @ManyToOne
    public Order order;
}
