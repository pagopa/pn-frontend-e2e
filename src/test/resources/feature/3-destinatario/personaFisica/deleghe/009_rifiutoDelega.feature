Feature: Il delgato persona fisica rifiuta la delega che gli è stata inviata

  @TestSuite
  @TA_PFrifiutoDelega
  @DeleghePF
  @PF
  @DeleghePFPG1
  @deleghe1
  @NRT_Blocco_3_GRUPPO_AWS
  Scenario: PN-9414 - Il delegato persona fisica rifiuta la delega che gli è stata inviata

    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
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

    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    When Creo in background una delega per persona fisica
      | accessoCome | delegante        |
      | fiscalCode  | BRGLRZ80D58H501Q |
      | person      | true             |
      | displayName | Lucrezia Borgia  |
      | firstName   | Lucrezia         |
      | lastName    | Borgia           |
    And Si verifica sia presente una delega da rifiutare nella sezione Deleghe a Tuo Carico
      | nome          | Gaio Giulio |
      | cognome       | Cesare      |
    And Nella pagina Deleghe si clicca sul menu della delega a tuo carico
      | nome          | Gaio Giulio |
      | cognome       | Cesare      |
    And Nella pagina Deleghe si sceglie opzione rifiuta
    And Si clicca sul bottone rifiuta all'interno del pop-up
    And Si controlla che la delega non sia più presente nella lista "personaFisica"