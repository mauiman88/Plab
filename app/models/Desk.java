package models;

import com.google.common.collect.Lists;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by ujvaricsaba on 10/16/14.
 */
@Entity
@Table(name = "desk")
public class Desk extends Model {

    public static enum DeskState{
        NEW("New");

        private final String name;

        DeskState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Date created = new Date();

    @Id
    public Long id;

    //asztal szám
    @Column(nullable = false)
    public Long deskNumber;


    @Enumerated(EnumType.STRING)
    public DeskState orderState;

    @OneToOne
    public Order order;


    @OneToMany(mappedBy = "desk")
    public List<OrderToDesk> orderToDesks = Lists.newArrayList();
}
