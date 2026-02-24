Feature: Rework della pagina dei contatti

#  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciModificaCancellaEmail_PF_OFF
  @addressBook1
  @TA_REWORK_RECAPITI_OFF
  @NRT_Blocco_2_OFF
  Scenario:[OFF_REWORK_DOMICILIO_DIGITALE_PF_85_86_87_92_93] Attivazione Domicilio Digitale SEND - Inserimento, modifica, cancellazione mail PF - Feature flag spento
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    And Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Attesa 1 secondi
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi
# Verifica presenza banner email mancante
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche
    And Aspetta 5 secondi
    And Si visualizza correttamente il banner di email mancante
    And La persona giuridica clicca sulla prima notifica restituita
    And Si visualizza correttamente il banner di email mancante
    And La persona fisica seleziona la voce I tuoi dati
    And Si visualizza correttamente il banner di email mancante
#  Creazione Email
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    When Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Verifica Pagina "prova@test.it"
    And Verifica Pagina "ti avvisiamo con una email all"

    #Disattiva email e annullo
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi
    And Si annulla eliminazione email

#  Modifica Email
    Then Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email del PF e clicca su Conferma
    And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "provaemailpf@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Verifica Pagina "provaemailpf@test.it"
    And Verifica Pagina "ti avvisiamo con una email all"
# Verifica assenza banner email mancante
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone Notifiche
    And Aspetta 10 secondi
    And Non si visualizza correttamente il banner di email mancante
    And La persona fisica clicca sulla prima notifica restituita
    And Non si visualizza correttamente il banner di email mancante
    And La persona fisica seleziona la voce I tuoi dati
    And Non si visualizza correttamente il banner di email mancante
#  Elimina Email
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva email
    And Aspetta 5 secondi
    And Verifica Da Attivare Email
