package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ujvaricsaba on 10/23/14.
 */
@Entity
@Table( name = "pizza")
public class Pizza extends Model {

    @Id
    public Long id;

    @OneToMany(mappedBy = "pizza")
    public List<Topping> topping;

    public String name;

    @ManyToOne
    public Order order;

}
