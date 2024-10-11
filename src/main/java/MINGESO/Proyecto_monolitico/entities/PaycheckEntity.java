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
    private String name;
    private String lastName;
    private String category;
    private int servicesYears;
    private int monthSalary;
    private double servicesYearsBonus;
    private int extraHoursBonus;
    private int discounts;
    private int grossSalary;
    private int forecastQuote;
    private int healthQuote;
    //private int childrenBonus;

    private int totalSalary;
}

