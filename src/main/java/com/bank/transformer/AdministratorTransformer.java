package com.bank.transformer;

import com.bank.domain.operators.AdministratorEntity;
import com.bank.dto.Administrator;
import org.springframework.stereotype.Component;

@Component
public class AdministratorTransformer implements Transformer<AdministratorEntity, Administrator> {
    @Override
    public Administrator toDto(AdministratorEntity administratorEntity) {
        return null;
    }

    @Override
    public AdministratorEntity toEntity(Administrator administrator) {
        return null;
    }
}