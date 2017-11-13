package wang.tyrael.springsupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    Log log = LogFactory.getLog(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.info("onAuthenticationFailure:" + exception);
        log.info("onAuthenticationFailure:start:" + response.isCommitted());
        String msg = null;
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            msg = "登录失败: 用户名或密码错误";
        }else{
            msg = exception.getMessage();
        }
        response.setCharacterEncoding("utf-8");
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, msg);
        //response.flushBuffer();
        log.info("onAuthenticationFailure:finish:" + response.isCommitted());
    }
}
