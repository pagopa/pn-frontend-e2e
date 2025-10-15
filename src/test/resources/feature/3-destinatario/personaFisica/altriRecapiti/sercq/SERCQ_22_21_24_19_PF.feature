Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_22_21_24_19_PF
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_22_21_24_19_PF]
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
    And Attesa 1 secondi
    And Refresh pagina
    When Click Inizia
    #    Scenario:21
    And Click Bottone "Inserisci PEC"
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone "Inserisci PEC"
    And Click Attiva domicilio digitale PEC
    And Verifica  Indirizzo pec non valido
    #    Scenario:22
    And Spuntare checkbox privacy
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla
    And Click Attiva domicilio digitale PEC
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Attesa 2 secondi
    And Refresh pagina
#    Scenario:24
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC
    And Click Annulla
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC
    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC "provapf@pec.it"
    And Click Bottone Conferma per modifica PEC
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "provapf@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Attesa 2 secondi
    And Refresh pagina
    Then Verifica Pagina "provapf@pec.it"
#   SERCQ Fase 2: Scenario 19
#   & Configurazione domicilio digitale - Fase 2: Scenario 18 (Disattivazione Domicilio Digitale PEC)
    And Disattiva domicilio digitale e Annulla
    And Verifica e Disattiva domicilio digitale "Conferma"

