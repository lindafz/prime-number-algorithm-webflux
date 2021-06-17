package com.lindazf.primeness.controller;

import com.lindazf.primeness.exceptioon.NotFoundException;
import com.lindazf.primeness.model.PrimeRequest;
import com.lindazf.primeness.repository.PrimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import javax.validation.Valid;

@Slf4j
@Controller
public class PrimeController {

    private final PrimeRepository primeRepository;

    public PrimeController(PrimeRepository primeRepository){
        this.primeRepository = primeRepository;
    }

    @GetMapping("/")
    public String getIndexPage(Model model) {
        // data streaming, data driven mode.
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(primeRepository.findAll(), 1);
        model.addAttribute("primes", reactiveDataDrivenMode);
        return "index";
    }
    @GetMapping("/prime")
    public String getRequestForm(Model model) {
        model.addAttribute("prime", new PrimeRequest ());
        return "prime/request";
    }

    @PostMapping("/prime")
    public String processRequest(@Valid @ModelAttribute PrimeRequest prime, Model model){
        log.info("Prime input:" + prime.getInput());
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(primeRepository.processRequest(prime), 1);
        model.addAttribute("response", reactiveDataDrivenMode);
        return "prime/response";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class, TemplateInputException.class})
    public String handleNotFound(Exception exception, Model model){
        log.error("Handling not found exception");
        log.error(exception.getMessage());
        model.addAttribute("exception", exception);
        return "404Error";
    }

}

