package com.techietester.config;

import com.techietester.resource.PublisherResource;
import com.techietester.resource.VideoGameResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("in.techietester.resource", "in.techietester.config");

        register(VideoGameResource.class);
        register(PublisherResource.class);
    }
}
