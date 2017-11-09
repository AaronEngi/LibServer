package wang.tyrael.springsupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    Log log = LogFactory.getLog(this.getClass());


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // This is actually not an error, but an OK message. It is sent to avoid redirects.
        log.info("onAuthenticationSuccess start");
        response.sendError(HttpServletResponse.SC_OK);
        log.info("onAuthenticationSuccess finish");
    }
}
