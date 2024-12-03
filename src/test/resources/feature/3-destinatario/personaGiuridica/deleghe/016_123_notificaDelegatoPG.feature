Feature:Il delegato persona giuridica accede ad una delega

  @Parallel
  @TA_PGdelegatoAccedeNotifica
  @DeleghePG
  @PG

  Scenario: PN-9177 - Il delegato persona giuridica accede ad una notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Creo in background una delega per persona giuridica
      | accessoCome    | delegante     |
      | fiscalCode     | 27957814470   |
      | companyName    | Convivio Spa  |
      | displayName    | Convivio Spa  |
      | person         | false         |
    And Si accetta la delega senza gruppo
    And Si controlla che la delega PG ha lo stato Attiva "Convivio Spa"
    And Nella Pagina Notifiche destinatario si clicca su notifiche delegate
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | at        | Presso     |
      | indirizzo        | VIA ROMA     |
      | codicePostale    | 20147        |
      | comune           | Milano       |
      | dettagliComune   | Milano       |
      | provincia        | MI           |
      | stato            | Italia       |
      | nomeCognome      | Convivio Spa |
      | codiceFiscale    | 27957814470  |
      | tipoDestinatario | PG           |
      | avvisoPagoPa     | 2            |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 5 secondi
    And Si seleziona la notifica destinatario
    And Logout da portale persona giuridica