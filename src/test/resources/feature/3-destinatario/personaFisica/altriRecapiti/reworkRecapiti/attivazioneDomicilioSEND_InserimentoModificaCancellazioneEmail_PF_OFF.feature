Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciModificaCancellaEmail_PF_OFF
  @addressBook1
  @TA_NRT_OFF

  Scenario:[OFF_REWORK_DOMICILIO_DIGITALE_PF_85_86_87] Attivazione Domicilio Digitale SEND - Inserimento, modifica, cancellazione mail PF - Feature flag spento
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Attesa 1 secondi
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "email"
    And Attesa 2 secondi
#  Creazione Email
    When Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
    And Verifica Pagina "ti avvisiamo con una email all"
#  Modifica Email
    Then Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email del PF e clicca su Conferma
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "provaemail@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Verifica Pagina "provaemail@test.it"
    And Verifica Pagina "ti avvisiamo con una email all"
#  Elimina Email
    #And Nella pagina I Tuoi Recapiti si clicca sul bottone elimina email e si conferma nel pop up
    #And Nella pagina I Tuoi Recapiti si controlla che l'indirizzo Email non sia presente
