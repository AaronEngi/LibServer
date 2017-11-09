package wang.tyrael.springsupport;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String msg = null;
        if("Bad credentials".equals(exception.getMessage())){
            msg = "登录失败: 用户名或密码错误";
        }else{
            msg = exception.getMessage();
        }
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, msg);
    }
}
