package org.cnam.sample.dto.Response;

import org.cnam.sample.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ResponseNewTransactionDto  implements ResponseDto{
    private List<String> message;
    private Transaction transaction;

    public ResponseNewTransactionDto() {
    }

    public ResponseNewTransactionDto(List<String> messages, Transaction transaction) {
        this.message = messages;
        this.transaction = transaction;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
