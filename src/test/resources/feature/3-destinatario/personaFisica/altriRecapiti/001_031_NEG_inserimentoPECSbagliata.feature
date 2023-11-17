Feature: La persona fisica inserisce una PEC sbagliata
  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @TA_inserimentoPECErrataPF
  @recapitiPF
  @personaFisicaDestinatario

  Scenario: La persona fisica inserisce una PEC sbagliata
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC errata "testpagopa2@@pnpagopa.postecert.local"
    Then Nella pagina I Tuoi Recapiti si visualizza correttamente il messaggio di pec errata
    And Nella pagina I Tuoi Recapiti si controlla che il tasto conferma sia bloccato
    And Logout da portale persona fisica