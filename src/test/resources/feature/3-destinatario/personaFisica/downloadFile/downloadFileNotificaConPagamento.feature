Feature: Il destinatario accede ad una notifica non ancora pagata e scarica i file

  @downloadFileNotificaConPagamento
  Scenario: [TA-FE DOWNLOAD FILE NOTIFICA CON PAGAMENTO] - Il destinatario accede ad una notifica con pagamento e apre il modello F24, successivamente apre anche l'avviso PagoPA
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina piattaforma notifiche destinatario si effettua la ricerca per codice IUN "JVDU-KLJG-RPZV-202405-X-1"
    And Si clicca la notifica ricercata
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    Then Si clicca sul bottone scarica F24
    And Aspetta 10 secondi
    #And Si controlla di aver aperto il file F24
    #And Aspetta 5 secondi
    And Si torna alla pagina precedente
    And Aspetta 10 secondi
    Then Si clicca sul bottone scarica avviso PagoPA
    And Aspetta 10 secondi
    And Si torna alla pagina precedente
    And Logout da portale persona fisica
