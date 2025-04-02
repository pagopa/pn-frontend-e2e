Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_attivazioneDomicilioSEND_RecapitoCortesiaAttivo_PG
  @addressBook2
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_38] Attivazione Domicilio Digitale SEND PG - Recapiti di cortesia presenti

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva domicilio digitale
    And Aspetta 2 secondi
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
    When Click Inizia
    And Click Attiva
    Then Si visualizza correttamente la pagina di avvenuta attivazione del Domicilio Digitale