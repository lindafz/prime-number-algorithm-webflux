package com.lindazf.primeness.controller;

import com.lindazf.primeness.model.PrimeRequest;
import com.lindazf.primeness.model.PrimeResponse;
import com.lindazf.primeness.repository.PrimeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@Slf4j
@Controller
public class PrimeController {
    @Autowired
    private PrimeRepository primeRepository;

    @RequestMapping("/")
    public String index(final Model model) {
        // data streaming, data driven mode.
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(primeRepository.findAll(), 1);
        model.addAttribute("primes", reactiveDataDrivenMode);
        return "index";
    }

    @PostMapping("/prime")
    public String processRequest( @ModelAttribute("pdata") String command){


//        PrimeResponse response = primeRepository.processRequest(command).block();

        log.info("Prime Response Result:" + command);

        return "redirect:/prime/response";
    }
}

