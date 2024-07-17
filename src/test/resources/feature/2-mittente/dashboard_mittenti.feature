Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @DashboardMittenti

  @MittenteVisualizzaDashboardStatistiche
  Scenario: PN-12036
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    Then Si visualizza correttamente la pagina di Statistiche
    And Logout da portale mittente


  @12037
  Scenario: PN-12037
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    And Nella pagina Statistiche si visualizza il grafico
    And Nella pagina Statistiche si clicca il bottone esporta jpeg
    And Si elimina jpeg scaricato
    And Logout da portale mittente


  @12039
  Scenario: PN-12039
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    And Nella pagina Statistiche si visualizza il grafico
    And Nella pagina Statistiche si inserisce una data errata
    And Nella pagina Statistiche il bottone Filtra disabilitata
    And Nella pagina Statistiche si inserisce una data corretta
    And Nella pagina Statistiche si clicca sul bottone Filtra
    And Nella pagina Statistiche si visualizza il grafico
    Then Nella pagina Statistiche si clicca sul bottone Annulla filtri
    And Logout da portale mittente


  @12040
  Scenario: PN-12040
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    And Nella pagina Statistiche si visualizza il grafico
    And Si verifica che tipo di grafico è "Aggregato"
    And Si cambia tipo di grafico
    And Nella pagina Statistiche si visualizza il grafico
    And Si sceglie opzione Giorni
    Then Nella pagina Statistiche si visualizza il grafico
    And Logout da portale mittente


  @12041
  Scenario: PN-12041
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico Notifiche inviate per stato
    And Logout da portale mittente


  @12042
  Scenario: PN-12042
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    And Nella pagina Statistiche si visualizza il grafico Notifiche consegnate per modalità di invio
    And Si verifica che tipo di grafico per modalita di invio è "Aggregato"
    And Si cambia tipo di grafico per modalita di invio
    And Si sceglie opzione Giorni
    Then Nella pagina Statistiche si visualizza il grafico Notifiche consegnate per modalità di invio
    And Logout da portale mittente


  @12043
  Scenario: PN-12043
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche Notifiche digitali cliccare sul bottone ultimi 6 mesi
    And Nella pagina Statistiche si visualizza il grafico Invii digitali per esito
    And Nella pagina Statistiche Notifiche digitali si inserisce una data errata
    And Nella pagina Statistiche il bottone Filtra disabilitata
    And Nella pagina Statistiche Notifiche digitali si inserisce una data corretta
    And Nella pagina Statistiche Notifiche digitali si clicca sul bottone Filtra
    And Nella pagina Statistiche si visualizza il grafico Invii digitali per esito
    Then Nella pagina Statistiche Notifiche digitali si clicca sul bottone Annulla filtri
    And Logout da portale mittente


  @12044
  Scenario: PN-12044
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche Notifiche digitali cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico Invii digitali per esito
    And Logout da portale mittente

  @12045
  Scenario: PN-12045
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche Notifiche digitali cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico Tempo medio degli invii digitali
    And Logout da portale mittente


  @12046
  Scenario: PN-12046
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche Notifiche digitali cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico Errori tecnici per tipologia
    And Logout da portale mittente



  @12047
  Scenario: PN-12047
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico
    And Logout da portale mittente
