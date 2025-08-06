Feature:La persona fisica aggiunge una nuova delega

  @TestSuite
  @TA_PF_aggiuntadelega_nome_cognome_errato
  @DeleghePF
  @NRT_VALIDATION
  @deleghe1
  Scenario:PN- La persona giuridica aggiunge una nuova delega con nome e cogmome errati rispetto al CF
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe

    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza correttamente la pagina nuova delega

    And Inserire dati errati Nella sezione Le Tue Deleghe
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Verona    |
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare Nome e Cognome
#    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Attesa 1 secondi
    And Nella sezione Le Tue Deleghe verifica esistenza nome e cognomi Errati "PF"
    And Nella sezione Deleghe si visualizza la delega in stato di attesa di conferma

