// LoanApplicationRepository.java

package com.copilot.loan_application_service;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoanApplicationRepository extends MongoRepository<LoanApplicationModel, String> {
    Optional<LoanApplicationModel> findByApplicationId(String applicationId);
}