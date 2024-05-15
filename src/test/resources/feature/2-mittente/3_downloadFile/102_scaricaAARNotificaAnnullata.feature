Feature: Mittente visualizza correttamente la notifica in stato Annulato

  @TestSuite
  @TA_MittenteScaricaAARDellaNotificaAnnullata
  @mittente
  @visualizzazioneNotificheMittente

  Scenario: PN-10245 - Mittente scarica documento AAR della notifica annullata
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche  persona fisica inserire il codice IUN "VDQK-PXHJ-MATL-202405-E-1"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si verifica che la notifica abbia lo stato "Annullata"
    And Nella sezione Dettaglio Notifiche si scarica il file AAR
    Then Si controlla il testo all interno del file "Avviso_di_avvenuta_ricezione"
    And Logout da portale mittente



