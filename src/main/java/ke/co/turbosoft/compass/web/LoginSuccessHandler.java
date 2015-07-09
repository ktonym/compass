package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.LoginStatus;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ktonym on 6/20/15.
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        LoginStatus status = new LoginStatus(true,auth.isAuthenticated(),auth.getName(),null);
        OutputStream output = response.getOutputStream();
        mapper.writeValue(output,status);
    }
}
