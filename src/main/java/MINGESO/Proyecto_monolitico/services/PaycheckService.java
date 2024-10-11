package MINGESO.Proyecto_monolitico.services;

import MINGESO.Proyecto_monolitico.entities.EmployeeEntity;
import MINGESO.Proyecto_monolitico.entities.PaycheckEntity;
import MINGESO.Proyecto_monolitico.repositories.PaycheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaycheckService {
    @Autowired
    PaycheckRepository paycheckRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    OfficeHRMService officeHRMService;

    @Autowired
    ExtraHoursService extraHoursService;

    public ArrayList<PaycheckEntity> getPaychecks() {
        return (ArrayList<PaycheckEntity>) paycheckRepository.findAll();
    }

    public PaycheckEntity savePaycheck(PaycheckEntity paycheck) {
        return paycheckRepository.save(paycheck);
    }

    public ArrayList<PaycheckEntity> getPaychecksByYearMonth(Integer year, Integer month) {
        return (ArrayList<PaycheckEntity>) paycheckRepository.getPaychecksByYearMonth(year, month);
    }

    public Boolean calculatePaychecks(int year, int month) {
        List<EmployeeEntity> employees = employeeService.getEmployees();

        for (EmployeeEntity employee : employees) {
            PaycheckEntity paycheck = new PaycheckEntity();
            paycheck.setRut(employee.getRut());
            paycheck.setName(employee.getNames());  // Cambiado
            paycheck.setLastName(employee.getLastNames());  // Cambiado
            paycheck.setCategory(employee.getCategory());  // Cambiado
            int servicesYears = officeHRMService.calculateYearsOfService(employee);
            paycheck.setServicesYears(servicesYears);
            int monthlySalary = officeHRMService.getMonthlySalary(employee);
            paycheck.setMonthSalary(monthlySalary);
            double yearsServicesBonus = officeHRMService.calculateYearsServiceBonus(employee);
            paycheck.setServicesYearsBonus(yearsServicesBonus);
            int extraHoursBonus = extraHoursService.getTotalExtraHoursAmountByRutYearMonth(employee.getRut(), year, month);
            paycheck.setExtraHoursBonus(extraHoursBonus);



            // Calcular el salario total
            //paycheck.setTotalSalary(
            //        paycheck.getGrossSalary() +
            //                paycheck.getServicesYearsBonus() +
             //               paycheck.getExtraHoursBonus()
            //);

            paycheckRepository.save(paycheck);  // Guardar el cheque de pago
        }

        return true;
    }
}
