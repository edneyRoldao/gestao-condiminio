package online.codemize.gestaocondominio.controller;

import lombok.RequiredArgsConstructor;
import online.codemize.gestaocondominio.domain.Despesa;
import online.codemize.gestaocondominio.dto.DespesaRequest;
import online.codemize.gestaocondominio.oauth.PreAutorizado;
import online.codemize.gestaocondominio.service.DespesaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/despesas")
public class DespesaController {

    private final DespesaService service;

    @PreAutorizado
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDespesa(@RequestBody DespesaRequest request) {
        service.cadastrar(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Despesa> listarDespesas() {
        return service.listar();
    }

    @GetMapping("filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<Despesa> listarDespesasComFiltro(@RequestParam(value = "status", required = false) String status) {
        return service.listar(status);
    }

    @GetMapping(value = "{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Despesa getDespesaPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

}
