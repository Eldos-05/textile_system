package comsep23.textileindustry.repository;

import comsep23.textileindustry.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByMaterialNameContainingIgnoreCase(String name);
}
