@US02
  Feature: Bir yönetici (admin) olarak API bağlantısı üzerinden Hub List'e erişebilmek istiyorum.

    Scenario: api/hub/list endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
    dönen status code'un 200 oldugu ve response message bilgisinin "Success" oldugu dogrulanmali

      Given Hub List endpointi icin gerekli olan path parametreleri set edilir.
      Then Hub List endpointine Get request gonderilir.
      Then Donen responsein Status Code'unun 200 ve Response Message bilgisinin Success oldugunu dogrular.