Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciEmailCell_PG
  @addressBook2
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_6_75] Attivazione Domicilio Digitale SEND - Inserimento mail e cellulare PG

   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Aspetta 1 secondi
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 2 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Attiva
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
     #  INIZIO REWORK_DOMICILIO_DIGITALE_PG_75
    And Nella sezione altri recapiti si clicca sul bottone conferma di popup
    And Verifica Pagina "Codice assente o incompleto"
    #  FINE REWORK_DOMICILIO_DIGITALE_PG_75

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email

    And Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Verifica Pagina "email dove possiamo informarti quando"
#  ----------------------------------------------------------------------

