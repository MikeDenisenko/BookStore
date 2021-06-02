package com.mike.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private static final Logger LOG = LogManager.getLogger(AccessDeniedHandlerImpl.class);

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       org.springframework.security.access.AccessDeniedException e) throws IOException, ServletException {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                LOG.info( "User " + auth.getName() + "attempted to access the protected URL:");
            }
            httpServletResponse.sendRedirect("/");
    }
}
