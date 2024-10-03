package MINGESO.Proyecto_monolitico.repositories;

import MINGESO.Proyecto_monolitico.entities.InAndOutRegEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InAndOutRegRepository extends JpaRepository<InAndOutRegEntity, Long> {
}
