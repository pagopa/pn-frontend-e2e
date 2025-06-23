Feature: La persona fisica inserisce una PEC sbagliata

#  @TestSuite_ON
  @TA_inserimentoPECErrataPF_ON
  @addressBook1
  @TA_REWORK_RECAPITI_ON
  @NRT_Blocco_2
  Scenario: ON_REWORK_DOMICILIO_DIGITALE_PF_PN-9240-B31 - La persona fisica inserisce una PEC sbagliata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

    When Nella pagina Piattaforma Notifiche persona fisica si clicca sul bottone I Tuoi Recapiti
    #    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare

    When Click Inizia
    And Click Bottone "Inserisci PEC"

    And Inserisci Pec Errata "testpagopa2@@pnpagopa.postecert.local"
    And Si visualizza correttamente il messaggio di pec non valida
    And Nella pagina I Tuoi Recapiti si inserisce un PEC maggiore di 255 caratteri
    And Si visualizza correttamente il messaggio di pec non valida
    And Click Bottone Indietro Trasferisci e Personalizza il domicilio digitale
