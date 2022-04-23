package org.cnam.sample.controller;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.dto.Request.RequestGetTransactionDto;
import org.cnam.sample.dto.Request.RequestNewTransactionDto;
import org.cnam.sample.dto.Response.ResponseGetTransactionDto;
import org.cnam.sample.dto.Response.ResponseNewTransactionDto;
import org.cnam.sample.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping()
    @ResponseBody
    public ResponseNewTransactionDto saveTransaction (@RequestBody RequestNewTransactionDto newTransactionDto){
        return transactionService.createNewTransaction(newTransactionDto);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseGetTransactionDto getAllTransaaction ( @PathVariable("id") UUID id){
        return transactionService.getAllTransaction(id);
    }
}
