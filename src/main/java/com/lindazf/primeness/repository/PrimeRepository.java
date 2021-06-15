package com.lindazf.primeness.repository;
import com.lindazf.primeness.model.PrimeRequest;
import com.lindazf.primeness.model.PrimeResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PrimeRepository {

     String PRIME_DATA  =" Is a Prime Integer";
     String PRIME_NONE  =" Is not a Prime Integer";

    Flux<PrimeResponse> findAll();

    Mono<PrimeResponse> processRequest(Integer command);
}
