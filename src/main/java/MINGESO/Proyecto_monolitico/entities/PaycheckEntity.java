package MINGESO.Proyecto_monolitico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paychecks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaycheckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rut;
    private int year;
    private int month;
    private int monthlySalary;
    private int salaryBonus;
    private int childrenBonus;
    private int extraHoursBonus;
    private int totalSalary;
}

