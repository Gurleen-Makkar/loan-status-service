

package com.copilot.loan_application_service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class LoanApplicationController {
    @Autowired
    private LoanApplicationService service;

    @PostMapping("/loan-applications")
    public ResponseEntity<LoanApplicationModel> submitApplication(@RequestBody LoanApplicationModel application) {
        try {
            return ResponseEntity.ok(service.submitApplication(application.getApplicantName(), application.getLoanAmount()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/loan-applications/{applicationId}/status")
    public ResponseEntity<LoanApplicationModel.ApplicationStatus> getApplicationStatus(@PathVariable String applicationId) {
        try {
            return ResponseEntity.ok(service.getApplicationStatus(applicationId));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
