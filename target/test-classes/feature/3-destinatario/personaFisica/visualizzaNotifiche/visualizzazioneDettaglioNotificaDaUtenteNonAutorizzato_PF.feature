Feature: Visualizzazione dettaglio notifica persona fisica

  @TA_PFVisualizzaNotificaDaUtenteNonAutorizzato
  @GestioneErrori
  @NRT_Blocco_2
  Scenario: [PN-14926-PN_DELIVERY_USER_ID_NOT_RECIPIENT_OR_DELEGATOR_PF] - Errore per notifiche non accessibili all’utente
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica si accede alla notifica con codice IUN "XVRJ-HRGW-AQZN-202505-N-1"
    And Attesa 1 secondi
    And Verifica Pop-up toast di errore "errore"
    And Verifica Messaggio toast di errore "informazioni errore"
    And Verifica Codice toast di errore "PN_DELIVERY_USER_ID_NOT_RECIPIENT_OR_DELEGATOR"
    And Copia TraceID toast di errore
    And Si chiude toast di errore
    And Refresh pagina
    And Attesa 1 secondi
    And Verifica Codice toast di errore "PN_DELIVERY_USER_ID_NOT_RECIPIENT_OR_DELEGATOR"
