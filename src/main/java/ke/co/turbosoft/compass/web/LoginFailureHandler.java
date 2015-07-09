package ke.co.turbosoft.compass.web;

import ke.co.turbosoft.compass.entity.LoginStatus;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ktonym on 6/20/15.
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        LoginStatus status = new LoginStatus(false, false, null, "Cannot verify username/password combination");
        OutputStream output = response.getOutputStream();
        mapper.writeValue(output,status);

    }
}
