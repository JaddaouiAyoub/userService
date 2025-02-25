package com.example.service_utilisateur.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "cmi", url = "https://cmiservice-production.up.railway.app/api/cmi")

public interface CmiClient {
    @PutMapping("/{compteId}/{utilisateurId}/assigner-utilisateur")
    boolean assignerUtilisateur(@PathVariable("compteId") String compteId,
                                       @PathVariable("utilisateurId") Long utilisateurId);

}
