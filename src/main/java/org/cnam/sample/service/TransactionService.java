package org.cnam.sample.service;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.dto.Request.*;
import org.cnam.sample.dto.Response.*;
import org.cnam.sample.dto.Response.ResponseWithdrawCompteDto;
import org.cnam.sample.model.TransactionModel;
import org.cnam.sample.repository.TransactionRepository;
import org.cnam.sample.service.Requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @PersistenceContext
    private EntityManager em;

    @Value("${application.mail.url}")
    private String url_mail ;
    @Value("${application.mail.feature.send}")
    private String url_mail_send ;

    @Value("${application.monetique.url}")
    private String url_monetique ;

    public TransactionService() {
    }

    public ResponseNewTransactionDto createNewTransaction(RequestNewTransactionDto data){
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setIdEmetteur(  data.getIdEmetteur());
        transactionModel.setIdRecepteur(  data.getIdRecepteur());
        transactionModel.setAmount(  data.getAmount());
        transactionModel.setType(  data.getType());
        transactionModel.setIdType(  UUID.fromString(data.getIdType()));
        List<String> message = new ArrayList<String>();
        RequestStrategy requestStrategy;
        boolean err = false;


        message.add("Recv :"+transactionModel.getIdRecepteur());
        message.add("Emet :"+transactionModel.getIdEmetteur());
        message.add("Amount :"+transactionModel.getAmount().toString());
        message.add("mail : " + url_mail );

        //ask user to have login
        requestStrategy = new ClientRequestStrategy(new RequestClientDto(transactionModel.getIdEmetteur()));
        ResponseClientDto responseClientDto = (ResponseClientDto ) requestStrategy.callRemote(message);
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error with Security, see mail");
        }

        //ask Security for auth for login
        requestStrategy = new SecurityRequestStrategy(new RequestSecurityRightDto(responseClientDto.getLastName(),"Transaction"));
        requestStrategy.callRemote(message);
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error with Security, see mail");
        }

        //ask withdraw account
        message.add("cd_label :" + "Withdraw");
        requestStrategy = new CompteRequestStrategy(new RequestWithdrawCompteDto(UUID.fromString(transactionModel.getIdEmetteur()),transactionModel.getAmount().negate()));
        requestStrategy.callRemote(message);
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error width withdrawal, see mail");
        }

        //ask credit account
        message.add("cd_label: " + "Credit");
        requestStrategy = new CompteRequestStrategy(new RequestWithdrawCompteDto(UUID.fromString(transactionModel.getIdRecepteur()), transactionModel.getAmount()));
        requestStrategy.callRemote(message);
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error width credit, see mail");
        }

        //create  facture
        requestStrategy = new FactureRequestStrategy(new NewFactureDto(UUID.fromString(transactionModel.getIdEmetteur()),"transaction",1.0,Date.from(Instant.now())));
        requestStrategy.callRemote(message);
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error with facture, see mail");
        }

        TransactionModel transacModelSaved = transactionModel;

        // Everything goes well, save the transaction else we return unchanged transaction
        if(!err)
           transacModelSaved = transactionRepository.save(transactionModel);

        //call mail
        //callRemoteServiceMail("Transaction",message,transactionModel.getIdRecepteur());
        //callRemoteServiceMail("Transaction",message,transactionModel.getIdEmetteur());

        return new ResponseNewTransactionDto(message,new Transaction(transacModelSaved));
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