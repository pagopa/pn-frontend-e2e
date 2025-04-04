Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_DisattivaRecapitiDiCortesia_PF
  @addressBook1
  @NRT_ON

  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_41] Attivazione Gestisci Domicilio Digitale SEND PF -I tuoi Recapiti
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
    And Verifica e Disattiva Personalizzati per Ente
    And Aspetta 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare
    When Click Inizia
    And Click Attiva
    And Attesa 1 secondi
    And Click Non ora
    And Click Lo Faro piu tardi
    And Click Torna ai tuoi recapiti
    When Verifica Attivazione Domicilio digitale
##  REWORK_DOMICILIO_DIGITALE_PF_41
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
    And Nella pagina I Tuoi Recapiti si controlla che la Email sia presente

#   Disattivazione email
    And Verifica e Disattiva email
    And Attesa 1 secondi
    Then Verifica Da Attivare Email

