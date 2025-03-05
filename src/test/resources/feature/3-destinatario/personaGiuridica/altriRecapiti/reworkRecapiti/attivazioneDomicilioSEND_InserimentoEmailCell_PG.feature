Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciEmailCell_PG
  @addressBook2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_6] Attivazione Domicilio Digitale SEND - Inserimento mail e cellulare PG

   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Verifica e Disattiva "email"
    And Verifica e Disattiva "cellulare"

    When Click Inizia
    And Click Attiva
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email

    And Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Verifica Pagina "email dove possiamo informarti quando"
#  ----------------------------------------------------------------------
#    When Click Bottone "Aggiungi un numero di cellulare"
#
#    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3334545899" e si clicca sul bottone avvisami via SMS
#    And Si visualizza correttamente il pop-up e si clicca su conferma
#
#    #In questo step viene fatta una chiamata per OTP delle mail, da cambiare quando avremo la chiamata per il cellulare
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
#    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "cellulare"
#    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Cellulare



#    And Click Annulla
#    Then Verifica Da Attivare Domicilio digitale
