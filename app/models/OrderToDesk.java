package models;

import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Created by ujvaricsaba on 10/16/14.
 */

@Entity
@Table(name = "deskCode")
public class OrderToDesk extends Model {

    @ManyToOne
    public Desk desk;

    @ManyToOne
    public Order order;

    @Column(unique = true)
    public Long code = generateUniqueCode();

    private Long generateUniqueCode() {
        return 1L;
    }


}
