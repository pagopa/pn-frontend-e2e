Feature: il mittente fa una ricerca combinata tra stato e arco temporale  con nessun risultato

  @TestSuite
    @mittente
    @ricercaNatoficheMittente
    @TA_MittenteRicercaSenaRisultatoPerStatoPeriodo

  Scenario Outline: PN-9325 - il mittente fa una ricerca sia per arco temporale che per stato con nessun risultato
    Given PA - Si effettua la login tramite token exchange di "mittente" e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche inserire una data da <inizioArcoTemporale> a <fineArcoTemporale>
    And Nella pagina piattaforma Notifiche selezionare uno stato notifica <stato>
    And Cliccare sul bottone Filtra
    And Il sistema non restituisce notifiche
    And Logout da portale mittente
    Examples:
      | inizioArcoTemporale | fineArcoTemporale | stato     |
      | 20/01/2024          | 20/01/2024        | Annullata |