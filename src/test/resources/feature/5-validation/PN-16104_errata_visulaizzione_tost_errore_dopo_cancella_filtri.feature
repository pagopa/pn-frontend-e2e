Feature: il mittente fa una ricerca combinata tra stato e arco temporale  con nessun risultato

#  @TestSuite
    @TA_PN-16104_errata_visulaizzione_tost_errore_dopo_cancella_filtri_PA
    @NRT_VALIDATION
  Scenario Outline: PN-16104 errata_visulaizzione_tost_errore_dopo_cancella_filtri_PA
#      Scenario: PN-16104 errata_visulaizzione_tost_errore_dopo_cancella_filtri_PA
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Nella pagina Statistiche inserire una data da <inizioArcoTemporale> a <fineArcoTemporale>
#
    And Nella pagina Statistiche si clicca sul bottone Annulla filtri
#    And Nella pagina Statistiche si inserisce una data corretta EndDate
#    And Nella pagina Statistiche si inserisce una data corretta StartDate
    And Attesa 5 secondi



    Examples:
      | inizioArcoTemporale | fineArcoTemporale |
      | 20/01/2024          | 20/01/2024        |