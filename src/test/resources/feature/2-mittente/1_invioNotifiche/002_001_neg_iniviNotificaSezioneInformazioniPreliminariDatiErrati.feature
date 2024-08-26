Feature: Il mittente inserisce i dati non coretti nella sezione informazioni preliminari

  @TA_inserimentoDatiErratiInfoPreliminari
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario:Il mittente inserisce i dati non corretti nella sezione informazioni preliminari
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section si prova ad cliccare sul tasto continua senza aver inserito nessun dato
   # And Nella section Informazioni preliminari inserire i dati della notifica sbagliati "datiNotificaErrore" senza pagamento
    And Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU |
    And Nella section Informazioni preliminari si visualizza un messaggio di errore
    And Nella section cliccare sul tasto indietro
    And Nella section si visualizza il popup vuoi uscire
    And Nella section cliccare sul tasto esci
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Logout da portale mittente
