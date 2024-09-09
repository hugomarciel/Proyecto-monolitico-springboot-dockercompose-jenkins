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

    @GetMapping("/calculate")
    public ResponseEntity<Void> calculatePaychecks(@RequestParam("year") int year, @RequestParam("month") int month) {
        paycheckService.calculatePaychecks(year, month);
        return ResponseEntity.noContent().build();
    }
}