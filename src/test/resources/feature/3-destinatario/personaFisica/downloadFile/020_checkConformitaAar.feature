Feature: persona fisica scarica AAR e verifica la conformità

  @TestSuite
  @test19
  @TA_PFCheckConformitaAar
  @PF

  Scenario: PN-10436 - Scarica AAR e verifica la conformità
    Given PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche PF si recupera un codice IUN valido
    And Cliccare sul bottone Filtra persona fisica
    When La persona fisica clicca sulla notifica restituita
    And Si visualizza correttamente la section Dettaglio Notifica persona fisica
    Then Si clicca su "Avviso di avvenuta ricezione" e si verifica che il download del file sia avvenuto correttamente
