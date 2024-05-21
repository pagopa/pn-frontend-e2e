Feature: Mittente visualizza correttamente la notifica in stato Annulato

  @TestSuite
  @TA_MittenteScaricaAllegatoDellaNotificaAnnullata
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-10244 - Mittente scarica documento allegato della notifica annullata
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN "WVEM-RAVW-HLYV-202405-T-1"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si verifica che la notifica abbia lo stato "Annullata"
     #per il momento non e possibile scaricare documento
   #And Nella sezione Dettaglio Notifiche si scarica il documento allegato
     # Then Si controlla il testo all interno del file "PN_NOTIFICATION_ATTACHMENTS"
    Then Si clicca sul documento allegato
    And Logout da portale mittente



