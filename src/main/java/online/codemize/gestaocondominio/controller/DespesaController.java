package online.codemize.gestaocondominio.controller;

import lombok.RequiredArgsConstructor;
import online.codemize.gestaocondominio.dto.DespesaRequest;
import online.codemize.gestaocondominio.service.DespesaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/despesas")
public class DespesaController {

    private final DespesaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDespesa(@RequestBody DespesaRequest request) {
        service.cadastrar(request);
    }

}
