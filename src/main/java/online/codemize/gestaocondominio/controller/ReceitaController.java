package online.codemize.gestaocondominio.controller;

import lombok.RequiredArgsConstructor;
import online.codemize.gestaocondominio.domain.Receita;
import online.codemize.gestaocondominio.dto.ReceitaRequest;
import online.codemize.gestaocondominio.service.ReceitaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/receitas")
public class ReceitaController {

    private final ReceitaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarReceita(@RequestBody ReceitaRequest request) {
        service.cadastrar(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Receita> listarTodasReceitas() {
        return service.listarTodas();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Receita buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

}
