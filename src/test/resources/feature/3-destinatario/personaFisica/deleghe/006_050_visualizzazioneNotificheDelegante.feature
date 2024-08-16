Feature: Il delegato visualizza la notifiche del delegante
  
  @TestSuite
  @TA_PFvisualizzaNotificheDelegante
  @DeleghePF
  @PF

  Scenario: PN-9419 - Il delegato visualizza la notifiche del delegante
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    When Creo in background una delega per persona fisica
      | accessoCome | delegante        |
      | fiscalCode  | BRGLRZ80D58H501Q |
      | person      | true             |
      | displayName | Lucrezia Borgia  |
      | firstName   | Lucrezia         |
      | lastName    | Borgia           |
    And Si accetta la delega senza gruppo
    And Nella Pagina Notifiche destinatario si clicca su notifiche delegate
    And Si controlla la pagina delle notifiche delegati di "Gaio Giulio Cesare"
    And Si clicca sulla notifica del delegante
    And Si controlla il dettaglio della notifica
    And Logout da portale persona fisica