package com.luxoft.springdb.lab3.service;

import com.luxoft.springdb.lab3.dao.CountryDao;
import com.luxoft.springdb.lab3.dao.CountryNotFoundException;
import com.luxoft.springdb.lab3.model.Country;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public class ProgrammaticTransactionCountryServiceImpl implements ProgrammaticTransactionCountryService {
    private final TransactionTemplate transactionTemplate;
    private CountryDao dao;

    public ProgrammaticTransactionCountryServiceImpl(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public Country getCountryByName(final String name) {
        transactionTemplate.setIsolationLevel(Isolation.REPEATABLE_READ.value());
        return transactionTemplate.execute(status -> {
            try {
                return dao.getCountryByName(name);
            } catch (CountryNotFoundException e) {

            }
            return null;
        });
    }

    public Country getCountryByCodeName(final String codeName, final Propagation propagation) {
        return transactionTemplate.execute(status -> getCountryByCodeNameInTransaction(codeName, propagation));
    }

    private Country getCountryByCodeNameInTransaction(final String codeName, final Propagation propagation) {

        return transactionTemplate.execute(status -> {
            TransactionLog.append("Method inside transaction, propagation = " + propagation.toString() + "\n");
            return dao.getCountryByCodeName(codeName);
        });
    }

    public List<Country> getAllCountries() {
        return dao.getCountryList();
    }

    public CountryDao getDao() {
        return dao;
    }

    public void setDao(CountryDao dao) {
        this.dao = dao;
    }
}
