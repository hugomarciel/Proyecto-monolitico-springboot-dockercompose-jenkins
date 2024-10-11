package MINGESO.Proyecto_monolitico.controllers;

import MINGESO.Proyecto_monolitico.services.LateArrivalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/latearrivals")
@CrossOrigin("*")
public class LateArrivalsController {

    @Autowired
    private LateArrivalsService lateArrivalsService;

    @PostMapping("/calculate/{year}/{month}")
    public ResponseEntity<Void> calculateMinuts(@PathVariable int year, @PathVariable int month) {
        // Llamar al servicio para calcular y guardar los minutos de atraso
        lateArrivalsService.calculateAndSaveLateArrivals(year, month);
        return ResponseEntity.ok().build(); // Devuelve un 200 OK si la operación fue exitosa
    }
}
