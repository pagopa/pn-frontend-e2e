Feature: la persona fisica elimina l'indirizzo Email

  @TestSuite
  @TA_annullaeliminaEmailPF
  @recapitiPF
  @PF

  Scenario: PN-9310-B36 - la persona fisica elimina l'indirizzo Email
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email
    And Nella pagina I Tuoi Recapiti si clicca sul bottone elimina email e si annulla nel pop up
    And Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non stata eleminata
    And Nella pagina I Tuoi Recapiti si clicca sul bottone elimina email e si conferma nel pop up
    Then Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non sia presente
    And Logout da portale persona fisica