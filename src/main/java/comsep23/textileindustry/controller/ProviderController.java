package comsep23.textileindustry.controller;


import comsep23.textileindustry.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/supply")
@AllArgsConstructor
public class ProviderController {

    private ProviderService providerService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Textile Industry Provider";
    }



    @GetMapping("/all-needed")
    public List<String> getAllNeededMaterials() {
        return providerService.getAllRequests();
    }

    @GetMapping("/total-quantity")
    public int getTotalQuantity() {
        return providerService.getTotalQuantity();
    }

    @GetMapping("/most-ordered")
    public String getMostRequestedMaterial() {
        return providerService.getMostRequestedMaterial();
    }

    @GetMapping("/least-ordered")
    public String getLeastRequestedMaterial() {
        return providerService.getLeastRequestedMaterial();
    }

}



