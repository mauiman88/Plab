package models;

import play.db.ebean.Model;

import javax.persistence.*;


@Entity
@Table(name = "deskCode")
public class OrderToDesk extends Model {

    @ManyToOne
    public Order order;

    @Column(unique = true)
    public Long code = generateUniqueCode();

    private Long generateUniqueCode() {
        return 1L;
    }


}
