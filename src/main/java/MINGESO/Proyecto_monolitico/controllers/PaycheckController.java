package MINGESO.Proyecto_monolitico.controllers;

import MINGESO.Proyecto_monolitico.entities.PaycheckEntity;
import MINGESO.Proyecto_monolitico.services.PaycheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paycheck")
@CrossOrigin("*")
public class PaycheckController {
    @Autowired
    PaycheckService paycheckService;

    @GetMapping("/")
    public ResponseEntity<List<PaycheckEntity>> listPaychecks() {
        List<PaycheckEntity> paychecks = paycheckService.getPaychecks();
        return ResponseEntity.ok(paychecks);
    }

    @PostMapping("/calculate/{year}/{month}")
    public ResponseEntity<String> calculatePaychecks(@PathVariable("year") int year, @PathVariable("month") int month) {
        // Ejecutar el cálculo de los paychecks
        boolean success = paycheckService.calculatePaychecks(year, month);

        // Devolver respuesta en función del resultado
        if (success) {
            return ResponseEntity.ok("Planilla calculada exitosamente para " + month + "/" + year);
        } else {
            return ResponseEntity.status(500).body("Error al calcular la planilla para " + month + "/" + year);
        }
    }


}