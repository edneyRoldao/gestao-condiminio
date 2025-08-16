package online.codemize.gestaocondominio.service.impl;

import online.codemize.gestaocondominio.domain.Unidade;
import online.codemize.gestaocondominio.repository.UnidadeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnidadeServiceImplTest {

    @InjectMocks
    private UnidadeServiceImpl service;

    @Mock
    private UnidadeRepository repository;

    @Test
    void quandoUnidadeExistirDeveRetornar() {
        // cenario
        Long unidadeId = 66L;
        var unidade = new Unidade();
        unidade.setTipo("test tipo");

        // stub (ensinando como camadas externas devem se comportar)
        when(repository.findById(unidadeId)).thenReturn(Optional.of(unidade));

        // execucao
        var result = service.buscarPorId(unidadeId);

        // check
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getTipo(), "test tipo");

        // spy
        verify(repository, times(1)).findById(unidadeId);
    }

}
