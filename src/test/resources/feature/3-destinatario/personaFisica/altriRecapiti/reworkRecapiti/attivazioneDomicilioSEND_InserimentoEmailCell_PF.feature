Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_AttivazioneDomicilioDigitaleSEND_InserisciEmailCell_PF
  @addressBook1
  Scenario:[REWORK_DOMICILIO_DIGITALE_PG_6] Attivazione Domicilio Digitale SEND - Inserimento mail e cellulare PF
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Aspetta 2 secondi
    And Verifica e Disattiva "email"
    And Aspetta 2 secondi
    And Verifica e Disattiva "cellulare"

    When Click Inizia
    And Click Attiva
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email

    And Verifica Pagina "La tua mail per ricevere aggiornamenti"
    And Verifica Pagina "email dove possiamo informarti quando"
#  ----------------------------------------------------------------------
#    When Click Bottone "Aggiungi un numero di cellulare"
#    And Nella pagina I Tuoi Recapiti si inserisce il numero di telefono "3409876543" e si clicca sul bottone avvisami via SMS
#    And Aspetta 5 secondi
##    And Click Bottone "ho capito"
#
#    And Si clicca sul bottone del pop-up ok o capito
##
#    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "cellulare"
#    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Cellulare "personaFisica"





#    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Cellulare



#    And Click Annulla
#    Then Verifica Da Attivare Domicilio digitale
