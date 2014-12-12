package models;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Lists;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "desk")
public class Desk extends Model {

    public static enum DeskState{
        NEW("New"), BOOKED("Booked"), BEING_USED("Being used");

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

    @Column(nullable = false)
    public Long deskCode;

    @Enumerated(EnumType.STRING)
    public DeskState deskState = DeskState.NEW;


    @ManyToMany
    public List<OrderToDesk> orderToDesks = Lists.newArrayList();

    /* EBEAN */
    public static Finder<Long, Desk> find = new Finder(Long.class, Desk.class);

    public static List<Desk> all(){ return find.all(); }

    public static Desk findDeskByCode(Long deskCode) {
        return Ebean.find(Desk.class).where().eq("deskCode", deskCode).findUnique();
    }

    public ObjectNode toJson() {
        ObjectNode json = Json.newObject();
        json    .put("id", id)
                .put("deskNumber", deskNumber)
                .put("deskCode", deskCode)
                .put("deskState", deskState.getName());
        return json;
    }

    public static Desk authenticateDesk(Long deskNumber, Long deskCode) {
        return Ebean.find(Desk.class).where()
                .eq("deskNumber", deskNumber)
                .eq("deskCode", deskCode)
                .findUnique();
    }
}
