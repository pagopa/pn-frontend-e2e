Feature: La persona giuridica visualizza tutti gli elementi della sezione altri recapiti

  @TestSuite
  @TA_atriRecapitiPGInserisceEmail
  @PG
  @recapitiPG

  Scenario: PN-9161 -D1 La persona giuridica inserisce email sbagliato e corretto
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si visualizza correttamente la pagina Recapiti persona giuridica
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Si inserisce l'email "prova@test.it" e si clicca sul bottone avvisami via email
    And Si visualizza correttamente il pop-up e si clicca su conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "prova@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia presente
    And Nella pagina I Tuoi Recapiti si visualizza correttamente la sezione altri recapiti persona giuridica "email"
    And Nella sezione altri recapiti PG si seleziona l'ente "Agenzia delle Entrate"
    And Nella sezione altri recapiti si seleziona il tipo di indirizzo PG scegliendo "Email"
    And Nella sezione altri recapiti si inserisce la email aggiuntiva "test/mail.it"
    And Nella sezione altri recapiti si visualizza il messaggio di errore "email"
    And Nella sezione altri recapiti si cancella email da textbox "email"
    And Nella pagina I Tuoi Recapiti si inserisce un email aggiuntiva maggiore di 255 caratteri
    And Nella sezione altri recapiti si visualizza il messaggio di errore "email"
    And Nella sezione altri recapiti si cancella email da textbox "email"
    And Nella sezione altri recapiti si inserisce la email aggiuntiva "prova@mail.it"
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato "15494"
    And Nella sezione altri recapiti si clicca sul bottone conferma di popup
    And Nella sezione altri recapiti si visualizza correttamente il messaggio di errore di popup
    And Nella sezione altri recapiti si clicca sul bottone annulla di popup
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "prova@mail.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP scaduto
    And Nella sezione altri recapiti si visualizza correttamente il messaggio di errore di popup
    And Nella sezione altri recapiti si clicca sul bottone annulla di popup
    And Nella sezione altri recapiti si clicca sul bottone associa
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "prova@mail.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente
    Then Si visualizzano correttamente tutti gli elementi della sezione altri recapiti
    And Logout da portale persona giuridica