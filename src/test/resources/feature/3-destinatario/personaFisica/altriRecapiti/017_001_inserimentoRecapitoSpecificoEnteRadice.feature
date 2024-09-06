Feature: la persona fisica inserisce un recapito aggiuntivo da un ente radice

  @TestSuite
  @TA_inserimentoRecapitoAggiuntivoDaEnteRadicePF
  @PF
  @recapitiPF

  Scenario: PN-10426 - La persona fisica inserisce un recapito aggiuntivo da un ente radice
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina I Tuoi Recapiti
    And Nella pagina I Tuoi Recapiti di PF, si controlla che ci sia già una pec "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti
    Then Si clicca sul dropdown "ente" di altri recapiti
    And Si visualizza correttamente la lista degli enti
      | Agenzia delle Entrate        |
      | Comune di Afragola           |
      | Comune di Caserta            |
      | Comune di Cotronei           |
      | Comune di Mercenasco         |
      | Comune di Palmanova          |
      | Comune di Trivignano Udinese |
      | Comune di Vibo Valentia      |
      | Istituto Nazionale           |
      | Mercurio Riscossioni         |
    And Nella sezione altri recapiti si controlla l'esistenza di una PEC "pectest@pec.it"
    And Nella sezione altri recapiti si seleziona l'ente
   # And Nella sezione altri recapiti si seleziona l'ente "mittente"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo
    And Nella sezione altri recapiti si inserisce la PEC aggiuntiva de persona fisica "pectest2@pec.it"
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request dell'email "pectest2@pec.it" e viene inserito
    #And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email 'altri recapiti' tramite request method "personaFisica"
    #And Nella pagina I Tuoi Recapiti si inserisce il codice OTP della email "personaFisica"
    Then Nella sezione altri recapiti si controlla che la pec aggiuntiva sia stata inserita correttamente
    And Logout da portale persona fisica