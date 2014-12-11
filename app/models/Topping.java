package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by ujvaricsaba on 11/7/14.
 */
@Entity
@Table( name = "toppings")
public class Topping extends Model {
    @Id
    public Long id;

    @ManyToMany
    public Pizza pizza;

    public String name;

    @Column(scale = 2, precision = 2)
    public BigDecimal price;

}
