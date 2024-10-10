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
        // Definir el horario de inicio de la jornada laboral
        LocalTime workStartTime = LocalTime.of(8, 0);

        // Obtener las marcas de entrada y salida del reloj de control
        List<InAndOutRegEntity> inAndOutRegs = inAndOutRegService.getAllInAndOutRegsByMonthAndYear(month, year);

        // Agrupar por RUT y fecha
        Map<String, Map<LocalDate, List<InAndOutRegEntity>>> recordsByEmployeeAndDate = inAndOutRegs.stream()
                .collect(Collectors.groupingBy(InAndOutRegEntity::getRutEmployee,
                        Collectors.groupingBy(InAndOutRegEntity::getDate))); // Usar el atributo date

        // Calcular minutos de atraso para cada empleado
        for (String rut : recordsByEmployeeAndDate.keySet()) {
            Map<LocalDate, List<InAndOutRegEntity>> recordsByDate = recordsByEmployeeAndDate.get(rut);
            int totalLateMinutes = 0;

            for (Map.Entry<LocalDate, List<InAndOutRegEntity>> entry : recordsByDate.entrySet()) {
                List<InAndOutRegEntity> records = entry.getValue();

                // Verificar que hay al menos un registro de entrada
                if (!records.isEmpty()) {
                    InAndOutRegEntity firstRecord = records.get(0); // Primer registro asumido como entrada

                    // Calcular los minutos de atraso
                    if (firstRecord.getTime().isAfter(workStartTime)) {
                        totalLateMinutes += Duration.between(workStartTime, firstRecord.getTime()).toMinutes();
                    }
                }
            }

            // Guardar los minutos de atraso calculados
            LateArrivalsEntity lateArrival = new LateArrivalsEntity();
            lateArrival.setRut(rut);
            lateArrival.setMonth(month);
            lateArrival.setYear(year);
            lateArrival.setNumLateMinutes(totalLateMinutes);

            lateArrivalsRepository.save(lateArrival);
        }
    }
}

