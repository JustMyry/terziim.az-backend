package com.terziim.backend.auth.entrypoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.security.constans.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
// 403 FORBIDDEN mesajini aliciya vermek ucundur.
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

        IcpResponseStatus epResponseStatus = new IcpResponseStatus(HttpStatus.FORBIDDEN.value(), SecurityConstants.FORBIDDEN_MESSAGE);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, epResponseStatus);
        outputStream.flush();
    }


}
