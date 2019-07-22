package com.budgettracker;

import com.budgettracker.api.filters.CORSFilter;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.server.ResourceConfig;
import com.budgettracker.api.filters.AuthenticationFilter;

public class AppConfig extends ResourceConfig {
    public AppConfig() {
        property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);

        register(CORSFilter.class);
        register(AuthenticationFilter.class);
    }
}
