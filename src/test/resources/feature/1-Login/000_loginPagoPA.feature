Feature: Login pagoPA

  @TA_loginMittente
  @TestSuite
  @loginFE

  Scenario: Login pagoPA mittente
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.dev.notifichedigitali.it |
    When Login con mittente
      | user   | ggiorgi |
      | pwd    | test    |
      | comune | Viggiu  |
    Then Home page mittente viene visualizzata correttamente
    And Logout da portale mittente

  @TA_loginpersonaFisica
  @TestSuite
  @loginFE
  Scenario: Login pagoPA persona fisica
    Given Login Page persona fisica viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    Then Home page persona fisica viene visualizzata correttamente
    And Logout da portale persona fisica

  @TA_loginpersonaGiuridica
  @TestSuite
  @loginFE
  Scenario: PN-9146 - Login pagoPA persona giuridica
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente
    And Logout da portale persona giuridica
