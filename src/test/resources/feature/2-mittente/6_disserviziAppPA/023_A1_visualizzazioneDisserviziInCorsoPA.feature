Feature: Mittente visualizza i disservizi della applicazione in corso

  @TestSuite
  @mittente
  @TA_MittenteVisualizzazioneDisservizi
  @TA_PA_VisualizzaDisservizioInCorso

  Scenario: PN-9237 - Mittente visualizza i disservizi della applicazione in corso
    Given Creazione disservizio su portale helpdesk
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche selezionare la voce 'stato della piattaforma'
    Then Si visualizza correttamente la pagina dello 'stato della piattaforma' di mittente
    And Si visualizza correttamente la tabella dei disservizi
    And Si visualizza un record in elenco relativo ad un disservizio ancora in corso
    And Logout da portale mittente
    And Risoluzione disservizio su portale helpdesk

