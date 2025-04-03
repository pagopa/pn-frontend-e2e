Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_DisattivaRecapitiDiCortesia_PG
  @addressBook2
  @NRT
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_41] Disattiva Recapiti di cortesia PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
##    verificare mancano pezzi inerente a SEND sull'appIO e indirizzo email
    And Verifica e Disattiva domicilio digitale
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    When Click Inizia
    And Click Attiva
    And Click Non ora
#    And Click Lo Faro piu tardi
#    And Click Torna ai tuoi recapiti
    Then Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PG_41
    When Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
    And Verifica Pagina "ti informiamo con una email"
#
    And Verifica e Disattiva email
    And Click Bottone Conferma email
    Then Verifica Da Attivare Email