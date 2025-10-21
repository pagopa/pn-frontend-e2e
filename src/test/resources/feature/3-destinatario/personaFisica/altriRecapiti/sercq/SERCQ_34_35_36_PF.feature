Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_SERCQ_34_35_36_PF
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_34_35_36_PF]
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    And Attesa 1 secondi
    And Refresh pagina
#    Precondizione
    When Click Inizia
    And Click Continua
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #    Scenario: 34
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
#    And Seleziona Tipologia "Indirizzo PEC"
    And Spuntare checkbox privacy
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1pf@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova1pf@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Attesa 2 secondi
    And Refresh pagina
    Then Verifica testo nella pagina "prova1pf@pec.it"
    And Refresh pagina
    And Attesa 20 secondi
     #Validation bug PF QA-8729
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Verifica Banner Personalizza il tuo domicilio digitale per ente mittente "associato al recapito prova1pf@pec.it"
    And Spuntare checkbox privacy
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova1pf@pec.it"
    And Click Torna ai tuoi recapiti
    # FINE Validation bug PF QA-8729
    And Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Spuntare checkbox privacy
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova2pf@pec.it"
    And Click Bottone conferma Pop-up
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova2pf@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Attesa 2 secondi
    And Refresh pagina
    Then Verifica testo nella pagina "prova2pf@pec.it"
    #    Scenario: 35
    And Click Modifica personalizzati per ente
    And Click Annulla
    And Click Modifica personalizzati per ente
    And Modifica Pec personalizzati per Ente e conferma "pectest@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "pectest@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente
    And Attesa 3 secondi
    And Refresh pagina
    And Verifica testo nella pagina "pectest@pec.it"
    #    Scenario: 36
    When Click Elimina personalizzati per ente
    And Attesa 2 secondi
    And Refresh pagina
    And Verifica Assenza Sezione Personalizzati Per Ente