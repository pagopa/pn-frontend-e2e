Feature: Login pagoPA

  @loginMittente
  @TestSuite

  Scenario: Login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Logout da portale mittente senza entrare su notifiche

  @loginpersonaFisica
  @TestSuite
  Scenario: Login pagoPA persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login con persona fisica "personaFisica"
    Then Home page persona fisica viene visualizzata correttamente
    And Logout da portale persona fisica

  @loginpersonaGiuridica
  @TestSuite
  Scenario: Login pagoPA persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login con persona giuridica "personaGiuridica"
    Then Home page persona giuridica viene visualizzata correttamente
    And Logout da portale persona giuridica
