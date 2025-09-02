Feature:Il delegato persona giuridica accede ad una delega

  @TestSuite
  @TA_PFdelegatoPagaNotifica
  @DeleghePF
  @PF
  @DeleghePFPG
  @deleghe1
  @NRT_PN13213
  Scenario: PN-10388 - Il delegato persona fisica paga una notifica
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Si controlla che non sia presente una delega con stesso nome
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Rimuovi tutti i delegati da i Tuoi Delegati se esistono
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega PF
    And Si visualizza correttamente la pagina nuova delega
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
    And Si sceglie opzione accetta delega a tuo carico da "Gaio Giulio Cesare"
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName | Gaio Giulio |
      | lastName  | Cesare      |