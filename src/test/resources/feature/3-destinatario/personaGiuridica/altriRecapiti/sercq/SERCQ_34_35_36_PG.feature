Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_34_35_36_PG
  @addressBook2
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_34_35_36_PG]
   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
   And Rimuovi tutti i recapiti se esistono
   And Refresh pagina

   #    Precondizione
   When Click Inizia
   And Click Continua
   And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
   And Si clicca sul bottone del pop-up ok ho capito
   And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
   And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
   And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
   And Click Continua Tab Inserisci un recapito
   And Spuntare checkbox privacy
   And Click Attiva domicilio digitale
   And Click Torna ai tuoi recapiti
   When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
   And Verifica e Disattiva email

     #    Scenario: 34
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
#    And Seleziona Tipologia "Indirizzo PEC"
    And Spuntare checkbox privacy
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova1@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Attesa 2 secondi
    And Refresh pagina
    Then Verifica Pagina "prova1@pec.it"

   And Click Bottone Gestisci
   And Click Bottone "Personalizza per ente"
   And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
   And Spuntare checkbox privacy
   And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova2@pec.it"
   And Click Bottone conferma Pop-up
   And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
   And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova2@pec.it" tramite chiamata request

   And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
   And Click Torna ai tuoi recapiti
   When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
   And Attesa 2 secondi
   And Refresh pagina
   Then Verifica Pagina "prova2@pec.it"


  #    Scenario: 35
    And Click Modifica personalizzati per ente
   And Click Annulla
   And Click Modifica personalizzati per ente
    And Modifica Pec personalizzati per Ente e conferma "pec@pec.pagopa.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "pec@pec.pagopa.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente
    And Attesa 3 secondi
    And Refresh pagina
    And Verifica Pagina "pec@pec.pagopa.it"

#    Scenario: 36
    When Click Elimina personalizzati per ente
    And Attesa 2 secondi
    And Refresh pagina
    And Verifica Assenza Sezione Personalizzati Per Ente

