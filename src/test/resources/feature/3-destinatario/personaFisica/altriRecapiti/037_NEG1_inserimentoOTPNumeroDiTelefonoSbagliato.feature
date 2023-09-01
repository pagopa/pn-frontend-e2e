Feature: la persona fisica inserisce l'OTP numero di telefono  errato
  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @fase2Test37_neg1
  Scenario: la persona fisica inserisce l'OTP numero di telefono errato
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono del PF "personaFisica" e clicca sul bottone avvisami via SMS
    And Si visualizza correttamente il pop-up e si clicca su conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato "15494"
    And Nella pagina I Tuoi Recapiti clicca sul bottone conferma
    And Si visualizza correttamente il messaggio di errore
    Then Cliccare sul bottone Annulla
    And Logout da portale persona fisica