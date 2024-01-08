Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TA_inserimentoDatiInfoPreliminari
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario: PN-9127 - Il mittente inserisce i dati nella sezione informazioni preliminari
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Logout da portale mittente