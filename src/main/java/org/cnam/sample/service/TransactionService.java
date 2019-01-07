package org.cnam.sample.service;

import org.cnam.sample.domain.Email;
import org.cnam.sample.domain.EmailTemplate;
import org.cnam.sample.domain.Transaction;
import org.cnam.sample.dto.Request.*;
import org.cnam.sample.dto.Response.*;
import org.cnam.sample.model.TransactionModel;
import org.cnam.sample.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @PersistenceContext
    private EntityManager em;

    @Value("${application.compte.url}")
    private String url_compte ;
    @Value("${application.compte.feature.withdraw}")
    private String url_compte_withdraw ;

    @Value("${application.mail.url}")
    private String url_mail ;
    @Value("${application.mail.feature.send}")
    private String url_mail_send ;

    @Value("${application.facture.url}")
    private String url_facture ;
    @Value("${application.facture.feature.create}")
    private String url_facture_create ;

    @Value("${application.securite.url}")
    private String url_securite;
    @Value("${application.securite.feature.service}")
    private String url_securite_service;
    @Value("${application.securite.feature.service}")
    private String url_securite_check ;

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
        transactionModel.setIdType(  data.getIdType());

        Map<String,String> message = new HashMap<String,String>();
        message.put("Recv",transactionModel.getIdRecepteur());
        message.put("Emet",transactionModel.getIdEmetteur());
        message.put("Amount",transactionModel.getAmount().toString());

        /*ask Security
        ResponseSecurityRightDto responseSecurityRightDto = callRemoteSecurity(transactionModel.getIdEmetteur());
        message.put("secu_label","Res sécurité");
        message.put("secu_val",responseSecurityRightDto.isAllowed() ? "true" : "false");

        //ask withdraw account et credit account
        ResponseWithdrawCompteDto responseWithdrawCompteDto_withdraw = callRemoteCompte(transactionModel.getIdEmetteur(), transactionModel.getAmount().negate());
        message.put("wd_label","Withdraw account res");
        message.put("wd_res_1",responseWithdrawCompteDto_withdraw.getMessage());
        if(!responseWithdrawCompteDto_withdraw.getRequestSuceed())
            return new ResponseNewTransactionDto("Error width withdrawal, see mail",null);

        ResponseWithdrawCompteDto responseWithdrawCompteDto_credit = callRemoteCompte(transactionModel.getIdRecepteur(), transactionModel.getAmount());
        message.put("cd_label","Credit account res");
        message.put("cd_res_1",responseWithdrawCompteDto_credit.getMessage());
        if(!responseWithdrawCompteDto_credit.getRequestSuceed())
            return new ResponseNewTransactionDto("Error with Credit, see mail",null);*/

        //create  facture
        ResponseNewFactureDto responseNewFactureDto = callRemoteFacture(UUID.fromString(transactionModel.getIdEmetteur()),"transaction",1.0,Date.from(Instant.now()));
        message.put("facture_label","Facturation : ");
        message.put("facture_val",responseNewFactureDto.getMessage());
        if(!responseNewFactureDto.isSuccess())
            return new ResponseNewTransactionDto("Error with facture, see mail",null);

        // Everything goes well, save the transaction
        TransactionModel transacModelSaved = transactionRepository.save(transactionModel);
        message.put("transac_label","Transaction Res");
        message.put("transac_val",responseNewFactureDto.getMessage());

        //call mail
        //callRemoteServiceMail("Transaction",message,transactionModel.getIdRecepteur());
        //callRemoteServiceMail("Transaction",message,transactionModel.getIdEmetteur());

        return new ResponseNewTransactionDto("Transaction Saved",new Transaction(transacModelSaved));
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

    private void callRemoteServiceMail(String serviceName, Map<String,String> values, String recipient)
    {
        final RestTemplate restTemplate = new RestTemplate();
        //Email email = new Email();
        //final mailRequestDto mailRequestDto = new mailRequestDto(serviceName, values,recipient);

        //final String response = restTemplate.postForObject(url_mail+url_mail_send, mailRequestDto, String.class);
    }

    private ResponseNewFactureDto callRemoteFacture(UUID id_client, String libelle_frais, double montant, Date date)
    {
        final RestTemplate restTemplate = new RestTemplate();

        final NewFactureDto newFactureDto = new NewFactureDto( id_client, libelle_frais, montant,  date);

        return restTemplate.postForObject(url_facture+url_facture_create, newFactureDto, ResponseNewFactureDto.class);
    }

    private ResponseSecurityRightDto callRemoteSecurity (String id){
        final RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url_securite+url_securite_check+id+url_securite_service, ResponseSecurityRightDto.class);
    }

    private ResponseWithdrawCompteDto callRemoteCompte(String id, BigDecimal amount){
        final RestTemplate restTemplate = new RestTemplate();

        final RequestWithdrawCompteDto requestWithdrawCompteDto = new RequestWithdrawCompteDto(UUID.fromString(id),amount);

        return restTemplate.postForObject(url_compte+url_compte_withdraw, requestWithdrawCompteDto, ResponseWithdrawCompteDto.class);
    }
}