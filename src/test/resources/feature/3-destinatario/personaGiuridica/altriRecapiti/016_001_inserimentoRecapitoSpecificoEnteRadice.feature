Feature: la persona giuridica inserisce un recapito aggiuntivo da un ente radice

  @TestSuite
  @TA_inserimentoRecapitoAggiuntivoDaEnteRadicePG
  @PG
  @recapitiPG

  Scenario: PN-10430 - La persona giuridica inserisce un recapito aggiuntivo da un ente radice
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    Then Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
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
    And Nella sezione altri recapiti si controlla l'esistenza di una PEC "personaFisica"
    And Nella sezione altri recapiti si seleziona l'ente "mittente"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo
    And Nella sezione altri recapiti si inserisce la PEC aggiuntiva de persona fisica "pectest2@pec.it"
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email 'altri recapiti' tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP della email "personaFisica"
    Then Nella sezione altri recapiti si controlla che la pec aggiuntiva sia stata inserita correttamente
    And Logout da portale persona giuridica