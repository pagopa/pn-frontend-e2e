Feature: la persona giuridica inserisce un OTP email sbagliato
  Background: Login persona giuridica
    Given Login Page persona giuridica "personaFisica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente

  Scenario: la persona giuridica inserisce un OTP email sbagliato
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce l'email del PG "personaGiuridica" e clicca sul bottone avvisami via email
    And Si visualizza correttamente il pop-up e si clicca su conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato "15494"
    And Nella pagina I Tuoi Recapiti clicca sul bottone conferma
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla
    And Logout da portale persona giuridica