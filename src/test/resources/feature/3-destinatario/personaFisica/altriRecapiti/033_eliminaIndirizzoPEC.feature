Feature: la persona fisica elimina l'indirizzo pec

  Background:  Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @eliminaPECPF

  Scenario: la persona fisica elimina l'indirizzo pec
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una pec
    And Nella pagina I Tuoi Recapiti si clicca sul bottone elimina pec
    And Nel pop up elimina indirizzo pec si clicca sul bottone conferma
    Then Nella pagina I Tuoi Recapiti si controlla che l'indirizzo pec non sia presente
    And Logout da portale persona fisica