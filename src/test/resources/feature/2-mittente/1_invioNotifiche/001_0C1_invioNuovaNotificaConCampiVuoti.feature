Feature: Il mittente inserisce i dati di una nuova notifica, dopo l'inserzione decide di tornare indietro per poi creare una nuova notifica

  Background: login pagoPA mittente
    Given Login Page mittente "mittente" viene visualizzata
    When Login con mittente tramite token exchange
    Then Si visualizza correttamente la pagina Piattaforma Notifiche

  @TA_inviaNuovaNotificaConCampiVuoti
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario:Il mittente inserisce i dati non corretti nella sezione informazioni preliminari
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Nella section cliccare sul tasto indietro
    And Nella section si visualizza il popup vuoi uscire
    And Nella section cliccare sul tasto esci
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section si visualizza correttamente i campi vuoti
    And Logout da portale mittente