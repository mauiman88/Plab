package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by ujvaricsaba on 12/12/14.
 */
@Entity
public class OrderItem extends Model {

    @Id
    public Long id;

    @ManyToOne
    public Pizza pizza;

    @ManyToOne
    public Drink drink;

    @ManyToOne
    public Order order;
}
