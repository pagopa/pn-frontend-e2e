Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_REWORK_DOMICILIO_DIGITALE_PF_49_50_53_54_51_52_56_55_PF
  @addressBook1
  @TA_REWORK_RECAPITI_ON
  @NRT_Blocco_2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_49_50_53_54_51_52_56_55] Cambio Domicilio Digitale PEC Per Ente Personalizzato PF
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone "Inserisci PEC"


    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Aspetta 1 secondi

    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
##  REWORK_DOMICILIO_DIGITALE_PG_49
    When Click Bottone Gestisci
    And Click Bottone "Trasferisci su SEND"
    And Click Attiva
    When Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale
    And Click Torna ai tuoi recapiti
    Then Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PG_50
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Verifica Pagina "Personalizza il tuo domicilio digitale per ente mittente"
    And Verifica Pagina "ente e il recapito da associare"
##  REWORK_DOMICILIO_DIGITALE_PG_53
    When Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Verifica Pagina "domicilio digitale"
    And Verifica Pagina "La piattaforma SEND"
##  REWORK_DOMICILIO_DIGITALE_PG_54
    When Click Bottone "Personalizza per ente"
    And Click Bottone Esci PF
    And Verifica Pagina "Il tuo domicilio digitale"
    And Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PG_51
    When Click Bottone Gestisci
    And Click Bottone "Personalizza per ente"
    And Click Menu Ente Mittente Inserimento ente "Agenzia delle Entrate"
    And Inserisci Pec in Personalizza il tuo domicilio digitale per ente "prova@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
##  REWORK_DOMICILIO_DIGITALE_PG_52
    When Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale
    And Click Torna ai tuoi recapiti
    And Verifica Pagina "Validazione PEC in corso"
##  REWORK_DOMICILIO_DIGITALE_PG_56
    And Aspetta 1 secondi
    And Click Modifica personalizzati per ente
    And Click Bottone "Annulla"
    And Verifica Pagina "prova@pec.it"
    And Click Modifica personalizzati per ente
    And Modifica Pec personalizzati per Ente e conferma "pectest@pec.it"
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "pectest@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente
    And Verifica Pagina "pectest@pec.it"