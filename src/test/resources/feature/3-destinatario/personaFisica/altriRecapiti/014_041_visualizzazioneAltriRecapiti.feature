Feature: la persona fisica visualizza correttamente la sezione altri recapiti

  @TestSuite
  @TA_visualizzazioneSezioneAltriRecapitiPF
  @PF
  @recapitiPF

  Scenario: PN-9318-A40 - La persona fisica visualizza correttamente la sezione altri recapiti
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti di PF, si controlla che ci sia già una pec "prova@pec.it"
    And Nella sezione altri recapiti si seleziona l'ente "mittente"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo scegliendo "PEC"
    And Nella sezione altri recapiti si inserisce la PEC aggiuntiva de persona fisica "test2@pec.com"
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Aspetta 10 secondi
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request dell'email "pectest2@pec.it" e viene inserito
   # And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email 'altri recapiti' tramite request method "personaFisica"
   # And Nella pagina I Tuoi Recapiti si inserisce il codice OTP della email "personaFisica"
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti
    And Logout da portale persona fisica