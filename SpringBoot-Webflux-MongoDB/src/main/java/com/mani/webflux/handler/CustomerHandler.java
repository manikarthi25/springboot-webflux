package com.mani.webflux.handler;

import com.mani.webflux.dao.CustomerDao;
import com.mani.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCustomersRouter(ServerRequest serverRequest) {
        Flux<Customer> customerFlux = customerDao.getCustomersRouter();
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }

    public Mono<ServerResponse> getCustomersRouterStream(ServerRequest serverRequest) {
        Flux<Customer> customerFlux = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerFlux, Customer.class);
    }

    public Mono<ServerResponse> getCustomerRouterById(ServerRequest serverRequest) {
        int id = Integer.valueOf(serverRequest.pathVariable("input"));
        //customerDao.getCustomersRouter().filter(c -> c.getId()==0).take(1).single();
        Mono<Customer> customerMono = customerDao.getCustomersRouter().filter(c -> c.getId()==id).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        Mono<Customer> monoCustomer = serverRequest.bodyToMono(Customer.class);
        Mono<String> customerString = monoCustomer.map(dto -> dto.getId() + ": " + dto.getName());
        return ServerResponse.ok().body(customerString, String.class);
    }
}
