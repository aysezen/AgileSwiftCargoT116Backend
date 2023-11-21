Feature:

  Scenario:

    Given Api "hub/list" Path Parametreleri set edilir
    Then Get request gonderilir
    Then Donen Response'in status code'unun 200 oldugu dogrulanir
    Then Donen Response Body'sinin success degerinin "true" , message bilgisinin de "Success" oldugu dogrulanir