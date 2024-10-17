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
    private int month;
    private int year;
    private int servicesYears;
    private int monthSalary;
    private double servicesYearsBonus;
    private int extraHoursBonus;
    private double discounts;
    private double grossSalary;
    private double forecastQuote;
    private double healthQuote;
    private int childrenBonus;

    private double totalSalary;
}

