package online.codemize.gestaocondominio.service.impl;

import lombok.RequiredArgsConstructor;
import online.codemize.gestaocondominio.domain.Unidade;
import online.codemize.gestaocondominio.dto.UnidadeRequest;
import online.codemize.gestaocondominio.exception.UnidadeNotFoundException;
import online.codemize.gestaocondominio.repository.UnidadeRepository;
import online.codemize.gestaocondominio.service.UnidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository repository;

    @Override
    public void criar(UnidadeRequest request) {
        var unidade = new Unidade();
        BeanUtils.copyProperties(request, unidade);
        repository.save(unidade);
    }

    @Override
    public Unidade buscarPorId(Long idUnidade) {
        return repository
                .findById(idUnidade)
                .orElseThrow(UnidadeNotFoundException::new);
    }

}
