package org.cnam.sample.service;

import org.cnam.sample.domain.Email;
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
    public String url_mail ;
    @Value("${application.mail.feature.send}")
    public String url_mail_send ;

    @Value("${application.facture.url}")
    public String url_facture ;
    @Value("${application.facture.feature.create}")
    public String url_facture_create ;

    @Value("${application.compte.url}")
    public String url_compte ;
    @Value("${application.compte.feature.withdraw}")
    public String url_compte_withdraw ;

    @Value("${application.client.url}")
    public String url_client;
    @Value("${application.client.feature.getLogin}")
    public String url_client_getLogin;

    @Value("${application.securite.url}")
    public String url_securite;
    @Value("${application.securite.feature.service}")
    public String url_securite_service;
    @Value("${application.securite.feature.check}")
    public String url_securite_check ;

    @Value("${application.monetique.url}")
    public String url_monetique;
    @Value("${application.monetique.feature.service}")
    public String url_monetique_service;
    @Value("${application.monetique.feature.check}")
    public String url_monetique_check ;

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

        //ask user to have login
        requestStrategy = new ClientRequestStrategy(url_client,url_client_getLogin);
        ResponseClientDto responseClientDto = (ResponseClientDto ) requestStrategy.callRemote(message,new RequestClientDto(transactionModel.getIdEmetteur()));
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error with Client");
        }

        //ask Security for auth for login
        requestStrategy = new SecurityRequestStrategy(url_securite, url_securite_check,url_securite_service);
        requestStrategy.callRemote(message,new RequestSecurityRightDto(responseClientDto.getLastName(),"Transaction"));
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error with Security");
        }


        //ask monetique if card can pay (isn't blocked)
        requestStrategy = new MonetiqueRequestStrategy(url_monetique, url_monetique_check,url_monetique_service);
        requestStrategy.callRemote(message,new RequestMonetiqueDto(transactionModel.getIdType().toString()));
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error with Monetique");
        }

        //ask withdraw account
        message.add("cd_label :" + "Withdraw");
        requestStrategy = new CompteRequestStrategy(url_compte,  url_compte_withdraw);
        requestStrategy.callRemote(message,new RequestWithdrawCompteDto(UUID.fromString(transactionModel.getIdEmetteur()),transactionModel.getAmount().negate()));
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error width Account withdrawal");
        }

        //ask credit account
        message.add("cd_label: " + "Credit");
        requestStrategy = new CompteRequestStrategy(url_compte, url_compte_withdraw);
        requestStrategy.callRemote(message,new RequestWithdrawCompteDto(UUID.fromString(transactionModel.getIdRecepteur()), transactionModel.getAmount()));
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error width Account Crediting");
        }

        //create  facture
        requestStrategy = new FactureRequestStrategy(url_facture,url_facture_create);
        requestStrategy.callRemote(message,new NewFactureDto(UUID.fromString(transactionModel.getIdEmetteur()),"transaction",1.0,Date.from(Instant.now())));
        if(!requestStrategy.status(message)){
            err = true;
            message.add("err :"+"Error with Facture");
        }

        TransactionModel transacModelSaved = transactionModel;

        // Everything goes well, save the transaction else we return unchanged transaction
        if(!err)
           transacModelSaved = transactionRepository.save(transactionModel);

        //call mail
        requestStrategy = new EmailRequestStrategy(url_mail,url_mail_send);
        HashMap<String,String> vals = new HashMap<>();
        vals.put("message",String.join(" \r\n", message));
        Email email = new Email("cnam@grobert.ovh",vals);
        requestStrategy.callRemote(message,new RequestMailDto(email,"Transaction"));
        requestStrategy.status(message);

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