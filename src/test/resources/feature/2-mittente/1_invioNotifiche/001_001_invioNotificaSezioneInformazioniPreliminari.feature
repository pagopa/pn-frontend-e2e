Feature: Il mittente inserisce i dati nella sezione informazioni preliminari

  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
   When Login con mittente "mittente"
    Then Home page mittente viene visualizzata correttamente

  @fase2Test1
  @TestSuite

  Scenario:Il mittente inserisce i dati nella sezione informazioni preliminari
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    Then Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Logout da portale mittente