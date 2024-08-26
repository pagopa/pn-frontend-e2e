Feature: Il mittente inserisce i dati di una nuova notifica, dopo l'inserzione decide di tornare indietro per poi creare una nuova notifica

  @TA_inviaNuovaNotificaConCampiVuoti
  @TestSuite
  @mittente
  @invioNotifiche

  Scenario: PN-8895 - Il mittente inserisce i dati non corretti nella sezione informazioni preliminari
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Aspetta 10 secondi
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    #And Nella section Informazioni preliminari inserire i dati della notifica "datiNotifica" senza pagamento
    And Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | gruppo            | test-TA-FE-TEST    |
      | codiceTassonomico | 123456A            |
      | modalitaInvio     | A/R                |
    And Nella section cliccare sul tasto indietro
    And Nella section si visualizza il popup vuoi uscire
    And Nella section cliccare sul tasto esci
    Then Si visualizza correttamente la pagina Piattaforma Notifiche
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section si visualizza correttamente i campi vuoti
    And Logout da portale mittente