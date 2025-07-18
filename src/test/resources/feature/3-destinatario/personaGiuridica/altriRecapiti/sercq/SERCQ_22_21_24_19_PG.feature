Feature: Rework della pagina dei contatti

#  @TestSuite_ON
  @TA_SERCQ_22_21_24_19_PG
  @addressBook2
  @TA_SERCQ_ON
  @NRT_Blocco_2
  Scenario:[SERCQ_22_21_24_19_PG]
#   Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente
    And Si clicca su prodotto
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
    ##     verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
#    And Aspetta 1 secondi
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica ed Elimina personalizzati per ente
#    And Attesa 1 secondi
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva domicilio digitale
#    And Attesa 1 secondi
    And Attesa 1 secondi
    And Refresh pagina
    And Verifica e Disattiva email

    When Click Inizia

 #    Scenario:21
    And Click Bottone "Inserisci PEC"
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
    And Click Bottone "Inserisci PEC"
    And Click Attiva domicilio digitale
    And Verifica  Indirizzo pec non valido

    #    Scenario:22
    And Spuntare checkbox privacy

    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce OTP sbagliato tre volte "15494"
    And Si visualizza correttamente il messaggio di errore
    And Cliccare sul bottone Annulla

    And Click Attiva domicilio digitale

##    And Si inserisce la Pec della "personaGiuridica" e si clicca sul bottone Conferma
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP
    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Attesa 2 secondi
    And Refresh pagina

#        Scenario:24

    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC
    And Click Annulla
    And Nella pagina I Tuoi Recapiti si clicca sul bottone modifica PEC e si verifica che si possa modificare la PEC

    And Nella pagina I Tuoi Recapiti si inserisce una nuova PEC "prova@pec.it"
    And Click Bottone Conferma per modifica PEC
    And Nella pagina I Tuoi Recapiti si visualizza correttamente il pop-up di inserimento OTP

    And Nella pagina I Tuoi Recapiti si recupera il codice OTP della nuova PEC "prova@pec.it" tramite chiamata request
    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP
    And Si verifica se popup conferma presente

    Then Verifica Pagina "prova@pec.it"

#    Scenario:19
    And Disattiva domicilio digitale e Annulla
    And Verifica e Disattiva domicilio digitale


