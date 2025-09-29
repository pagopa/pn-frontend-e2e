Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_8_1_12_14_16_20_PG
  @addressBook2
  #@TA_SERCQ_ON
  @NRT_Blocco_1
  Scenario:[SERCQ_8_1_12_14_16_20_PG]
   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti

   And Rimuovi tutti i recapiti se esistono
   And Refresh pagina

   When Click Inizia
    And Click Continua

    #    Scenario:1
    And Click Aggiungi email
    And Verifica  Indirizzo email non valido
    And Click Continua Tab Inserisci un recapito
    And Click Ok ho capito Recapiti

#    Scenario:8
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
   And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

#    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
#    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Nella sezione altri recapiti si clicca sul bottone annulla di popup

#    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email

   And Click Aggiungi email
   And Si clicca sul bottone del pop-up ok ho capito

    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email


#    Scenario:1
    And Click Continua Tab Inserisci un recapito
    And Click Attiva domicilio digitale
    And Verifica presenza Campo obbligatorio
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti

  #    Scenario:12

    And Click Modifica Email
    And Click Annulla
    And Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email del PG e clicca su Conferma

    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "provaemail@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata "provaemail@test.it"

#    Scenario:14

    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Conferma"
    When Click Inizia
    And Click Continua

    And Click Modifica Email
    And Click Annulla
    And Click Modifica Email
    And Si visualizzano correttamente i pulsanti modifica, elimina ed è possibile modificare l'email
    And Si inserisce la nuova Email "emailprova@test.it" del PG e clicca su Conferma
   And Si clicca sul bottone del pop-up ok ho capito

   And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova Email "emailprova@test.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    Then Nella pagina I Tuoi Recapiti si controlla che la Email sia stata modificata "emailprova@test.it"

    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti


#    Scenario:16
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale "Conferma"
    When Click Inizia
    And Click Continua
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Annulla
    And Verifica e Disattiva email

#    Scenario:20
    When Click Inizia
    And Click Continua
#    And Click Aggiungi email

    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email
   And Si clicca sul bottone del pop-up ok ho capito
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera l'OTP della Email tramite request method "personaGiuridica"
    And Nella pagina I Tuoi Recapiti Persona Giuridica si inserisce l'OTP ricevuto via Email

    And Click Continua Tab Inserisci un recapito
    And Spuntare checkbox privacy
    And Click Attiva domicilio digitale
    And Click Torna ai tuoi recapiti
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    And Verifica e Disattiva email
    And Disattiva domicilio digitale e Annulla
    And Verifica e Disattiva domicilio digitale "Conferma"