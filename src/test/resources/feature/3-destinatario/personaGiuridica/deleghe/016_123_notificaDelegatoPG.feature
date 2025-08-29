Feature:Il delegato persona giuridica accede ad una delega

  @Parallel
  @TA_PGdelegatoAccedeNotifica
  @DeleghePG_1
  @PG
  @DeleghePFPG
  @NRT_PN13213
  @deleghe2
  Scenario: PN-9177 - Il delegato persona giuridica accede ad una notifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Deleghe
    And Si visualizza correttamente la pagina Deleghe sezione Deleghe a Carico dell impresa
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Nella sezione Aggiungi Delega persona giuridica inserire i dati
      | accessoCome    | delegante         |
      | ragioneSociale | Convivio Spa  |
      | codiceFiscale  | 27957814470  |
      | ente           | Comune di Palermo |
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And Nella pagina Deleghe si clicca su Deleghe a carico dell impresa
    And Si accetta la delega senza gruppo
    And Si controlla che la delega PG ha lo stato Attiva "Convivio Spa"
    And Nella Pagina Notifiche destinatario si clicca su notifiche delegate
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU -> PN-9177 |
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