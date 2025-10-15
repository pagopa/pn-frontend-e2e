Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_25_26_27_PF
  @addressBook1
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_25_26_27_PF]
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva email
    And Verifica e Disattiva cellulare
    And Attesa 1 secondi
    And Refresh pagina
#    Scenario: 25
    And Click Notifiche
    And Attesa 1 secondi
    And Refresh pagina
    And Click Bottone Inizia nel Banner
    And Click Continua
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Annulla
#    Scenario: 26
    And Click I Tuoi Dati
    And Click Bottone Inizia nel Banner
    And Click Continua
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Annulla
    #    Scenario: 27
    And Click Notifiche
    And Entro dentro la prima notifica con stato "Avvenuto accesso"
    And Click Bottone Inizia nel Banner
    And Click Continua
    And Si inserisce l'email della "personaFisica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaFisica"
    And Nella pagina I Tuoi Recapiti si inserisce l'OTP ricevuto via Email
#    And Nella sezione altri recapiti si clicca sul bottone conferma di popup
    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    And Non si visualizza correttamente il banner di recapito di cortesia mancante con DD attivato