Feature: invio notifica con lo stesso codice fiscale

  Background: Login mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @test82
  @LoginMittenteRest

    Scenario: il mittente invia la notifica con lo stesso codice fiscale
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da destinatario "destinatario"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati del destinatario "destinatario"
    And Nella section Destinatario cliccare su Aggiungi destinatario
    And Nella section Destinatario si inserisce lo stesso destinatario di prima "destinatario"
    Then Si visualizza correttamente l errore di stesso codice fiscale
    And Logout da portale mittente