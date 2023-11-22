Feature: 
  
  Scenario: 
    
    Given Api "hub/add" Path Parametreleri set edilir
    Then Hub Add Api icin name "Omer", phone "123456789", address "Ankara, Turkey" bilgileriyle Post request gonderilir
    Then Donen Response'in status code'unun 200 oldugu dogrulanir
    Then Donen Response Body'sinin success degerinin "true" , message bilgisinin de "Hub is added" oldugu dogrulanir