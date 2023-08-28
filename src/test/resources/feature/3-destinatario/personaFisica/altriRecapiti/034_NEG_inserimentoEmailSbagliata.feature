Feature: La persona fisica inserisce una email sbagliata
  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  Scenario: La persona fisica inserisce una email sbagliata
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce l'email errata "prova&@gmail.com"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio email errata
    And Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via email sia bloccato
    And Logout da portale persona fisica
