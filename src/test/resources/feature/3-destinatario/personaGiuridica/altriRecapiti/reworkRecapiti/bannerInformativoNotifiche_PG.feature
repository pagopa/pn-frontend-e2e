Feature: Rework della pagina dei contatti

  @TestSuite
  @TA_BanneInformativoNotifiche_PG
  @addressBook2

  Scenario: [REWORK_DOMICILIO_DIGITALE_PG_15_16] - Attivazione Domicilio Digitale SEND - Banner informativo link consegnata PG
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone I Tuoi Recapiti
#    verificare mancano pezzi inerente a SEND sull'appIO
    And Verifica e Disattiva "domicilio digitale"
    And Attesa 1 secondi
    And Verifica e Disattiva "email"
    And Attesa 1 secondi
    And Verifica e Disattiva "cellulare"
    And Click Bottone Notifica
    And Click Bottone Notifiche dell Impresa

    And Click Bottone "Inizia"

    When Verifica Pagina "Attiva domicilio digitale"
    And Verifica Pagina "Inserisci PEC"
    And Verifica Pagina "Come funziona"
    And Verifica Pagina "Accedi alla notifica"

    When Click link consegnata
    And Verifica Pagina "Valore giuridico della notifica sul domicilio digitale SEND"
    And Verifica Pagina "Se scegli SEND come domicilio digitale"

    And Click Bottone "ho capito"
    Then Verifica Pagina "Attiva domicilio digitale"
    And Verifica Pagina "Inserisci PEC"
    And Verifica Pagina "Come funziona"
    And Verifica Pagina "Accedi alla notifica"