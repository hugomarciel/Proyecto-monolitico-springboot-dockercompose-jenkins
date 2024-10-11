package MINGESO.Proyecto_monolitico.repositories;

import MINGESO.Proyecto_monolitico.entities.CategorysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategorysEntity, Long> {
    CategorysEntity findByCategory(String category);
}
