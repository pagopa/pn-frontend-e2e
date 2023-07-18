Feature: Login pagoPA

  @loginMittente
  @TestSuite

  Scenario: Login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Logout da portale mittente


  @loginpersonaFisica
  @TestSuite
  Scenario: Login pagoPA persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login con persona fisica "personaFisica"
    Then Home page persona fisica viene visualizzata correttamente
    And Logout da portale persona fisica