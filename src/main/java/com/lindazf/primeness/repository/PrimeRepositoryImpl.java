package com.lindazf.primeness.repository;

import com.lindazf.primeness.model.PrimeRequest;
import com.lindazf.primeness.model.PrimeResponse;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Repository
public class PrimeRepositoryImpl implements PrimeRepository{

    private static List<PrimeResponse> responseList= new ArrayList<>();
    static {
        responseList.add(new PrimeResponse("2019", PRIME_DATA));
        responseList.add(new PrimeResponse("6", PRIME_NONE));
        responseList.add(new PrimeResponse("25", PRIME_NONE));
        responseList.add(new PrimeResponse("5", PRIME_NONE));
        responseList.add(new PrimeResponse("2", PRIME_NONE));
    }

    @Override
    public Flux<PrimeResponse> findAll(){
        return Flux.fromIterable(responseList);
    }

    @Override
    public Flux<PrimeResponse> processRequest(PrimeRequest command){
        Integer input = command.getInput();
        boolean isPrime = isPrime(input);
        String inputData = input.toString();
        String result = "Your data input: " + inputData;
        if(isPrime){
            result = "Congratulations! " + result+ ", is a prime integer.";
        }else{
            result = "Sorry, " + result + ", is not prime ";
        }
        PrimeResponse response = new PrimeResponse(inputData, result);
        return Flux.just(response);
    }


    private    boolean isPrime(int number) {
            if(number <= 2)
                return number == 2;
            else
                return  (number % 2) != 0
                        &&
                        IntStream.rangeClosed(3, (int) Math.sqrt(number))
                                .filter(n -> n % 2 != 0)
                                .noneMatch(n -> (number % n == 0));
        }
    }

