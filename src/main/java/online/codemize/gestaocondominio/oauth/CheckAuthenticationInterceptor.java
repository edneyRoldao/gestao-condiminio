package online.codemize.gestaocondominio.oauth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import online.codemize.gestaocondominio.service.JWTService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CheckAuthenticationInterceptor implements HandlerInterceptor {

    private final JWTService jwtService;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        if (handler instanceof HandlerMethod method) {
            try {
                PreAutorizado preAutorizadoAnnotation = method.getMethodAnnotation(PreAutorizado.class);
                if (Objects.isNull(preAutorizadoAnnotation))
                    return true;

                var token = getTokenFromRequest(request);
                jwtService.validarToken(token);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Credenciais invalidas");
                return false;
            }
        }

        return true;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        return token.split("Bearer ")[1];
    }

}
