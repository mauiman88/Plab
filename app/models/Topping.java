package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table( name = "toppings")
public class Topping extends Model {
    @Id
    public Long id;

    @ManyToMany
    public Pizza pizza;

    public String name;

    @Column(scale = 4, precision = 15)
    public BigDecimal price;

}
