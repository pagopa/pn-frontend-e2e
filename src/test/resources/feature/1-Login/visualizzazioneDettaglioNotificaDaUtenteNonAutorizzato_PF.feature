Feature: Ricerca notifica per periodo temporale persona fisica

  @TestSuite
  @TA_PFVisualizzaNotificaUtenteNonAutorizzato
  @GestioneErrori
  Scenario: [PN-14926-PN_DELIVERY_NOTIFICATIONNOTFOUND_PF] - Errore per notifiche non accessibili all’utente
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica si accede alla notifica con codice IUN "XVRJ-HRGW-AQZN-202505-N-1"
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Verifica Pop-up Toast Errore "Non puoi leggere questa notifica"
    And Verifica Messaggio Toast Errore "Può essere letta solo dagli utenti censiti dall’impresa destinataria o da un suo delegato"



