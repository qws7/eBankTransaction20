package org.cnam.sample.service;

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

    /*
            public Transac createNewTransac(String data){
                TransacModel transacModel = new TransacModel(data);
                TransacModel transacModelSaved = transacRepository.save(transacModel);
                return new Transac(transacModelSaved.getId(),transacModelSaved.getData());

            }

            public Transac getTransac(long id) {
                TransacModel transacModelFound = transacRepository.getOne(id);
                return new Transac(transacModelFound.getId(),transacModelFound.getData());
            }

            public String createNewTransacExternal(String data){
                RestTemplate restTemplate = new RestTemplate();
                NewTransacDto myRequest = new NewTransacDto(data);

                TransacDto myResponse = restTemplate.postForObject(clientApplication+ createNewClientPath, myRequest, TransacDto.class);
                LOGGER.info(myResponse.toString());
                return myResponse.data;
            }*/
}