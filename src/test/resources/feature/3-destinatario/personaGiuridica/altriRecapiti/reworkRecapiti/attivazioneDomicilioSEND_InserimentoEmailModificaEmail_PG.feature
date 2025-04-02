Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciEmailModificaEmail_PG
  @addressBook2
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_10_11] Attivazione Domicilio Digitale SEND - Inserimento mail e cellulare PF
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Attesa 1 secondi
    And Verifica e Disattiva "email"
    And Attesa 1 secondi
    And Verifica e Disattiva "cellulare"

    When Click Inizia
    And Click Attiva
    And Si inserisce l'email della "personaGiuridiche" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridiche"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email

    And Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Verifica Pagina "email dove possiamo informarti quando"
##  Modifica Email
    When Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email del PG e clicca su Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "provaemail@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    Then  Verifica Pagina "provaemail@test.it"
#  ----------------------------------------------------------------------

