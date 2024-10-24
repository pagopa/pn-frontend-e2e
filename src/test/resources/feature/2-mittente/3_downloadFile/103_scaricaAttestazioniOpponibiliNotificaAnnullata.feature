Feature: Mittente visualizza correttamente la notifica in stato Annulato

  @TestSuite
  @TA_MittenteScaricaAttestazioniDellaNotificaAnnullata
  @mittente
  @visualizzazioneNotificheMittente
  Scenario: PN-10245-B - Mittente scarica documento AAR della notifica annullata
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica inserire il codice IUN "NPMK-EKTP-AGAD-202410-N-1"
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica
    And Si verifica che la notifica abbia lo stato "Annullata"
    And Si verifica che la ricevuta di postalizzazione sia cliccabile
      | xpathStato   | //button[contains(text(),"Attestazione opponibile a terzi: notifica presa in carico")] |
      | vediDettagli | false                                          |
    Then Si verifica che il link sul documento Attestazione è cliccabile
    And Logout da portale mittente