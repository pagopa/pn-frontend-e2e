Feature: La persona giuridica inserisce una PEC

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TestSuite
  @PG
  @recapitiPG
  @TA_inserimentoPECErrataPG


  Scenario: La persona giuridica loggato inserisce una PEC
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina Recapiti persona giuridica si inserisce una PEC sbagliata "provatest2.spqe"
    Then Nella pagina Recapiti persona giuridica si visualizza correttamente il messaggio di errore pec sbagliata
    And Logout da portale persona giuridica