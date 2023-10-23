@US01
Feature:As an administrator, I want to access the Purpose List through an API connection.

  Scenario: To validate that the status code is 200 and the response message is "Success"
  when sending a GET request to the api/visitorsPurposeList endpoint with valid authorization
  credentials, you would typically need to use a programming language or a tool to make the
  API request and perform the validation.

    Given Admin sets the parameters in the path "api/visitorsPurposeList".
    Then send Get request for visitorsPurposeList
    Then verifies that the return response for the visitorsPurposeList Api succesfull

  Scenario: When an invalid authorization information is sent with a GET request to
  the api/visitorsPurposeList endpoint, the expected status code is 403, and the
  response message should be "failed."

    Given Admin sets the parameters in the path "api/visitorsPurposeList".
    Then send Get Request with invalid credentials to visitorsPurposeList endpoint
    Then verifies that the return response for the visitorsPurposeList Api is not successfull


  Scenario: The content of the lists in the response body should be validated to
  contain data with ID "2," where the visitors_purpose is "purpose update"
  and created_at is "2023-01-18 06:07:12"

    Given Admin sets the parameters in the path "api/visitorsPurposeList".
    Then send Get request for visitorsPurposeList
    Then Verifies in the Response body with id "2" , visitors_purpose "purpose update", description "", created_at "2023-01-18 06:07:12",


