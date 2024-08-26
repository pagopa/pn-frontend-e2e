Feature: La persona giuridica elimina l'indirizzo PEC

  @TestSuite
  @TA_eliminaPECPG
  @PG
  @recapitiPG

  Scenario: PN-9154 - La persona giuridica elimina l'indirizzo PEC
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti di PG, si controlla che ci sia già una pec "pec@pec.pagopa.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone elimina pec
    And Nel pop up elimina indirizzo pec si clicca sul bottone conferma
    Then Nella pagina I Tuoi Recapiti si controlla che l'indirizzo pec non sia presente
    And Logout da portale persona giuridica