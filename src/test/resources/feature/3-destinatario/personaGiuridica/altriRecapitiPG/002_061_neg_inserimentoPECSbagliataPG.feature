Feature: La persona giuridica inserisce una PEC

  @TestSuite
  @PG
  @recapitiPG
  @TA_inserimentoPECErrataPG

  Scenario: PN-9152-B60 - La persona giuridica loggato inserisce una PEC
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC errata "personaGiuridica"
    Then Si visualizza correttamente il messaggio di pec errata
    And Logout da portale persona giuridica