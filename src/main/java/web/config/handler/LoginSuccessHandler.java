package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession httpSession = httpServletRequest.getSession();
        User user = (User) authentication.getPrincipal();
        httpSession.setAttribute("user", user);
        if (user.getRoles()
                .stream()
                .anyMatch(role -> role.name().equals("ADMIN"))) {
            httpServletResponse.sendRedirect("/admin");
        }else {
            httpServletResponse.sendRedirect("/user");
        }

    }

    }
