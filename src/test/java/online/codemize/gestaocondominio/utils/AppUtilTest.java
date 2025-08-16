package online.codemize.gestaocondominio.utils;

// junit
// mockito

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AppUtilTest {

    @Test
    void test_isEmailValid_quandoInsereEmailInvalidoDeveRetornarFalse() {
        var emailInvalido = "edney#com.br";
        var result = AppUtil.isEmailValid(emailInvalido);
        Assertions.assertFalse(result);
    }

    @Test
    void test_isEmailValid_quandoInsereEmailValidoDeveRetornarTrue() {
        // sumilacao do cenario
        var emailInvalido = "edney@mail.com.br";

        // execucao
        var result = AppUtil.isEmailValid(emailInvalido);

        // verificacoes
        Assertions.assertTrue(result);
    }

}
