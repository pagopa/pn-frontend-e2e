Feature: la persona fisica inserisce un numero di telefono errato
  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente
  @TestSuite
  @fase2Test37_neg
  @new
  Scenario: La persona fisica inserisce un numero di telefono errato
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono errato "2318773225"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di numero di telefono errato
    And Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via sms sia bloccato
    And Logout da portale persona fisica