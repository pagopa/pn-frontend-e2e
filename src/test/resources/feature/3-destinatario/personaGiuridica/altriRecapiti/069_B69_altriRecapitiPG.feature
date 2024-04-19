Feature: La persona giuridica visualizza tutti gli elementi della sezione altri recapiti

  @TestSuite
  @TA_atriRecapitiPGInseriscePec
  @PG
  @recapitiPG

  Scenario: PN-9161 -B1 La persona giuridica inserisce PEC sbagliato e corretto
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti di PG, si controlla che ci sia già una pec
    And Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti persona giuridica
    And Nella sezione altri recapiti PG si seleziona l'ente "Agenzia delle Entrate"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo PG scegliendo "PEC"
    And Nella sezione altri recapiti si inserisce la PEC aggiuntiva "test/pec.it"
    And Nella sezione altri recapiti si visualizza il messaggio di errore
    And Nella sezione altri recapiti si cancella email da textbox
    And Nella pagina I Tuoi Recapiti si inserisce un PEC maggiore di 255 caratteri
    And Nella sezione altri recapiti si visualizza il messaggio di errore
    And Nella sezione altri recapiti si cancella email da textbox
    And Nella sezione altri recapiti si inserisce la PEC aggiuntiva "prova@pec.it"
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato "15494"
    And Nella sezione altri recapiti si clicca sul bottone conferma di popup
    And Nella sezione altri recapiti si visualizza correttamente il messaggio di errore di popup
    And Nella sezione altri recapiti si clicca sul bottone annulla di popup
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC tramite chiamata request
    And Aspetta 660 secondi
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Nella sezione altri recapiti si visualizza correttamente il messaggio di errore di popup
    And Nella sezione altri recapiti si clicca sul bottone annulla di popup
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione 'E-mail o numero di cellulare'
    And Logout da portale persona giuridica