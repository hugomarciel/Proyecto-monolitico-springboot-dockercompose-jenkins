package MINGESO.Proyecto_monolitico.services;


import MINGESO.Proyecto_monolitico.entities.JustificationsEntity;
import MINGESO.Proyecto_monolitico.repositories.JustificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JustificationsService {
    @Autowired
    JustificationsRepository justificationsRepository;

    public ArrayList<JustificationsEntity> getJustifications(){
        return (ArrayList<JustificationsEntity>) justificationsRepository.findAll();
    }

    public JustificationsEntity saveJustification(JustificationsEntity justification){return justificationsRepository.save(justification);
    }
}
