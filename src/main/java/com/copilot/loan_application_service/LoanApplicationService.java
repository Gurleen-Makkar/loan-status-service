package com.copilot.loan_application_service;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.copilot.loan_application_service.LoanApplicationModel.ApplicationStatus;

@Service
public class LoanApplicationService {
    @Autowired
    private LoanApplicationRepository repository;

    public LoanApplicationModel submitApplication(String applicantName, double loanAmount) {
        LoanApplicationModel application = new LoanApplicationModel();
        application.setApplicationId(UUID.randomUUID().toString());
        application.setApplicantName(applicantName);
        application.setLoanAmount(loanAmount);
        application.setApplicationStatus(loanAmount > 100000 ? ApplicationStatus.REJECTED : ApplicationStatus.SUBMITTED);
        return repository.save(application);
    }

    public ApplicationStatus getApplicationStatus(String applicationId) {
		System.out.println("Get Application is started");
        return repository.findByApplicationId(applicationId)
            .map(LoanApplicationModel::getApplicationStatus)
            .orElseThrow(() -> new NotFoundException("Application not found"));
    }

}