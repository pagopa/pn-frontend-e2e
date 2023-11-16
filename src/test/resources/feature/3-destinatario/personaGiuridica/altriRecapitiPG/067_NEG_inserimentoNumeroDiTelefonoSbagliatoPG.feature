Feature: la persona giuridica inserisce un numero di telefono errato
  Background: Login persona giuridica
    Given Login Page persona giuridica "personaGiuridica" viene visualizzata
    When Login portale persona giuridica tramite request method
    Then Home page persona giuridica viene visualizzata correttamente
    And Nella Home page persona giuridica si clicca su Send Notifiche Digitali
    And Si visualizza correttamente la Pagina Notifiche persona giuridica "personaGiuridica"

  @TestSuite
  @PG
  @TA_inserimentoCellulareErratoPG
  @recapitiPG

  Scenario: La persona giuridica inserisce un numero di telefono errato
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono errato "2318773225"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di numero di telefono errato
    And Nella pagina I Tuoi Recapiti si controlla che il tasto avvisami via sms sia bloccato
    And Logout da portale persona giuridica