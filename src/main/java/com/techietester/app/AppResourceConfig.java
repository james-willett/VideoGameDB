package com.techietester.app;

import com.techietester.resource.VideoGameResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.MediaType;

@Component
@ApplicationPath("/app")
public class AppResourceConfig extends ResourceConfig {

    public AppResourceConfig() {

        packages("in.techietester.resource", "in.techietester.app");
        register(VideoGameResource.class);

        configureSwagger();
    }

    private void configureSwagger() {
        register(ApiListingResource.class);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[] { "http", "https" });
        beanConfig.setBasePath("/app");
        beanConfig.setTitle("Video Game Database - Test Application");
        beanConfig.setDescription("https://github.com/TechieTester/VideoGameDB");
        beanConfig.getSwagger().addConsumes(MediaType.APPLICATION_JSON);
        beanConfig.getSwagger().addProduces(MediaType.APPLICATION_JSON);
        beanConfig.setContact("James Willett");
        beanConfig.setResourcePackage("com.techietester.resource");
        beanConfig.setPrettyPrint(false);
        beanConfig.setScan();
    }

}
