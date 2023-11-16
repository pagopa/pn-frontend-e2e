Feature: la persona fisica modifica l'indirizzo Email

  Background: Login persona fisica
    Given Login Page persona fisica "personaFisica" viene visualizzata
    When Login portale persona fisica tramite request method
    Then Home page persona fisica viene visualizzata correttamente

  @TestSuite
  @TA_modificaEmailPF
  @personaFisicaDestinatario
  @recapitiPF

  Scenario: la persona fisica modifica l'indirizzo Email
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica
    And Nella pagina I Tuoi Recapiti si inserisce la nuova Email del PF "personaFisica" e clicca su salva
    And Si visualizza correttamente il pop-up e si clicca su conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della nuova Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email "personaFisica"
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata
    And Logout da portale persona fisica