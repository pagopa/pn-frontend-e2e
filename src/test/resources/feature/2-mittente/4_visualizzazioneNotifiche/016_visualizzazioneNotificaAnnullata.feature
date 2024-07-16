Feature: Mittente visualizza correttamente la notifica in stato Annulato

  @TestSuite
  @TA_MittentevisualizzazioneDettaglioNotifichaAnnullataConPagamento
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-10247 - Mittente visualizza correttamente la notifica in stato Annullato con Pagamento
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica inserire il codice IUN "WVEM-RAVW-HLYV-202405-T-1"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si visualizza correttamente box di pagamento
    And Si visualizza correttamente il messaggio notifica annullata
    Then Si verifica che la notifica abbia lo stato "Annullata"
    And Logout da portale mittente

  @TA_DelegvisualizzazioneDettaglioNotifichaAnnullata
  @DeleghePF
  Scenario: [TA-FE INVIO DI UNA NOTIFICA E ANNULLAMENTO] - Mittente invia una notifica con delegato e la annulla, si verifica che sia visibile anche lato delegato
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    When Creo in background una delega per persona fisica
      | accessoCome | delegante        |
      | fiscalCode  | BRGLRZ80D58H501Q |
      | person      | true             |
      | displayName | Lucrezia Borgia  |
      | firstName   | Lucrezia         |
      | lastName    | Borgia           |
    And Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up OTP "corretto"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName | Gaio Giulio |
      | lastName  | Cesare      |
    And Logout da portale persona fisica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | 890                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | true               |
    And Si aggiunge un destinatario alla notifica
      | at                | Presso           |
      | indirizzo         | VIA ROMA 20      |
      | dettagliIndirizzo | Scala b          |
      | codicePostale     | 20147            |
      | comune            | Milano           |
      | dettagliComune    | Milano           |
      | provincia         | MI               |
      | stato             | Italia           |
      | nomeCognome       | Gaio Giulio      |
      | codiceFiscale     | CSRGGL44L13H501E |
      | tipoDestinatario  | PF               |
      | avvisoPagoPa      | 1                |
      | F24               | 1                |
    Then Creo in background una notifica per destinatario tramite API REST
    And Si seleziona la notifica
    And Aspetta 60 secondi
    And Si annulla la notifica
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    And Aspetta 10 secondi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sulle notifiche di "Gaio Giulio Cesare"
    And Si seleziona la notifica destinatario
    And Si visualizza correttamente la section Dettaglio Notifica annullata
    And Logout da portale persona fisica



