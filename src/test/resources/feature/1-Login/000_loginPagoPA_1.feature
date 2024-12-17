Feature: Login pagoPA



  @TA_loginpersonaGiuridica
  @TestSuite_x
  @loginFE_3
  Scenario: PN-9146 - Login pagoPA persona giuridica 7 parallel
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |




  @TA_loginpersonaGiuridica
  @TestSuite_x
  @loginFE_3
  @deleghe1
  Scenario: PN-9146 - Login pagoPA persona giuridica 12 seq
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |

  @TA_loginpersonaGiuridica
  @TestSuite_x
  @loginFE_3
  Scenario: PN-9146 - Login pagoPA persona giuridica 10 parallel
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |


  @TA_loginpersonaGiuridica
  @TestSuite_x
  @loginFE_3
  Scenario: PN-9146 - Login pagoPA persona giuridica 11 parallel
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |


  @TA_loginpersonaGiuridica
  @TestSuite_x
  @loginFE_3
  Scenario: PN-9146 - Login pagoPA persona giuridica 12 parallel
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |