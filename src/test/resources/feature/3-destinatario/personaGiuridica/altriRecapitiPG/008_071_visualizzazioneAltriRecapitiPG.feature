Feature: La persona giuridica visualizza tutti gli elementi della sezione altri recapiti

  @TestSuite
  @TA_visualizzazioneSezioneAltriRecapitiPG
  @PG
  @recapitiPG

  Scenario: PN-9162 - La persona giuridica visualizza tutti gli elementi della sezione altri recapiti
    Given PG - Si effettua la login tramite token exchange di "personaGiuridica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti della persona giuridica
    And Logout da portale persona giuridica
