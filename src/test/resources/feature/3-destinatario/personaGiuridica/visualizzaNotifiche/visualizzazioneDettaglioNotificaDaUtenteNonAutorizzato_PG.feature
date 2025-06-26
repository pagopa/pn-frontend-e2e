Feature: Visualizzazione dettaglio notifica persona giuridica

  @TestSuite
  @TA_PGVisualizzaNotificaUtenteNonAutorizzato
  @GestioneErrori
    @NRT_Blocco_3_GRUPPO_AWS
  @VisualizzaNotifiche
  Scenario: [PN-14926-PN_DELIVERY_USER_ID_NOT_RECIPIENT_OR_DELEGATOR_PG] - Errore per notifiche non accessibili all’utente
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica si accede alla notifica con codice IUN "XVRJ-HRGW-AQZN-202505-N-1"
    And Verifica Pop-up toast di errore "Errore"
    And Verifica Messaggio toast di errore "Errore non previsto"
    And Verifica Codice toast di errore "PN_DELIVERY_USER_ID_NOT_RECIPIENT_OR_DELEGATOR"
    And Copia TraceID toast di errore
    And Si chiude toast di errore
    And Refresh pagina
    And Verifica Codice toast di errore "PN_DELIVERY_USER_ID_NOT_RECIPIENT_OR_DELEGATOR"
