Feature: il delegato accetta la delega

  @TestSuite
  @TA_PF_3_VerificareCheUnUtenteDelegatoVisualizzaLeNotificheUtenteDelegante
  @DeleghePF
  @PF
  @deleghe1
  @DeleghePFPG
  @NRT_Blocco_3
  Scenario: [QA-8819_3] Verificare Che Un Utente Delegato Visualizza Le Notifiche Utente Delegante

    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica input
      | user         | lucrezia                 |
      | pwd          | password123            |
      | name         | Lucrezia            |
      | familyName   | Borgia                 |
      | fiscalNumber | TINIT-BRGLRZ80D58H501Q |
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Revoca deleghe PF se esistono
    And Logout da portale persona fisica
    # Per creazione deleghe in DEV (background in DEV non funziona)
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si controlla che non sia presente una delega con stesso nome
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza correttamente la pagina nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia            |
      | cognome       | Borgia              |
      | codiceFiscale | BRGLRZ80D58H501Q    |
      | ente          | Comune di Verona    |
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file "PF"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe
    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe
    And Si sceglie opzione accetta delega a tuo carico da "Gaio Giulio Cesare"
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName   | Gaio Giulio       |
      | lastName    | Cesare            |
    And Click Notifiche
    And Click Notifica "Gaio Giulio Cesare"
    And Verifica Esistenza Tabella Notifiche