Feature: Rework della pagina dei contatti

  @TestSuite_OFF
  @TA_OFF_REWORK_DOMICILIO_DIGITALE_79_80_82_83_84_81_PG
  @addressBook2
  @TA_REWORK_RECAPITI_OFF
  @NRT_Blocco_1_OFF

  Scenario:[OFF_REWORK_DOMICILIO_DIGITALE_PG_79_80_82_83_84_81] Attivazione Domicilio Digitale SEND - Inserisci - Modifica - Elimina PEC  PG

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare
    And Attesa 1 secondi

    When Click Inizia

##  REWORK_DOMICILIO_DIGITALE_PG_79
    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    #And Aspetta 2 secondi
    And Refresh pagina
##  REWORK_DOMICILIO_DIGITALE_PG_80
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC
    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC "provaa@pec.it"
    And Click Bottone Conferma per modifica PEC
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "provaa@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente
    Then Verifica Pagina "provaa@pec.it"
##  REWORK_DOMICILIO_DIGITALE_PG_82
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "provae@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "provae@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Click Torna ai tuoi recapiti
    #And Aspetta 1 secondi
    And Refresh pagina
##  REWORK_DOMICILIO_DIGITALE_PG_83
    When Click Modifica personalizzati per ente OFF
    And Modifica Pec personalizzati per Ente e conferma "pec@pec.pagopa.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "pec@pec.pagopa.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente
    And Verifica Pagina "pec@pec.pagopa.it"
##  REWORK_DOMICILIO_DIGITALE_PG_84
    When Click Elimina personalizzati per ente
    #And Aspetta 1 secondi
    And Refresh pagina
    And Verifica Assenza Sezione Personalizzati Per Ente
##  REWORK_DOMICILIO_DIGITALE_PG_81
    And Verifica e Disattiva domicilio digitale
    #And Aspetta 1 secondi
    And Refresh pagina
    Then Verifica Da Attivare Domicilio digitale

