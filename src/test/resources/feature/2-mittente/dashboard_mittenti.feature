Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @DashboardMittenti

  @MittenteVisualizzaDashboardStatistiche
  Scenario: PN-12036 - Dashboard mittenti - Visualizzazione statistiche relative a una determinata PA
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    Then Si visualizza correttamente la pagina di Statistiche
    And Logout da portale mittente


  @MittenteVerificaEsportaGrafici
  Scenario: PN-12037 - Dashboard mittenti - Verifica funzione esporta grafici
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    And Nella pagina Statistiche si visualizza il grafico
    And Nella pagina Statistiche si clicca il bottone esporta jpeg
    And Si elimina jpeg scaricato
    And Logout da portale mittente


  @ApplicareFiltriDisponibiliVisulizzaGrafici
  Scenario: PN-12039 - Dashboard mittenti - Applicare filtri disponibili e visualizzare i grafici relativi ai dati filtrati
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


  @VisualizzazioneStatisticheRelativePA
  Scenario: PN-12040 - Dashboard mittenti - Visualizzazione delle statistiche relative alle Notifiche depositate dalla PA
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


  @VisualizzazioneGraficoDelleStatistiche
  Scenario: PN-12041 - Dashboard mittenti - Visualizzazione grafico delle statistiche relative all'ultimo stato raggiunto dalle notifiche depositate
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico Notifiche inviate per stato
    And Logout da portale mittente


  @VisualizzazioneGraficoModalitaInvio
  Scenario: PN-12042 - Dashboard mittenti - Visualizzazione grafico relativo alle Notifiche per modalità di invio
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


  @VisualizzareGraficiRelativiDatiFiltrati
  Scenario: PN-12043 - Dashboard mittenti - Notifiche digitali - Applicare filtri disponibili e visualizzare i grafici relativi ai dati filtrati
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


  @VisualizzazioneGraficoPerEsitoInvio
  Scenario: PN-12044 - Dashboard mittenti - Visualizzazione grafico relativo alle Notifiche digitali per esito di invio
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche Notifiche digitali cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico Invii digitali per esito
    And Logout da portale mittente

  @VisualizzazioneGraficoTempoMedioInviiDigitali
  Scenario: PN-12045 - Dashboard mittenti - Visualizzazione grafico relativo al Tempo medio degli invii digitali
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche Notifiche digitali cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico Tempo medio degli invii digitali
    And Logout da portale mittente


  @VisualizzazioneGraficoErroriTecnici
  Scenario: PN-12046 - Dashboard mittenti - Visualizzazione grafico relativo agli Errori tecnici per tipologia
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Si visualizza correttamente la pagina di Statistiche
    And Nella pagina Statistiche Notifiche digitali cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico Errori tecnici per tipologia
    And Logout da portale mittente



  @ApplicazioneFiltroGraficiElevataDati
  Scenario: PN-12047 - Dashboard mittenti - Applicazione filtro grafici con PA che restituisce una quantità elevata di dati
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Statistiche
    And Nella pagina Statistiche cliccare sul bottone ultimi 6 mesi
    Then Nella pagina Statistiche si visualizza il grafico
    And Logout da portale mittente
