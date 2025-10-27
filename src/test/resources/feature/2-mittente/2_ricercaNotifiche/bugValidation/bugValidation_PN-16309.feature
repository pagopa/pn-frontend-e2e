Feature: Mittente effetua una ricerca notifiche per Data errata

  @bugValidation_PN_16309
  @ricercaNotificheMittente_1
  @mittente

  Scenario: PN-16309 - Ricerca notifiche con nuovo gruppo vanno in timeout
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente
      | user   | m.curie |
      | pwd    | test    |
      | comune | Palermo |
    And Click entra su Send Mittente
    And Si clicca bottone accetta cookies
    #Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nel campo Data inizio si inserisce una data "11/02/2025"
    And Nel campo Data fine si inserisce una data "21/08/2025"
    And Si controlla che data di inizio e data di fine abbiano testo per helper text "intervallo massimo consentito è di 6 mesi"
    And Nel campo Data inizio si inserisce una data "11/07/2025"
    And Nel campo Data fine si inserisce una data "21/08/2025"
    And Cliccare sul bottone Filtra
    And  Aspetta 10 secondi
    And Si verifica che non ci sono notifiche disponibili
    And Refresh pagina
    And Verifica Pop-up toast di errore "Nessun risultato"
    And Verifica Messaggio toast di errore "Non abbiamo trovato risultati prova con filtri diversi"
    And Nella pagina Piattaforma Notifiche si controlla il testo per errore su notifiche SEND "Non siamo riusciti a recuperare questi dati."

    #PF
    Given Login Page persona fisica test viene visualizzata
    Given Login con persona fisica
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
    And Nel campo Data inizio si inserisce una data "11/02/2021"
    And Nel campo Data fine si inserisce una data "21/03/2021"
    And Cliccare sul bottone Filtra persona fisica
    And Si verifica che non ci sono notifiche disponibili

    #PG
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    Then Home page persona giuridica viene visualizzata correttamente
    And Click entra su Send Persona Giuridica
    And Nel campo Data inizio si inserisce una data "11/02/2021"
    And Nel campo Data fine si inserisce una data "21/03/2021"
    And Cliccare sul bottone Filtra persona giuridica
    And Si verifica che non ci sono notifiche disponibili