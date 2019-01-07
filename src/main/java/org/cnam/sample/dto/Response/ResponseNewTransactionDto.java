package org.cnam.sample.dto.Response;

import org.cnam.sample.domain.Transaction;

import java.math.BigDecimal;
import java.util.UUID;

public class ResponseNewTransactionDto  implements ResponseDto{
    private String message;
    private Transaction transaction;

    public ResponseNewTransactionDto() {
    }

    public ResponseNewTransactionDto(String message, Transaction transaction) {
        this.message = message;
        this.transaction = transaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
