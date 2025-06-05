Feature: Ricerca notifica per periodo temporale persona giuridica

  @TestSuite
  @TA_PGVisualizzaNotificaUtenteNonAutorizzato
  @GestioneErrori
  Scenario: [PN-14926-PN_DELIVERY_NOTIFICATIONNOTFOUND_PG] - Errore per notifiche non accessibili all’utente
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica si accede alla notifica con codice IUN "XVRJ-HRGW-AQZN-202505-N-1"
    Then Si visualizza correttamente la section Dettaglio Notifica persona giuridica delegato
    And Verifica Pop-up Toast Errore "Non puoi leggere questa notifica"
    And Verifica Messaggio Toast Errore "Può essere letta solo dagli utenti censiti dall’impresa destinataria o da un suo delegato"



