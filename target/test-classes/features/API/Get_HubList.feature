@US01
Feature:As an administrator, I want to access the Purpose List through an API connection.

  Scenario: To validate that the status code is 200 and the response message is "Success"
  when sending a GET request to the api/visitorsPurposeList endpoint with valid authorization
  credentials, you would typically need to use a programming language or a tool to make the
  API request and perform the validation.

    Given Admin sets the parameters in the path "hub/list".
    Then send Get request for visitorsPurposeList
    Then verifies that the return response for the visitorsPurposeList Api succesfull


