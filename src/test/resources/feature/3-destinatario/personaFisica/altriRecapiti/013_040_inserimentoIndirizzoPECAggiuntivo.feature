Feature: la persona fisica inserisce un indirizzo pec aggiuntivo

  @TestSuite
  @TA_PECAggiuntivaPF
  @recapitiPF
  @PF

  Scenario: PN-9318-D40 - la persona fisica inserisce un indirizzo pec aggiuntivo
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Rimuovi tutti i recapiti se esistono
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti di PF, si controlla che ci sia già una pec "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti
    And Nella sezione altri recapiti si controlla l'esistenza di una PEC "personaFisica"
    And Nella sezione altri recapiti si seleziona l'ente "mittente"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo
    And Nella sezione altri recapiti si inserisce la PEC aggiuntiva de persona fisica "pectest2@pec.it"
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email 'altri recapiti' tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP della email "personaFisica"
    Then Nella sezione altri recapiti si controlla che la pec aggiuntiva sia stata inserita correttamente
    And Logout da portale persona fisica
