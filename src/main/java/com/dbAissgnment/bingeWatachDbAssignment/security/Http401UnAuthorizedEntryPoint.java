package com.dbAissgnment.bingeWatachDbAssignment.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Http401UnAuthorizedEntryPoint implements AuthenticationEntryPoint {
    private static final Log logger = LogFactory.getLog(Http401UnAuthorizedEntryPoint.class);

    public Http401UnAuthorizedEntryPoint() {
    }

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        logger.debug("Pre-authenticated entry point called. Rejecting access");
        response.sendError(401, "UnAuthorized");
    }

}