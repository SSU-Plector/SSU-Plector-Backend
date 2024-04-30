package ssuPlector.security.handler;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ssuPlector.global.exception.GlobalException;
import ssuPlector.global.response.ApiResponse;
import ssuPlector.global.response.code.GlobalErrorCode;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(403);

        ApiResponse<GlobalException> errorResponse =
                ApiResponse.onFailure(GlobalErrorCode._FORBIDDEN, null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
