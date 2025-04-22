package comsep23.textileindustry.service;


import comsep23.textileindustry.entity.Material;
import comsep23.textileindustry.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;
    private List<Material> materials;


    public Material updateMaterial(Material material) {
        return materialRepository.save(material);
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Optional<Material> getMaterialById(Long id) {
        return materialRepository.findById(id);
    }

    public List<Material> findByMaterialName(String name) {
        return materialRepository.findByMaterialNameContainingIgnoreCase(name);
    }

}
