package org.cnam.sample.service;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.dto.Request.RequestGetTransactionDto;
import org.cnam.sample.dto.Request.RequestNewTransactionDto;
import org.cnam.sample.dto.Response.ResponseGetTransactionDto;
import org.cnam.sample.dto.Response.ResponseNewTransactionDto;
import org.cnam.sample.model.TransactionModel;
import org.cnam.sample.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @PersistenceContext
    private EntityManager em;

    public TransactionService() {
    }

    public ResponseNewTransactionDto createNewTransaction(RequestNewTransactionDto data){
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setIdEmetteur(  data.getIdEmetteur());
        transactionModel.setIdRecepteur(  data.getIdRecepteur());
        transactionModel.setAmount(  data.getAmount());
        transactionModel.setType(  data.getType());
        transactionModel.setIdType(  data.getIdType());

        // Everything goes well, save the transaction
        TransactionModel transacModelSaved = transactionRepository.save(transactionModel);
        return new ResponseNewTransactionDto("Transaction saved",new Transaction(transacModelSaved));
    }

    public ResponseGetTransactionDto getAllTransaction(UUID id){
        //Create the Res DTO
        ResponseGetTransactionDto responseGetTransactionDto = new ResponseGetTransactionDto();

        // Prepare the DB Request
        TypedQuery<TransactionModel> query = em.createQuery("FROM TransactionModel t WHERE t.idEmetteur = :id OR t.idRecepteur = :id", TransactionModel.class);
        query.setParameter("id", id);

        // Prepare the Res DTO
        responseGetTransactionDto.setAllTransacModel(query.getResultList());
        responseGetTransactionDto.setIdCompte(id);

        // Send the result
        return responseGetTransactionDto;
    }
}