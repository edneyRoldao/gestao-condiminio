package online.codemize.gestaocondominio.converter;

import lombok.RequiredArgsConstructor;
import online.codemize.gestaocondominio.domain.Despesa;
import online.codemize.gestaocondominio.domain.enums.StatusDespesa;
import online.codemize.gestaocondominio.dto.DespesaRequest;
import online.codemize.gestaocondominio.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static online.codemize.gestaocondominio.utils.DateUtils.stringToLocalDate;

@Component
@RequiredArgsConstructor
public class DespesaConverter {

    private final UsuarioService usuarioService;

    public Despesa convert(DespesaRequest request) {
        var usuario = usuarioService.obterUsuario(request.idUsuario());

        Despesa despesa = new Despesa();
        BeanUtils.copyProperties(request, despesa);

        despesa.setUsuario(usuario);
        despesa.setDataVencimento(stringToLocalDate(request.dataVencimento()));
        despesa.setDataPagamento(stringToLocalDate(request.dataPagamento()));
        despesa.setDataCriacao(LocalDateTime.now());
        despesa.setStatus(obterStatusDespesa(despesa));

        return despesa;
    }

    private StatusDespesa obterStatusDespesa(Despesa despesa) {
        if (Objects.nonNull(despesa.getDataPagamento()))
            return StatusDespesa.PAGO;

        if (despesa.getDataVencimento().isBefore(LocalDate.now()))
            return StatusDespesa.ATRASADO;

        return StatusDespesa.NAO_PAGO;
    }

}
