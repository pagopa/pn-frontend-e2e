Feature: La persona giuridica aggiunge una nuova delega

  @TestSuite
  @TA_PG_aggiuntadelega_nome_cognome_errato
  @DeleghePG
  @PG
  @deleghe2
  @DeleghePFPG
  @NRT_Blocco_1
  Scenario: PN- La persona giuridica aggiunge una nuova delega con nome e cogmome errati rispetto al CF
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega

    And Inserire dati errati Nella sezione Le Tue Deleghe
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Verona    |
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare Nome e Cognome
#    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella sezione Le Tue Deleghe verifica esistenza nome e cognomi Errati "PG"
    And Nella sezione Delegati dall impresa si visualizza la delega in stato di attesa di conferma

