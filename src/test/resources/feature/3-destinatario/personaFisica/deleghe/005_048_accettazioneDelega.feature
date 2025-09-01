Feature: il delegato accetta la delega

  @TestSuite
  @TA_PFaccettaDelega
  @DeleghePF
  @PF
  @deleghe1
  @DeleghePFPG
  @NRT_PN13213
  Scenario: PN-9411 - il delegato accetta la delega
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Nella sezione Deleghe si verifica sia presente una delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si clicca sul menu della delega
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Nella sezione Deleghe si sceglie l'opzione revoca
    And Si conferma l'azione scegliendo revoca la delega
    And Si controlla che non ci sia più una delega
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega PF
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia          |
      | cognome       | Borgia            |
      | codiceFiscale | BRGLRZ80D58H501Q  |
      | ente          | Comune di Verona |
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file "PF"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe

    And Logout da portale persona fisica

    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica input
      | user         | lucrezia                 |
      | pwd          | password123            |
      | name         | Lucrezia            |
      | familyName   | Borgia                 |
      | fiscalNumber | TINIT-BRGLRZ80D58H501Q |
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Si verifica che presente un indicatore numerico in corrispondenza della voce di menù Deleghe
    And Si sceglie opzione accetta delega a tuo carico da "Gaio Giulio Cesare"
    And  Si clicca sul bottone indietro popup
    And Si sceglie opzione accetta delega a tuo carico da "Gaio Giulio Cesare"
    And Si inserisce il codice delega nel pop-up OTP "errato"
    And Si clicca sul bottone Accetta
    And Si vefifica il messaggio di codice sbagliato
    And  Si clicca sul bottone indietro popup
    And Si sceglie opzione accetta delega a tuo carico da "Gaio Giulio Cesare"
    And Si inserisce il codice delega nel pop-up OTP "corretto"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName   | Gaio Giulio       |
      | lastName    | Cesare            |