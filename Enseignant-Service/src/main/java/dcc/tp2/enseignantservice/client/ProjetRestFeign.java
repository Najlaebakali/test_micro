package dcc.tp2.enseignantservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Projet-service", url = "http://localhost:8083")
public interface ProjetRestFeign {

    @GetMapping("/Projets/Enseignant/{id}")
    Long nb_Projet_Enseignant(@PathVariable Long id);

}
