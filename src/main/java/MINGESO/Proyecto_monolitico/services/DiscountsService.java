
package MINGESO.Proyecto_monolitico.services;


import MINGESO.Proyecto_monolitico.entities.LateArrivalsEntity;

import MINGESO.Proyecto_monolitico.repositories.JustificationsRepository;
import MINGESO.Proyecto_monolitico.repositories.LateArrivalsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountsService {

    @Autowired
    LateArrivalsRepository lateArrivalsRepository;

    @Autowired
    JustificationsRepository justificationsRepository;

    // Método para calcular el descuento por atrasos
    public double calculateLateArrivalDiscount(String rut, int year, int month, double grossSalary) {
        // Obtener los minutos de atraso del empleado para el mes y año
        List<LateArrivalsEntity> lateArrivals = lateArrivalsRepository.getLateArrivalsByRutYearMonth(rut, year, month);
        int totalLateMinutes = lateArrivals.stream().mapToInt(LateArrivalsEntity::getNumLateMinutes).sum();

        // Verificar si hay justificativos
        boolean hasJustification = justificationsRepository.findAll().stream()
                .anyMatch(justification -> justification.getRutEmployee().equals(rut) &&
                        justification.getDate().getYear() == year &&
                        justification.getDate().getMonthValue() == month);

        // Aplicar descuentos según los minutos de atraso y justificativo
        if (hasJustification && totalLateMinutes > 70) {
            return 0; // Si tiene justificativo y el atraso es mayor a 70 minutos, no hay descuento
        } else if (totalLateMinutes > 70) {
            return grossSalary; // Se considera inasistencia y se descuenta el salario completo
        } else if (totalLateMinutes > 45) {
            return grossSalary * 0.06; // 6% de descuento
        } else if (totalLateMinutes > 25) {
            return grossSalary * 0.03; // 3% de descuento
        } else if (totalLateMinutes > 10) {
            return grossSalary * 0.01; // 1% de descuento
        }

        return 0; // No hay descuento si el atraso es menor a 10 minutos
    }
}
