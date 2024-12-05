Feature: Login pagoPA



  @TA_loginpersonaGiuridica
  @TestSuite
  @loginFE_1
  @loginFE
  Scenario: PN-9146 - Login pagoPA persona giuridica 7 parallel
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |




  @TA_loginpersonaGiuridica
  @TestSuite
  @loginFE_1
  @deleghe1
  @loginFE
  Scenario: PN-9146 - Login pagoPA persona giuridica 12 seq
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |