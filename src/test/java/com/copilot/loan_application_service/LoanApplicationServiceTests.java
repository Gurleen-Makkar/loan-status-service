// LoanApplicationServiceTests.java

package com.copilot.loan_application_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoanApplicationServiceTests {

	@Mock
	private LoanApplicationRepository repository;

	@InjectMocks
	private LoanApplicationService service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSubmitApplication() {
		String applicantName = "John Doe";
		double loanAmount = 40000;
		LoanApplicationModel application = new LoanApplicationModel();
		application.setApplicantName(applicantName);
		application.setLoanAmount(loanAmount);
		application.setApplicationStatus(LoanApplicationModel.ApplicationStatus.SUBMITTED);

		when(repository.save(any(LoanApplicationModel.class))).thenReturn(application);

		LoanApplicationModel result = service.submitApplication(applicantName, loanAmount);

		assertEquals(applicantName, result.getApplicantName());
		assertEquals(loanAmount, result.getLoanAmount());
		assertEquals(LoanApplicationModel.ApplicationStatus.SUBMITTED, result.getApplicationStatus());
	}

	@Test
	public void testGetApplicationStatus() {
		String applicationId = "123";
		LoanApplicationModel application = new LoanApplicationModel();
		application.setApplicationId(applicationId);
		application.setApplicationStatus(LoanApplicationModel.ApplicationStatus.SUBMITTED);

		when(repository.findByApplicationId(applicationId)).thenReturn(Optional.of(application));

		LoanApplicationModel.ApplicationStatus result = service.getApplicationStatus(applicationId);

		assertEquals(LoanApplicationModel.ApplicationStatus.SUBMITTED, result);
	}

	@Test
	public void testGetApplicationStatusNotFound() {
		String applicationId = "123";

		when(repository.findByApplicationId(applicationId)).thenReturn(Optional.empty());

		assertThrows(NotFoundException.class, () -> service.getApplicationStatus(applicationId));
	}

	@Test
	public void testSubmitApplicationRejected() {
		String applicantName = "John Doe";
		double loanAmount = 60000;
		LoanApplicationModel application = new LoanApplicationModel();
		application.setApplicantName(applicantName);
		application.setLoanAmount(loanAmount);
		application.setApplicationStatus(LoanApplicationModel.ApplicationStatus.REJECTED);

		when(repository.save(any(LoanApplicationModel.class))).thenReturn(application);

		LoanApplicationModel result = service.submitApplication(applicantName, loanAmount);

		assertEquals(applicantName, result.getApplicantName());
		assertEquals(loanAmount, result.getLoanAmount());
		assertEquals(LoanApplicationModel.ApplicationStatus.REJECTED, result.getApplicationStatus());
	}

	// Add your new test cases here

}