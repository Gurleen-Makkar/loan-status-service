# Simple Loan Application Microservice

## Overview:

Participants will develop a microservice that handles basic loan application submissions and status checks for a financial institution. The microservice will accept loan applications and provide a status update.

## Requirements:

### API Endpoints:

- Submit Loan Application: Accepts loan applications from clients.
- Get Application Status: Returns the status of a submitted loan application.

### Entities:

Loan Application:

- applicationId (UUID)
- applicantName (String)
- loanAmount (Double)
- applicationStatus (Enum: SUBMITTED, REJECTED)

### Business Rules for Rejection:

- Loan amount should not exceed Rs. 50,000.

### Flow Diagram:

- Submit Loan Application
- Client submits application data.
- Microservice generates a unique applicationId and sets applicationStatus to SUBMITTED.
- If loanAmount exceeds Rs. 50,000, status is updated to REJECTED.
- Get Application Status
- Clients can query the status using applicationId.

### API Specifications:

#### Submit Loan Application:

Endpoint: POST /api/loan-applications

Request Body:
`{ "applicantName": "John Doe", "loanAmount": 20000 }`

Response:
`{ "applicationId": "uuid", "applicationStatus": "SUBMITTED" }`

On Error:
`{ "applicationId": "uuid", "applicationStatus": "REJECTED", "reason": "Loan amount exceeds the limit" }`

#### Get Application Status:

Endpoint: GET /api/loan-applications/{applicationId}/status

Response:
`{ "applicationId": "uuid", "applicationStatus": "SUBMITTED" }`
