
  Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden id'si
    verilen kullanicinin Hub bilgilerine erisebilmek istiyorum.


    Scenario: hub/id endpoint'ine gecerli bir id bilgisi eklenerek ve gecerli
    authorization bilgisi ile bir GET request gönderildiginde dönen status code'un 200 oldugu doğrulanmali.

      Given Api "hub/1" path parametreleri set edilir
      Then Get request gonderilir.
      Then Donen response status kodunun 200 oldugu dogrulanmali

    Scenario: hub/id  endpoint'ine  gecersiz (id) iceren bir GET gönderildiğinde statusCode=400,
    message bilgisinin "there is no hub with this id", endpoint´e id eklenmeden bir GET gönderildiginde
    "No id." oldugu, gecersiz authorization bilgileri ile bir GET gonderildiginde ise statusCode=401,
    message bilgisinin "Unauthenticated." oldugu dogrulanmali

      Given Api "hub/1" path parametreleri set edilir
      Then invalid credentials ile Get request gonderilir
      Then Donen response status kodunun 401 ve message bilgisinin de Unauthenticated. oldugu dogrulanmali