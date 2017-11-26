package com.webapp.guide_operator.Service;

import java.util.List;

import com.webapp.guide_operator.Entities.Contract;

public interface ContractService {
	Iterable<Contract> findAll();

    List<Contract> search(String q);

    Contract findOne(int id);

    void save(Contract Contract);

    void delete(int id);
}
