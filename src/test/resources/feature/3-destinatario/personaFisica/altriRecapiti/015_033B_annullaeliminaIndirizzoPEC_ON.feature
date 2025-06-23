Feature: la persona fisica elimina l'indirizzo pec

#  @TestSuite_ON
  @PF
  @TA_annulaEliminaPECPF_ON
  @addressBook1
  @TA_REWORK_RECAPITI_ON
  @NRT_Blocco_2
  Scenario: ON_REWORK_DOMICILIO_DIGITALE_PF_PN-9307-B33 - La persona fisica annlla eliminazione l'indirizzo pec
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone "Inserisci PEC"
##  REWORK_DOMICILIO_DIGITALE_PG_79
    And Si inserisce la Pec della "personaFisica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaFisica"
    And Aspetta 2 secondi

    Then Verifica e Disattiva domicilio digitale
    And Si annulla eliminazione email


