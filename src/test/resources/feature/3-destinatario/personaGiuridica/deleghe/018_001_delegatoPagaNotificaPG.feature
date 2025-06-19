Feature:Il delegato persona giuridica accede ad una delega

  @TestSuite
  @TA_PGdelegatoPagaNotifica
  @DeleghePG
  @PG
  @deleghe2
  @DeleghePFPG1
  @NRT_Blocco_1
  Scenario: PN-10389 - Il delegato persona giuridica paga una notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU -> PN-10389 |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo         | VIA ROMA 20        |
      | dettagliIndirizzo | Scala b            |
      | codicePostale     | 20147              |
      | comune            | Milano             |
      | dettagliComune    | Milano             |
      | provincia         | MI                 |
      | stato             | Italia             |
      | nomeCognome       | Convivio Spa |
      | codiceFiscale     | 27957814470   |
      | tipoDestinatario  | PG                 |
      | domicilioDigitale | test@test.com      |
      | avvisoPagoPa      | 1                  |
      | F24               | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 15 secondi
    And La persona giuridica clicca sulla prima notifica restituita
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe dell impresa
    # Seconda PG per TA non disponibile, si crea una delega per PF
    #And Si controlla che non sia presente una delega con stesso nome persona giuridica "Le Epistolae srl"
    #And Nella sezione Deleghe si verifica sia presente una delega accettata per PG
    And Si controlla che non sia presente una delega con stesso nome
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
    And Nella sezione Deleghe si crea una delega accettata per PG

    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella sezione Deleghe si accetta la delega accettata per PG

#    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And  Click Notifiche
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sulle notifiche di "(Convivio Spa)"
    And Si controlla la pagina delle notifiche delegati di "Convivio Spa"
    And Aspetta 10 secondi
    And Si seleziona la notifica
    And Si verifica che visualizzato lo stato Pagato