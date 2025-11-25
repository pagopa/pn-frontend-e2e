Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_AttivazioneDomicilioDigitaleSEND_InserisciEmailCell_PF
#  @addressBook1
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_2
  Scenario:[REWORK_DOMICILIO_DIGITALE_PF_6_75] Attivazione Domicilio Digitale SEND - Inserimento mail e cellulare PF
#    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Clicca tasto Accedi OneTrust PG e PF
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Aspetta 1 secondi
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 2 secondi
    And Verifica e Disattiva email
    And Attesa 2 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Attiva
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
  #  INIZIO REWORK_DOMICILIO_DIGITALE_PG_75
    And Nella sezione altri recapiti si clicca sul bottone conferma di popup
    And Verifica testo nella pagina "Codice assente o incompleto"
    #  FINE REWORK_DOMICILIO_DIGITALE_PG_75
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email

    And Verifica testo nella pagina "La tua mail per ricevere aggiornamenti"
    And Verifica testo nella pagina "email dove possiamo informarti quando"

#  ----------------------------------------------------------------------
#  AD oggi  il test non è implementabile perchè non abbiamo la possibilita di recupero OTP
