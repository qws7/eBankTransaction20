package org.cnam.sample.service;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.dto.Request.NewFactureDto;
import org.cnam.sample.dto.Request.RequestGetTransactionDto;
import org.cnam.sample.dto.Request.RequestNewTransactionDto;
import org.cnam.sample.dto.Request.mailRequestDto;
import org.cnam.sample.dto.Response.ResponseGetTransactionDto;
import org.cnam.sample.dto.Response.ResponseNewTransactionDto;
import org.cnam.sample.model.TransactionModel;
import org.cnam.sample.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @PersistenceContext
    private EntityManager em;

    @Value("${application.compte.url}")
    private String url_compte ;

    @Value("${application.mail.url}")
    private String url_mail ;
    @Value("${application.mail.feature.send}")
    private String url_mail_send ;

    @Value("${application.facture.url}")
    private String url_facture ;
    @Value("${application.facture.feature.create}")
    private String url_facture_create ;

    @Value("${application.securite.url}")
    private String url_securite ;
    @Value("${application.monetique.url}")
    private String url_monetique ;

    public TransactionService() {
    }

    public ResponseNewTransactionDto createNewTransaction(RequestNewTransactionDto data){
        String mess = "";
        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setIdEmetteur(  data.getIdEmetteur());
        transactionModel.setIdRecepteur(  data.getIdRecepteur());
        transactionModel.setAmount(  data.getAmount());
        transactionModel.setType(  data.getType());
        transactionModel.setIdType(  data.getIdType());

        //ask Security

        //ask monetary

        //ask ompte 1

        //ask compte 2




        // Everything goes well, save the transaction
        TransactionModel transacModelSaved = transactionRepository.save(transactionModel);
        mess = "Transaction saved";

        //call mail
        Map<String,String> message = new HashMap<String,String>();
        message.put("Result",mess);
        message.put("Recv",transactionModel.getIdRecepteur());
        message.put("Emet",transactionModel.getIdEmetteur());
        message.put("Amount",transactionModel.getAmount().toString());
        callRemoteServiceMail("Transaction",message,transactionModel.getIdRecepteur());
        callRemoteServiceMail("Transaction",message,transactionModel.getIdEmetteur());
        return new ResponseNewTransactionDto(mess,new Transaction(transacModelSaved));
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

    public void callRemoteServiceMail(String serviceName, Map<String,String> values, String recipient)
    {
        final RestTemplate restTemplate = new RestTemplate();

        final mailRequestDto mailRequestDto = new mailRequestDto(serviceName, values,recipient);

        final String quote = restTemplate.postForObject(url_mail+url_mail_send, mailRequestDto, String.class);
    }

    public void callRemoteFacture(long id_client, String libelle_frais, double montant, Date date)
    {
        final RestTemplate restTemplate = new RestTemplate();

        final NewFactureDto newFactureDto = new NewFactureDto( id_client, libelle_frais, montant,  date);

        final String quote = restTemplate.postForObject(url_facture+url_facture_create, newFactureDto, String.class);
    }
}