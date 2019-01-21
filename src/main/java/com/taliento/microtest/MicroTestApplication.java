package com.taliento.microtest;

import com.taliento.microtest.health.TemplateHealthCheck;
import com.taliento.microtest.resources.MicroTestResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MicroTestApplication extends Application<MicroTestConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MicroTestApplication().run(args);
    }

    @Override
    public String getName() {
        return "MicroTest";
    }

    @Override
    public void initialize(final Bootstrap<MicroTestConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MicroTestConfiguration configuration,
                    final Environment environment) {
        final MicroTestResource resource = new MicroTestResource(configuration.getTemplate(), configuration.getDefaultName());
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
            environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
