
  Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden id'si
    verilen kullanicinin Hub bilgilerine erisebilmek istiyorum.


    Scenario: api/hub/id endpoint'ine gecerli bir id bilgisi eklenerek ve gecerli
    authorization bilgisi ile bir GET request gönderildiginde dönen status code'un 200 oldugu doğrulanmali.

      Given Api " hub /1" path parametreleri set edilir