package org.cnam.sample.controller;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.dto.Request.NewTransactionDto;
import org.cnam.sample.dto.Response.ResponseNewTransactionDto;
import org.cnam.sample.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

   @PostMapping()
    public ResponseNewTransactionDto saveTrancation (final NewTransactionDto newTransactionDto){
        final ResponseNewTransactionDto responseTransaction = transactionService.createNewTransaction(newTransactionDto);
        return responseTransaction;
    }
}
