package online.codemize.gestaocondominio.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CheckAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        var token = request.getHeader("token-app");
        var isTokenValido = token != null && token.equals("abc123");

        if (isTokenValido)
            return true;

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("voce nao tem autorizacao para acessar este servico");
        return false;
    }

}
