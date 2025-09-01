Feature: Il delegato visualizza la notifiche del delegante
  
  @TestSuite
  @TA_PFvisualizzaNotificheDelegante
  @DeleghePF
  @deleghe1
  @PF
  @DeleghePFPG
  @DeleghePFPG1
    @loginFE_8_x
  @NRT_PN13213
  Scenario: PN-9419 - Il delegato visualizza la notifiche del delegante
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Aspetta 10 secondi
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Si controlla che non sia presente una delega con stesso nome
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Aspetta 10 secondi
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