Feature: Rework della pagina dei contatti

  @TestSuite_OFF
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciModificaCancellaEmail_PG_OFF
  @addressBook2
  @TA_REWORK_RECAPITI_OFF

  @NRT_Blocco_1_OFF



  Scenario:[OFF_REWORK_DOMICILIO_DIGITALE_PG_85_86_87_92_93] Attivazione Domicilio Digitale SEND - Inserimento, modifica, cancellazione mail PG - Feature flag spento
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi
# Verifica presenza banner email mancante
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    #And Aspetta 5 secondi
    And Refresh pagina
    And Si visualizza correttamente il banner di email mancante
    And La persona giuridica clicca sulla prima notifica restituita
    And Si visualizza correttamente il banner di email mancante
#  Creazione Email
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
    And Verifica Pagina "Quando c’è una notifica per la tua impresa, ti informiamo con una email."
#  Modifica Email
    And Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email del PG e clicca su Conferma
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "provaemail@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Verifica Pagina "provaemail@test.it"
    And Verifica Pagina "Quando c’è una notifica per la tua impresa, ti informiamo con una email."
# Verifica assenza banner email mancante
    Then Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    #And Aspetta 5 secondi
    And Refresh pagina
    And Non si visualizza correttamente il banner di email mancante
    And La persona giuridica clicca sulla prima notifica restituita
    And Non si visualizza correttamente il banner di email mancante
#  Elimina Email
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva email
    #And Aspetta 5 secondi
    And Refresh pagina
    And Verifica Da Attivare Email

