package io.github.valtergabriell.valtergabriel.infra.api;

import io.github.valtergabriell.valtergabriel.application.domain.dto.Lead;
import io.github.valtergabriell.valtergabriel.application.domain.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mslead", path = "lead")
public interface LeadApiConnection{
    @GetMapping(value = "/find-by-id", params = {"cnpj"})
    Response<Lead> findLeadById(@RequestParam("cnpj") Long cnpj);
}
