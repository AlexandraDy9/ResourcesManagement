package com.tw.management.resources.webapp.security;

import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;


@EnableJdbcHttpSession(maxInactiveIntervalInSeconds = 60*60*24*7)
public class HttpSessionConfig {
}
