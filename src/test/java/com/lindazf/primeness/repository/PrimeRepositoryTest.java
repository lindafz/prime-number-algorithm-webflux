package com.lindazf.primeness.repository;

import com.lindazf.primeness.model.PrimeRequest;
import com.lindazf.primeness.model.PrimeResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeRepositoryTest {

    PrimeRepository primeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        primeRepository = new PrimeRepositoryImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void processRequest() {
        Integer input = 6;
        PrimeRequest request = new PrimeRequest(input);
        PrimeResponse response = primeRepository.processRequest(request).blockFirst();
        assertEquals(input.toString(), response.getInput());
    }
}