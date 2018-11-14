package org.cnam.sample.service;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.dto.Request.GetTransactionDto;
import org.cnam.sample.dto.Request.NewTransactionDto;
import org.cnam.sample.dto.Response.ResponseNewTransactionDto;
import org.cnam.sample.model.TransactionModel;
import org.cnam.sample.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {



    @Autowired
    TransactionRepository transactionRepository;



    public TransactionService() {
    }


            public ResponseNewTransactionDto createNewTransaction(NewTransactionDto data){
                TransactionModel transactionModelQuery = new TransactionModel(data.idEmetteur,data.idRecepteur,data.amount,data.type,data.idType);
                TransactionModel transacModelSaved = transactionRepository.save(transactionModelQuery);
                return new ResponseNewTransactionDto("Transaction saved",transacModelSaved.getId(),transacModelSaved.getIdEmetteur(),transacModelSaved.getIdRecepteur(),transacModelSaved.getAmount(),transacModelSaved.getType(),transacModelSaved.getIdType());
            }/*

            public Transac getTransac(long id) {
                TransacModel transacModelFound = transacRepository.getOne(id);
                return new Transac(transacModelFound.getId(),transacModelFound.getData());
            }

            public String createNewTransacExternal(TransactionModel data){
                RestTemplate restTemplate = new RestTemplate();
                NewTransactionDto myRequest = new NewTransactionDto(data);

                ResponseNewTransactionDto myResponse = restTemplate.postForObject(clientApplication+ createNewClientPath, myRequest, NewTransactionDto.class);
                LOGGER.info(myResponse.toString());
                return myResponse.toString();
            }*/
}