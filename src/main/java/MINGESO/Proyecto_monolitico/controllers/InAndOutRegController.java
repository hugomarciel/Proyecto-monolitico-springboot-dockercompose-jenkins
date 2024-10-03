package MINGESO.Proyecto_monolitico.controllers;

import MINGESO.Proyecto_monolitico.entities.InAndOutRegEntity;
import MINGESO.Proyecto_monolitico.services.InAndOutRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inandout")
@CrossOrigin("*")
public class InAndOutRegController {

    @Autowired
    InAndOutRegService inAndOutRegService;

    @PostMapping("/import")
    public ResponseEntity<Void> importAssists(@RequestParam("rutaArchivo") String rutaArchivo) {
        try {
            inAndOutRegService.importData(rutaArchivo);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Registrar el error en los logs
            System.err.println("Error al importar el archivo: " + e.getMessage());
            // Retornar respuesta con error interno
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
