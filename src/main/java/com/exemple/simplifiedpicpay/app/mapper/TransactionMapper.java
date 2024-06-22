package com.exemple.simplifiedpicpay.app.mapper;

import com.exemple.simplifiedpicpay.core.domain.TransactionData;
import com.exemple.simplifiedpicpay.core.domain.dto.TransactionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TransactionMapper {
    TransactionData map(TransactionDTO transactionDTO);
}
