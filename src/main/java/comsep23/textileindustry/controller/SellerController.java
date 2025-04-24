package comsep23.textileindustry.controller;


import comsep23.textileindustry.entity.Material;
import comsep23.textileindustry.service.MaterialService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/sale")
@AllArgsConstructor
public class SellerController {

    private final MaterialService materialService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Textile Industry SaleManager";
    }

    @GetMapping("all-materials")
    @PreAuthorize("hasAuthority('ROLE_SALEMAN')")
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ROLE_SALEMAN')")
    public List<Material> getMaterialsByName(@RequestParam String name) {
        return materialService.findByMaterialName(name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SALEMAN')")
    public Material getMaterialById(@PathVariable Long id) {
        Optional<Material> material = materialService.getMaterialById(id);
        return material.orElseThrow(() -> new RuntimeException("Material with id " + id + " not found."));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_SALEMAN')")
    public ResponseEntity<Material> addMaterial(@RequestBody Material material) {
        Material savedMaterial = materialService.addMaterial(material);
        return new ResponseEntity<>(savedMaterial, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_SALEMAN')")
    public Material updateMaterial(@RequestBody Material material) {
        return materialService.updateMaterial(material);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SALEMAN')")
    public String deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return "Material with ID " + id + " solved successfully.";
    }
}
