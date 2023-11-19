package fr.unilasalle.flight.api.beans;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "reservations")
public class Reservation extends PanacheEntityBase{

    @Id
    @Getter
    @SequenceGenerator(name = "reservations_sequence", sequenceName = "reservations_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_sequence")
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, name="flight_id")
    private Integer flight_id;


    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;


}
