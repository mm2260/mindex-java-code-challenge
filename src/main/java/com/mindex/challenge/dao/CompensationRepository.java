package com.mindex.challenge.dao;

/*
@Author: Mindex
@Author: Mohammed Mehboob (mm2260@rit.edu)
 */


import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    Compensation findByEmployee(Employee employee);
}
