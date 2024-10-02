package MINGESO.Proyecto_monolitico.controllers;

import MINGESO.Proyecto_monolitico.entities.AutorizationsEntity;
import MINGESO.Proyecto_monolitico.services.AutorizationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authorizations")
@CrossOrigin("*")
public class AutorizationsController {
    @Autowired
    AutorizationsService autorizationsService;

    @GetMapping("/")
    public ResponseEntity<List<AutorizationsEntity>> listAutorizations() {
        List<AutorizationsEntity> autorizations = autorizationsService.getAutorizations();
        return ResponseEntity.ok(autorizations);
    }

    @PostMapping("/")
    public ResponseEntity<AutorizationsEntity> saveAuthorization(@RequestBody AutorizationsEntity authorization) {
        AutorizationsEntity authorizationNew = autorizationsService.saveAutorizations(authorization);
        return ResponseEntity.ok(authorizationNew);
    }
}
