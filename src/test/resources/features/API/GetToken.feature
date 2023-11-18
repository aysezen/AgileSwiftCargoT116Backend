Feature: Token Create eden Api

  Scenario:

    Given Token create etmek icin gerekli olan pathparametresi set edilir.
    Then getToken Post methodu icin gerekli olan ReqBody olusturulur
    Then getToken icin gerekli olan Request gonderilir
    Then GetToken requesti icin donen Response bodysinden Token degeri alinir
