package com.mani.webflux.router;

import com.mani.webflux.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CustomerRouter {

    @Autowired
    private CustomerHandler customerHandler;

    @Bean
    public RouterFunction<ServerResponse> getRouterFunction() {
        return RouterFunctions.route()
                .GET("/router/customer", customerHandler::getCustomersRouter)
                .GET("/router/customer/stream", customerHandler::getCustomersRouterStream)
                .GET("/router/customer/{input}", customerHandler::getCustomerRouterById)
                .POST("/router/customer/save", customerHandler::saveCustomer)
                .build();
    }

}
