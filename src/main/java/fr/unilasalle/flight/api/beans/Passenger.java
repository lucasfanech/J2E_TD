package fr.unilasalle.flight.api.beans;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "passengers")
public class Passenger extends PanacheEntityBase{

    @Id
    @Getter
    @SequenceGenerator(name = "passengers_sequence", sequenceName = "passengers_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passengers_sequence")
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, name="surname")
    private String surname;

    @Getter
    @Setter
    @Column(nullable = false, name="firstname")
    private String firstname;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name="email_address")
    private String email_address;


}
