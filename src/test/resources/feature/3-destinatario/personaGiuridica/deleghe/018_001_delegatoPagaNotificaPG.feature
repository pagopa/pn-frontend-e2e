Feature:Il delegato persona giuridica accede ad una delega

  @TestSuite
  @TA_PGdelegatoAccedeNotifica
  @DeleghePG
  @PG

  Scenario: PN-10389 - Il delegato persona giuridica paga una notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
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
    And Aspetta 10 secondi
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona giuridica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega accettata per PG
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche dell impresa
    And La persona giuridica clicca sulla prima notifica restituita
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona giuridica
    And PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And La persona giuridica clicca sulla prima notifica restituita
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona giuridica