Feature: il mittente fa una ricerca combinata tra stato e arco temporale  con nessun risultato

  @TA_PN-16104_errata_visulaizzione_tost_errore_dopo_cancella_filtri_PA
    @NRT_VALIDATION
  Scenario Outline: PN-16104 errata_visulaizzione_tost_errore_dopo_cancella_filtri_PA
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
#  Utilizzare per AWS
    And Nella pagina Statistiche inserire una data da <inizioArcoTemporale> a <fineArcoTemporale>

    #  Utilizzare In LOCALE
#    And Nella pagina Statistiche si inserisce una data corretta EndDate
#    And Nella pagina Statistiche si inserisce una data corretta StartDate

    And Nella pagina Statistiche si clicca sul bottone Annulla filtri

    And Verifica Pop-up toast di errore "errore"
    And Verifica Messaggio toast di errore "informazioni errore"
    And Verifica Codice toast di errore "ERROR_CODE_BFF_INVALIDDATERANGE"
    And Copia TraceID toast di errore
    And Si chiude toast di errore
    And Click Bottone "Ricarica"
    And Verifica Pop-up toast di errore

    Examples:
      | inizioArcoTemporale | fineArcoTemporale |
      | 20/01/2024          | 20/01/2024        |