
  Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden yeni bir hub kaydı olusturabilmek istiyorum.

    Scenario: api/hub/add endpoint'ine gecerli authorization bilgileri ve dogru datalar 
    (name,phone,address,current balance,status) iceren bir POST body gönderildiginde dönen 
    status code'in 200 oldugu ve response body'deki message bilgisinin "Hub is added" oldugu dogrulanmali
      
      Given Api "hub/add" path parametreleri set edilir
      Then Name "Esat Fatih", phone "123456789", address "Nigde", current balance "5000000", status 1 bilgileri ile Post request gonderilir.
      Then Then Donen response status kodunun 200 ve message bilgisinin de "Hub is added" oldugu dogrulanmali.