package MINGESO.Proyecto_monolitico.repositories;

import MINGESO.Proyecto_monolitico.entities.PaycheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaycheckRepository extends JpaRepository<PaycheckEntity, Long> {
    @Query(value = "SELECT * FROM paychecks WHERE paychecks.year = :year AND paychecks.month = :month ORDER BY paychecks.year, paychecks.month, paychecks.rut", nativeQuery = true)
    List<PaycheckEntity> getPaychecksByYearMonth(@Param("year") int year, @Param("month") int month);

}