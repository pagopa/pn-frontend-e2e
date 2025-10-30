Feature: Mittente visualizza correttamente la notifica in stato Annulato

  @TA_MittenteScaricaAARDellaNotificaAnnullata
  @DownloadFileMittente
  @NRT_Blocco_1

  Scenario: PN-10245-A - Mittente scarica documento AAR della notifica annullata
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica inserire il codice IUN "UTLT-LRQV-PKXU-202510-D-1"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si verifica che la notifica abbia lo stato "Annullata"
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[@id='document-button' and .//div[contains(text(),'Avviso di avvenuta ricezione')]] |
      | vediDettagli | false                                                                                       |
    Then Si clicca sul documento AAR



