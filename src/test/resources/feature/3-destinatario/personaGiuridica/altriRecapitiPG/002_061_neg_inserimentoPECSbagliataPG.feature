Feature: La persona giuridica inserisce una PEC

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "personaGiuridica"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TestSuite
  @PG
  @recapitiPG
  @TA_inserimentoPECErrataPG


  Scenario: La persona giuridica loggato inserisce una PEC
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC errata "personaGiuridica"
    Then Si visualizza correttamente il messaggio di pec errata
    And Logout da portale persona giuridica