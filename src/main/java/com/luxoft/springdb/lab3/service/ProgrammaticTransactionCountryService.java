package com.luxoft.springdb.lab3.service;

import com.luxoft.springdb.lab3.dao.CountryDao;
import com.luxoft.springdb.lab3.dao.CountryNotFoundException;
import com.luxoft.springdb.lab3.model.Country;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

public interface ProgrammaticTransactionCountryService {

    Country getCountryByName(String name) throws CountryNotFoundException;

    List<Country> getAllCountries();

    Country getCountryByCodeName(String codeName, Propagation propagation);

    void setDao(CountryDao dao);

    CountryDao getDao();

}
