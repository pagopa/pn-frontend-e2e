Feature: PG visualizza il dettaglio di una notifica con documenti allegati

  @TestSuite
  @TA_PGDettaglioNotificheConDocumenti
  @PG
  @visualizzazioneNotifichePG

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - PG scarica modello F24
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "UXUN-JLHY-GZRH-202405-Q-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si controlla sia presente l'avviso PagoPa PG
    And Si controlla sia presente il modello F24 PG
    And Si clicca il modello F24 PG
    Then Si torna alla pagina precedente
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - PG scarica l'avviso PagoPa
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "LDPX-ATUQ-YTHR-202405-J-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si clicca sul radio bottone di pagamento
    And Si controlla sia presente l'avviso PagoPa PG
    And Si clicca l'avviso PagoPa PG
    And Aspetta 3 secondi
    Then Si torna alla pagina precedente
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - PG visualizza dettaglio notifica con attestazione opponibile a terzi notifica presa in carico
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "UXUN-JLHY-GZRH-202405-Q-1"
    And Si clicca la notifica ricercata
    And Si controlla sia presente il modello F24 PG
    Then Si controlla sia presente attestazione opponibile a terzi notifica presa in carico
    And Logout da portale mittente

  @CheckNotificaConDocumentiAllegatiPG
  Scenario: [TA-FE VISUALIZZAZIONE DETTAGLI DI NOTIFICA] - PG visualizzazione notifica da pagare annullata
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche si effettua la ricerca per codice IUN "ADHM-RLUJ-MVGQ-202405-T-1"
    And Si clicca la notifica ricercata
    Then Si controlla che stato di notifica Annullata
    And Si controlla sia presente l'avviso PagoPa
    Then Si controlla sia presente il modello F24
    And Logout da portale mittente

