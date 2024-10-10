package MINGESO.Proyecto_monolitico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lateArrivals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LateArrivalsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rut;
    private int month;
    private int year;
    private int numLateMinutes;
}
