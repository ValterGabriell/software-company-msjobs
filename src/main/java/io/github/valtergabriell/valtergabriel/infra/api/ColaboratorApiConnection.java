package io.github.valtergabriell.valtergabriel.infra.api;

import io.github.valtergabriell.valtergabriel.application.domain.dto.Colaborator;
import io.github.valtergabriell.valtergabriel.application.domain.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mscolaborator", path = "colaborator")
public interface ColaboratorApiConnection {
    @GetMapping(params = {"cpf"})
    Response<Colaborator> getColaboratorById(@RequestParam("cpf") Long cpf);
}
