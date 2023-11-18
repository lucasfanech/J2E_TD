package fr.unilasalle.flight.api.beans;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;


@Entity
@Table(name = "flights")
public class Flight extends PanacheEntityBase{

    @Id
    @Getter
    @SequenceGenerator(name = "flights_sequence", sequenceName = "flights_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flights_sequence")
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name="number")
    private String number;

    @Getter
    @Setter
    @Column(nullable = false, name="origin")
    private String origin;

    @Getter
    @Setter
    @Column(nullable = false, name="destination")
    private String destination;

    @Getter
    @Setter
    @Column(nullable = false, name="departure_date")
    private Date departure_date;

    @Getter
    @Setter
    @Column(nullable = false, name="departure_time")
    private Time departure_time;

    @Getter
    @Setter
    @Column(nullable = false, name="arrival_date")
    private Date arrival_date;

    @Getter
    @Setter
    @Column(nullable = false, name="arrival_time")
    private Time arrival_time;

    // plane_id is a foreign key to the planes table
    @Getter
    @Setter
    @Column(nullable = false, name="plane_id")
    private Integer plane_id;

}
