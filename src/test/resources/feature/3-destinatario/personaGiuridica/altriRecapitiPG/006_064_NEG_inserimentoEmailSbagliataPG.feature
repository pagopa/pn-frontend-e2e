Feature: la persona giuridica inserisce una email errata
  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite token exchange "personaGiuridica"
    Then Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TestSuite
  @PG
  @TA_inserimentoEmailErrataPG
  @recapitiPG


  Scenario: PN-9155-B63 - La persona giuridica inserisce una email errata
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Nella pagina I Tuoi Recapiti si inserisce l'email errata "prova..@gmail.com"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata
    And Si controlla che il tasto avvisami via email sia bloccato
    And Logout da portale persona giuridica