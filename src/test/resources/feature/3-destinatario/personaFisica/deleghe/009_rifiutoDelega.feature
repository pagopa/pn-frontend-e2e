Feature: Il delgato persona fisica rifiuta la delega che gli è stata inviata

  @TestSuite
  @TA_PFrifiutoDelega
  @DeleghePF
  @PF

  Scenario: PN-9414 - Il delegato persona fisica rifiuta la delega che gli è stata inviata
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
    #And Si controlla che la delega non sia più presente nella lista "personaFisica"
    And Si controlla che la delega non sia più presente nella lista
      | nome       | Gaio Giulio |
      | familyName | Cesare      |
    And Logout da portale persona fisica