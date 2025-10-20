Feature: Rework della pagina dei contatti

#  @TestSuite_ON
#  @TA_BanneInformativoNotifiche_PG
#  @addressBook2
#  @TA_REWORK_RECAPITI_ON
#  @NRT_Blocco_1

  Scenario: [REWORK_DOMICILIO_DIGITALE_PG_15_16] - Attivazione Domicilio Digitale SEND - Banner informativo link consegnata PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica ed Elimina personalizzati per ente
    And Verifica e Disattiva domicilio digitale "Conferma"
    And Attesa 1 secondi
    And Verifica e Disattiva email
    And Attesa 1 secondi
    And Verifica e Disattiva cellulare
    And Click Bottone Notifica
    And Click Bottone Notifiche dell Impresa

    And Click Bottone "Inizia"

    When Verifica testo nella pagina "Attiva domicilio digitale"
    And Verifica testo nella pagina "Inserisci PEC"
    And Verifica testo nella pagina "Come funziona"
    And Verifica testo nella pagina "Accedi alla notifica"

    When Click link consegnata
    And Verifica testo nella pagina "Valore giuridico della notifica sul domicilio digitale SEND"
    And Verifica testo nella pagina "Se scegli SEND come domicilio digitale"

    And Click Bottone "ho capito"
    Then Verifica testo nella pagina "Attiva domicilio digitale"
    And Verifica testo nella pagina "Inserisci PEC"
    And Verifica testo nella pagina "Come funziona"
    And Verifica testo nella pagina "Accedi alla notifica"