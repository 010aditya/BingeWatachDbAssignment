package com.dbAissgnment.BingeWatachDbAssignment.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Http401UnAutherizedEntryPoint implements AuthenticationEntryPoint {
    private static final Log logger = LogFactory.getLog(Http403ForbiddenEntryPoint.class);

    public Http401UnAutherizedEntryPoint() {
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        logger.debug("Pre-authenticated entry point called. Rejecting access");
        response.sendError(401, "UnAuthorized");
    }

}