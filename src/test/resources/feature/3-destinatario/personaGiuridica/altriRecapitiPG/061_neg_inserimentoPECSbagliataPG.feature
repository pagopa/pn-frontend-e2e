Feature: il persona giuridica inserisce una OTP sbagliato PEC

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login con persona giuridica "personaGiuridica"
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica

  @TestSuite
  @fase2Test61_neg
  Scenario: il persona giuridica loggato inserisce un OTP sbagliato PEC
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti persona giuridica si inserisce una PEC sbagliata "provatest2.spqe"
    Then Nella pagina I Tuoi Recapiti persona giuridica si visualizza correttamente il messaggio di errore pec sbagliata
    And Logout da portale persona giuridica