Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_25_27_PG
  @addressBook2
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_25_27_PG]
#   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente
    And Si clicca su prodotto
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica ed Elimina personalizzati per ente
    And Attesa 1 secondi
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email


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


    #    Scenario: 28
    And Attesa 1 secondi
    And Refresh pagina
    And Click Bottone Inizia nel Banner
    And Verifica abilitazione campo email

    #    Scenario: 29
    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    And Entro dentro la prima notifica con stato "Avvenuto accesso"
    And Click Bottone Inizia nel Banner
    And Verifica abilitazione campo email


    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email

    And Nella pagina Piattaforma Notifiche persona giuridica si clicca solo su notifiche dell' impresa
    And Verifica Scomparsa Banner Inizia










