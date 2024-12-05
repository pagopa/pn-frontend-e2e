Feature: Login pagoPA



  @TA_loginpersonaGiuridica
  @TestSuite
  @loginFE_1
  Scenario: PN-9146 - Login pagoPA persona giuridica 20
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |




  @TA_loginpersonaGiuridica
  @TestSuite
  @loginFE_1
  @deleghe1
  Scenario: PN-9146 - Login pagoPA persona giuridica 21
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |