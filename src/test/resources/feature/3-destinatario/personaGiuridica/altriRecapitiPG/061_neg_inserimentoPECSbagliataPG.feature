Feature: il persona giuridica inserisce una OTP sbagliato PEC

  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente

  Scenario: il persona giuridica loggato inserisce un OTP sbagliato PEC
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce una PEC sbagliata "provatest2.spqe"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di errore pec sbagliata
    And Logout da portale persona giuridica