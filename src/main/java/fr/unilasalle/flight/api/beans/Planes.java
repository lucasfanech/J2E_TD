package fr.unilasalle.flight.api.beans;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "planes")
public class Planes extends PanacheEntityBase{

    @Id
    @Getter
    @SequenceGenerator(name = "planes_sequence", sequenceName = "planes_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planes_sequence")
    private Integer id;

    @Getter
    @Setter
    @Column(nullable = false, name="operator")
    private String operator;

    @Getter
    @Setter
    @Column(nullable = false, name="model")
    private String model;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, name="registration")
    private String registration;

    @Getter
    @Setter
    @Column(nullable = false, name="capacity")
    private Integer capacity;

}
