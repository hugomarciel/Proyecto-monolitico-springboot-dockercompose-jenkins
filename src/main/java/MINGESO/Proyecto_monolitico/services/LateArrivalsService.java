package MINGESO.Proyecto_monolitico.services;

import MINGESO.Proyecto_monolitico.entities.LateArrivalsEntity;
import MINGESO.Proyecto_monolitico.entities.InAndOutRegEntity;
import MINGESO.Proyecto_monolitico.repositories.LateArrivalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class LateArrivalsService {

    @Autowired
    LateArrivalsRepository lateArrivalsRepository;

    @Autowired
    InAndOutRegService inAndOutRegService;

    // Calcular y guardar los minutos de atraso
    public void calculateAndSaveLateArrivals(int month, int year) {
        System.out.println("entrandooooo 1");

        // Definir el horario de inicio de la jornada laboral
        LocalTime workStartTime = LocalTime.of(8, 0);

        // Obtener las marcas de entrada y salida del reloj de control
        List<InAndOutRegEntity> inAndOutRegs = inAndOutRegService.getAllInAndOutRegsByMonthAndYear(month, year);
        System.out.println("entrandooooo 2 - Cantidad de registros obtenidos: " + inAndOutRegs.size());

        // Si la lista está vacía, imprimir un mensaje de advertencia
        if (inAndOutRegs.isEmpty()) {
            System.out.println("No se encontraron registros para el mes y año indicados.");
            return;
        }

        // Agrupar por RUT y fecha
        Map<String, Map<LocalDate, List<InAndOutRegEntity>>> recordsByEmployeeAndDate = inAndOutRegs.stream()
                .collect(Collectors.groupingBy(InAndOutRegEntity::getRutEmployee,
                        Collectors.groupingBy(InAndOutRegEntity::getDate))); // Usar el atributo date
        System.out.println("entrandooooo 3 - Cantidad de empleados: " + recordsByEmployeeAndDate.size());

        // Calcular minutos de atraso para cada empleado
        for (String rut : recordsByEmployeeAndDate.keySet()) {
            Map<LocalDate, List<InAndOutRegEntity>> recordsByDate = recordsByEmployeeAndDate.get(rut);
            System.out.println("Procesando empleado con RUT: " + rut + ", cantidad de días con registros: " + recordsByDate.size());

            int totalLateMinutes = 0;

            for (Map.Entry<LocalDate, List<InAndOutRegEntity>> entry : recordsByDate.entrySet()) {
                List<InAndOutRegEntity> records = entry.getValue();
                System.out.println("Fecha: " + entry.getKey() + ", cantidad de registros para este día: " + records.size());

                // Verificar que hay al menos un registro de entrada
                if (!records.isEmpty()) {
                    InAndOutRegEntity firstRecord = records.get(0); // Primer registro asumido como entrada

                    // Calcular los minutos de atraso
                    if (firstRecord.getTime().isAfter(workStartTime)) {
                        int lateMinutes = (int) Duration.between(workStartTime, firstRecord.getTime()).toMinutes();
                        System.out.println("Atraso para el día " + entry.getKey() + ": " + lateMinutes + " minutos");
                        totalLateMinutes += lateMinutes;
                    }
                }
            }

            System.out.println("Total minutos de atraso para empleado con RUT: " + rut + ": " + totalLateMinutes);

            // Guardar los minutos de atraso calculados
            LateArrivalsEntity lateArrival = new LateArrivalsEntity();
            lateArrival.setRut(rut);
            lateArrival.setMonth(month);
            lateArrival.setYear(year);
            lateArrival.setNumLateMinutes(totalLateMinutes);
            System.out.println("entrandooooo 6 - Guardando atraso para empleado con RUT: " + rut);
            lateArrivalsRepository.save(lateArrival);
            System.out.println("entrandooooo 7 - Guardado exitoso para empleado con RUT: " + rut);
        }
    }
}
