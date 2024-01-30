Feature: la persona fisica inserisce un indirizzo Email aggiuntivo

  @TestSuite
  @PF
  @TA_emailAggiuntivaPF
  @recapitiPF

  Scenario: PN-9318-I40 - la persona fisica inserisce un indirizzo Email aggiuntivo
    Given PF - Si effettua la login tramite token exchange di "personaFisica" e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti si controlla che ci sia già una Email diversa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti
    And Nella sezione altri recapiti si controlla l'esistenza di una email "personaFisica"
    And Nella sezione altri recapiti si seleziona l'ente "mittente"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo scegliendo email
    And Nella sezione altri recapiti si inserisce la Email aggiuntiva della persona fisica "test2@test.com"
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP della email "personaFisica"
    Then Nella sezione altri recapiti si controlla che la Email aggiuntiva sia stata inserita correttamente
    And Logout da portale persona fisica