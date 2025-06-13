Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_AttivazioneRecapitiInserimentoEmail_PG
  @addressBook2
  @TA_REWORK_RECAPITI_ON

  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_28_29_27] Attivazione Recapiti Inserimento Email PG

   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
   And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
#    And Attesa 2 secondi
    And Verifica e Disattiva email
#    And Attesa 2 secondi
    And Verifica e Disattiva cellulare

    ##  REWORK_DOMICILIO_DIGITALE_PF_28
    When Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up Annulla
    And Verifica Da Attivare Email
###  REWORK_DOMICILIO_DIGITALE_PF_29
   And Aspetta 1 secondi
    When Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Cliccare sul bottone Annulla
    And Verifica Da Attivare Email
#    ##  REWORK_DOMICILIO_DIGITALE_PF_27
   And Aspetta 1 secondi
    When Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
    And Nella pagina I Tuoi Recapiti si controlla che la Email sia presente

