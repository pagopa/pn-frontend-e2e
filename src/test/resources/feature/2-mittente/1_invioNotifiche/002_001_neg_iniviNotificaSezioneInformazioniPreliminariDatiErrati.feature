Feature: Il mittente inserisce i dati non coretti nella sezione informazioni preliminari

  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TA_inserimentoDatiErratiInfoPreliminari
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario:Il mittente inserisce i dati non corretti nella sezione informazioni preliminari
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section si prova ad cliccare sul tasto continua senza aver inserito nessun dato
    And Nella section Informazioni preliminari inserire i dati della notifica sbagliati "datiNotificaErrore" senza pagamento
    And Nella section Informazioni preliminari si visualizza un messaggio di errore
    And Nella section cliccare sul tasto indietro
    And Nella section si visualizza il popup vuoi uscire
    And Nella section cliccare sul tasto esci
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section si prova ad cliccare sul tasto continua senza aver inserito nessun dato
    And Logout da portale mittente