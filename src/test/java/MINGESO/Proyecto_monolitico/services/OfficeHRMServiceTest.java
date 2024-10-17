package MINGESO.Proyecto_monolitico.services;

import MINGESO.Proyecto_monolitico.entities.CategorysEntity;
import MINGESO.Proyecto_monolitico.entities.EmployeeEntity;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class OfficeHRMServiceTest {

    OfficeHRMService officeHRM = new OfficeHRMService();
    EmployeeEntity employee = new EmployeeEntity();

    @Test
    void whenGetSalaryBonus_thenCorrect() {
        // Given
        employee.setSalary(1500); // Salario menor a 2000

        // When
        int salaryBonus = officeHRM.getSalaryBonus(employee);

        // Then
        assertThat(salaryBonus).isEqualTo(150); // 10% de 1500
    }

    @Test
    void whenGetChildrenBonus_withLessThanThreeChildren_thenCorrect() {
        // Given
        employee.setSalary(2000);
        employee.setChildren(2); // Menos de 3 hijos

        // When
        int childrenBonus = officeHRM.getChildrenBonus(employee);

        // Then
        assertThat(childrenBonus).isEqualTo(200); // 5% por cada hijo
    }

    @Test
    void whenGetChildrenBonus_withThreeOrMoreChildren_thenCorrect() {
        // Given
        employee.setSalary(2000);
        employee.setChildren(3); // 3 o más hijos

        // When
        int childrenBonus = officeHRM.getChildrenBonus(employee);

        // Then
        assertThat(childrenBonus).isEqualTo(900); // 15% por cada hijo
    }

    @Test
    void whenGetExtraHoursBonus_thenCorrect() {
        // Given
        employee.setCategory("A"); // Categoría A
        int numExtraHours = 5; // 5 horas extra

        // When
        int extraHoursBonus = officeHRM.getExtraHoursBonus(employee, numExtraHours);

        // Then
        assertThat(extraHoursBonus).isEqualTo(500); // 100 dólares por hora extra
    }

    @Test
    void whenCalculateYearsOfService_thenCorrect() {
        // Given
        employee.setEntryDate(LocalDate.of(2015, 1, 1)); // Fecha de entrada hace 9 años

        // When
        int yearsOfService = officeHRM.calculateYearsOfService(employee);

        // Then
        assertThat(yearsOfService).isEqualTo(9); // Debería ser 9 años
    }
}


