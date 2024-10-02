package MINGESO.Proyecto_monolitico.services;

import MINGESO.Proyecto_monolitico.entities.AutorizationsEntity;
import MINGESO.Proyecto_monolitico.repositories.AutorizationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AutorizationsService {
    @Autowired
    AutorizationsRepository autorizationsRepository;

    public ArrayList<AutorizationsEntity> getAutorizations(){
        return (ArrayList<AutorizationsEntity>) autorizationsRepository.findAll();
    }

    public AutorizationsEntity saveAutorizations(AutorizationsEntity authorization){
        return autorizationsRepository.save(authorization);
    }
}
