package com.lindazf.primeness.controller;

import com.lindazf.primeness.model.PrimeRequest;
import com.lindazf.primeness.model.PrimeResponse;
import com.lindazf.primeness.repository.PrimeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PrimeControllerTest {
    @Mock
    Model model;

    MockMvc mockMvc;

    @Mock
    PrimeRepository primeRepository;
    PrimeController primeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        primeController = new PrimeController(primeRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIndexPage() throws Exception {
        String input1 = "6";
        String input2 = "5";
        List<PrimeResponse> responseList = new ArrayList<>();
        PrimeResponse response1 = new PrimeResponse(input1, "Not a Prime");
        PrimeResponse response2 = new PrimeResponse(input2, "a Prime");
        responseList.add(response1);
        responseList.add(response2);
        when(primeRepository.findAll()).thenReturn(Flux.fromIterable(responseList));
        String viewName = primeController.getIndexPage(model);
        assertEquals("index", viewName);
        verify(primeRepository, times(1)).findAll();
    }

    @Test
    void getRequestForm() throws Exception {
        String viewName = primeController.getRequestForm(model);
        assertEquals("prime/request", viewName);
    }

    @Test
    void processRequest() throws Exception {
        Integer input = 6;
        PrimeRequest request = new PrimeRequest(input);
        PrimeResponse response = new PrimeResponse(input.toString(), "Not a Prime");

        when(primeRepository.processRequest(request)).thenReturn(Flux.just(response));
        String viewName = primeController.processRequest(request, model);
        assertEquals("prime/response", viewName);
        verify(primeRepository, times(1)).processRequest(request);
    }
}