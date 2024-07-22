Feature:Il delegato persona giuridica accede ad una delega

  @TestSuite
  @TA_PFdelegatoPagaNotifica
  @DeleghePF
  @PF

  Scenario: PN-10388 - Il delegato persona fisica paga una notifica
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA           |
      | codicePostale    | 20147              |
      | comune           | Milano             |
      | dettagliComune   | Milano             |
      | provincia        | MI                 |
      | stato            | Italia             |
      | nomeCognome      | Gaio Giulio Cesare |
      | codiceFiscale    | CSRGGL44L13H501E   |
      | tipoDestinatario | PF                 |
      | avvisoPagoPa     | 1                  |
    Then Creo in background una notifica per destinatario tramite API REST
    And Aspetta 10 secondi
    And La persona giuridica clicca sulla prima notifica restituita
    And Cliccare sul bottone Paga
    Then Si inserisce i dati di pagamento e procede con il pagamento "prova@test.it"
    And Si verifica che visualizzato lo stato Pagato
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Si controlla che non sia presente una delega con stesso nome
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza correttamente la pagina nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Palermo   |
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file "nuova_delega"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up OTP "corretto"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName   | Gaio Giulio       |
      | lastName    | Cesare            |
    And Nella Pagina Notifiche persona giuridica si clicca su notifiche delegate
    And La persona giuridica clicca sulla prima notifica restituita
    And Si verifica che visualizzato lo stato Pagato
    And Logout da portale persona fisica