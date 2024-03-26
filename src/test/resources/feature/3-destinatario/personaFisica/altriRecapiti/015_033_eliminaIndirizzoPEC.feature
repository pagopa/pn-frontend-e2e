Feature: la persona fisica elimina l'indirizzo pec

  @TestSuite
  @PF
  @TA_eliminaPECPF
  @recapitiPF

  Scenario: PN-9307-A33 - La persona fisica elimina l'indirizzo pec
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti di PF, si controlla che ci sia già una pec
    And Nella pagina I Tuoi Recapiti si clicca sul bottone elimina pec
    And Nel pop up elimina indirizzo pec si clicca sul bottone conferma
    Then Nella pagina I Tuoi Recapiti si controlla che l'indirizzo pec non sia presente
    And Logout da portale persona fisica