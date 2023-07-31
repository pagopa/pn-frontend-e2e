Feature: Mittente genera una notifica tramite destinatario con pec

  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login mittente tramite request method
    Then Home page mittente viene visualizzata correttamente

  @TestSuite
  @test86
  @LoginMittenteRest

  Scenario: Mittente genera una notifica tramite destinatario con pec
    When Nella Home page mittente cliccare sul bottone Gestisci di Piattaforma Notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    #And Nella pagina Piattaforma Notifiche accetta i Cookies
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisicaPec"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisicaPec"
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice fiscale della persona fisica "destinatarioPec"
    And Nella pagina Piattaforma Notifiche inserire la data invio notifica
    And Nella pagina piattaforma Notifiche selezionare lo stato notifica Depositata
    And Cliccare sul bottone Filtra
    And Verifica dello stato della notifica inviata tramite pec come "Depositata"
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica pec "datiNotifica"
    And Cliccare sul bottone Filtra
    And Si verifica che la notifica sia nello stato "Invio in corso"
    And Nella pagina Piattaforma Notifiche si clicca sulla notifica restituita
    And Si verifica sia presente l'indirizzo mail del destinatario "Success.it" nei dettagli della notifica
    And Logout da portale mittente