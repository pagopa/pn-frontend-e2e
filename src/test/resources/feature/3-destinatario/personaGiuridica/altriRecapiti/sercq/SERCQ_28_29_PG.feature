Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_228_229_PG
  @addressBook2
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_28_29_PG]
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
   And Attesa 1 secondi
   And Refresh pagina

   #    Precondizione
    When Click Inizia
    And Click Continua
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti

    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva email


    #    Scenario: 28
#   N.B. Banner sostituito dopo che eliminazione recapito mail elimina anche DD collegato
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    And Attesa 1 secondi
    And Refresh pagina
    And Si visualizza correttamente il banner di Domicilio Digitale non attivato

    And Click Bottone Inizia nel Banner
    And Click Annulla

    #    Scenario: 29
    #   N.B. Banner sostituito dopo che eliminazione recapito mail elimina anche DD collegato
    #And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    And Entro dentro la prima notifica con stato "Avvenuto accesso"
    And Si visualizza correttamente il banner di Domicilio Digitale non attivato
    And Refresh pagina
    And Si visualizza correttamente il banner di Domicilio Digitale non attivato
    And Click Bottone Inizia nel Banner
    And Click Annulla

    And Click Bottone Inizia nel Banner
    And Click Continua


    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email
    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    And Non si visualizza correttamente il banner di recapito di cortesia mancante con DD attivato
#   & Configurazione domicilio digitale - Fase 2: Scenario 20 (Verifica assenza banner per inserimento recapito di cortesia)
    And Entro dentro la prima notifica con stato "Avvenuto accesso"
    And Non si visualizza correttamente il banner di recapito di cortesia mancante con DD attivato
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Non si visualizza correttamente il banner di recapito di cortesia mancante con DD attivato










