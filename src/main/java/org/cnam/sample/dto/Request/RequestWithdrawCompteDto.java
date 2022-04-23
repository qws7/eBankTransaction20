package org.cnam.sample.dto.Request;

import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.UUID;

public class RequestWithdrawCompteDto  implements RequestDto{
    public UUID getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private UUID idAccount;
    private BigDecimal amount;
    public RequestWithdrawCompteDto(UUID idAccount, BigDecimal amount)
    {
        this.idAccount = idAccount;
        this.amount = amount;
    }
}
