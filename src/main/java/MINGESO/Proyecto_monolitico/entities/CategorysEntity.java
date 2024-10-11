package MINGESO.Proyecto_monolitico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    private String category;  // Nombre de la categoría (A, B, C, etc.)
    private int salary;  // Sueldo fijo mensual según la categoría
    private int extraHourRate;  // Monto a pagar por hora extra según la categoría
}