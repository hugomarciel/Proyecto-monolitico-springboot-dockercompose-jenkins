package MINGESO.Proyecto_monolitico.controllers;

//MINGESO.Proyecto_monolitico
import MINGESO.Proyecto_monolitico.entities.EmployeeEntity;
import MINGESO.Proyecto_monolitico.entities.JustificationsEntity;
import MINGESO.Proyecto_monolitico.services.JustificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/justifications")
@CrossOrigin("*")
public class justificactionController {
    @Autowired
    JustificationsService justificationsService;

    @GetMapping("/")
    public ResponseEntity<List<JustificationsEntity>> listJustifications() {
        List<JustificationsEntity> justifications = justificationsService.getJustifications();
        return ResponseEntity.ok(justifications);
    }

    @PostMapping("/")
    public ResponseEntity<JustificationsEntity> saveJustification(@RequestBody JustificationsEntity justification) {
        JustificationsEntity justificationNew = justificationsService.saveJustification(justification);
        return ResponseEntity.ok(justificationNew);
    }
}
