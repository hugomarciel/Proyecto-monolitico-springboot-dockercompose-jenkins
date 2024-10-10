package MINGESO.Proyecto_monolitico.repositories;
import java.util.List;
import MINGESO.Proyecto_monolitico.entities.InAndOutRegEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface InAndOutRegRepository extends JpaRepository<InAndOutRegEntity, Long> {
    @Query("SELECT r FROM InAndOutRegEntity r WHERE EXTRACT(MONTH FROM r.date) = ?1 AND EXTRACT(YEAR FROM r.date) = ?2")
    List<InAndOutRegEntity> findByMonthAndYear(int month, int year);
}
