Feature: Login pagoPA

  @TestSuite
  @loginMittente
  Scenario: Login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente
    And Logout da portale mittente

  @TestSuite
  @loginDestinatario
  Scenario: Login pagoPA destinatario
    Given Login Page destinatario "destinatario" viene visualizzata
    When Login con destinatario "destinatario"
    Then Home page destinatario viene visualizzata correttamente
    And Logout da portale destinatario