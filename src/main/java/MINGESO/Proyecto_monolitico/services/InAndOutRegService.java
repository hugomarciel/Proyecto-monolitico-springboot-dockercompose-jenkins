package MINGESO.Proyecto_monolitico.services;

import MINGESO.Proyecto_monolitico.entities.InAndOutRegEntity;
import MINGESO.Proyecto_monolitico.repositories.InAndOutRegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class InAndOutRegService {

    @Autowired
    private InAndOutRegRepository inAndOutRegRepository; // Eliminar 'static'

    public List<InAndOutRegEntity> importData(String filePath) { // Eliminar 'static'
        List<InAndOutRegEntity> inAndOutRegs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Formateadores para las fechas y horas
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");  // Dividimos la línea en partes

                // Convertimos los datos
                LocalDate date = LocalDate.parse(parts[0], dateFormatter);
                LocalTime time = LocalTime.parse(parts[1], timeFormatter);
                String rutEmployee = parts[2];

                // Creamos una nueva entidad InAndOutReg
                InAndOutRegEntity reg = new InAndOutRegEntity();
                reg.setDate(date);
                reg.setTime(time);
                reg.setRutEmployee(rutEmployee);

                // Guardamos el registro en la lista
                inAndOutRegs.add(reg);
            }

            // Guardamos todos los registros en la base de datos
            inAndOutRegRepository.saveAll(inAndOutRegs);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return inAndOutRegs;
    }
}
