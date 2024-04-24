Feature: il delegato accetta la delega

  @TestSuite
  @TA_PFaccettaDelega
  @DeleghePF
  @PF

  Scenario: PN-9411 - il delegato accetta la delega
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
    And  Si clicca sul bottone indietro popup
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up OTP "errato"
    And Si clicca sul bottone Accetta
    And Si vefifica il messaggio di codice sbagliato
    And  Si clicca sul bottone indietro popup
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up OTP "corretto"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName   | Gaio Giulio       |
      | lastName    | Cesare            |
    And Logout da portale persona fisica