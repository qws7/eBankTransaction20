package org.cnam.sample.dto.Response;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResponseGetTransactionDto implements ResponseDto{
    private UUID idCompte;
    private List<Transaction> allTransac;

    public ResponseGetTransactionDto() {
    }

    public ResponseGetTransactionDto(UUID idCompte) {
        this.allTransac = new ArrayList<>();
    }

    public ResponseGetTransactionDto(UUID idCompte, List<Transaction> allTransac) {
        this.idCompte = idCompte;
        this.allTransac = new ArrayList<>();
    }

    public UUID getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(UUID idCompte) {
        this.idCompte = idCompte;
    }

    public List<Transaction> getAllTransac() {
        return allTransac;
    }

    public void setAllTransac(List<Transaction> allTransac) {
        this.allTransac = allTransac;
    }

    public void setAllTransacModel(List<TransactionModel> allTransac) {
        this.allTransac = new ArrayList<>();

        while(allTransac.iterator().hasNext()){
            this.allTransac.add(new Transaction(allTransac.iterator().next()));
        }
    }

    public void addTransac(Transaction transaction){
        this.allTransac.add(transaction);
    }
}
