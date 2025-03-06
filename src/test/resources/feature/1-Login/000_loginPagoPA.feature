Feature: Login pagoPA

  @TA_loginMittente
  @TestSuite
  @loginFE_8
  @loginFE
  Scenario: Login pagoPA mittente
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | albino63 |
      | pwd    | test     |
      | comune | Verona   |
    Then Home page mittente viene visualizzata correttamente
    And Click entra su Send Mittente
    And Logout da portale mittente

  @TA_loginpersonaFisica_x
  @TestSuite
  @loginFE_8
  @loginFE
  Scenario: Login pagoPA persona fisica
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Aspetta 10 secondi
    Then Home page persona fisica viene visualizzata correttamente
    And Logout da portale persona fisica

  @TA_loginpersonaGiuridica
  @TestSuite
  @loginFE_8
  @loginFE
  Scenario: PN-9146 - Login pagoPA persona giuridica
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente
    And Click entra su Send Persona Giuridica
    And Logout da portale persona giuridica

