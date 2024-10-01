package MINGESO.Proyecto_monolitico.services;

import MINGESO.Proyecto_monolitico.entities.JustificacionsEntity;
import MINGESO.Proyecto_monolitico.repositories.JustificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificacionsService {
    @Autowired
    JustificationsRepository justificationsRepository;

    public JustificacionsEntity saveJustification(JustificacionsEntity justification){return justificationsRepository.save(justification);
    }
}
