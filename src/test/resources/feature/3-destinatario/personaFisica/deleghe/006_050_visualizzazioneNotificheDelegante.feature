Feature: Il delegato visualizza la notifiche del delegante
  
  @TestSuite
  @TA_PFvisualizzaNotificheDelegante
  @DeleghePF
  @PF

  Scenario: PN-9419 - Il delegato visualizza la notifiche del delegante
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Aspetta 10 secondi
    And Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Nella pagina Piattaforma Notifiche persona fisica si vede la sezione Deleghe
    And Si controlla che non sia presente una delega con stesso nome
      | nome    | Lucrezia |
      | cognome | Borgia   |
    And Aspetta 10 secondi
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
    And Si visualizza correttamente la pagina nuova delega
    And Nella sezione Le Tue Deleghe inserire i dati
      | nome          | Lucrezia          |
      | cognome       | Borgia            |
      | codiceFiscale | BRGLRZ80D58H501Q  |
      | ente          | Comune di Verona |
    And Nella sezione Le Tue Deleghe verificare che la data sia corretta
    And Nella sezione Le Tue Deleghe salvare il codice verifica all'interno del file "nuova_delega"
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta e sul bottone torna alle deleghe

    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona fisica click sul bottone Deleghe
    And Si sceglie opzione accetta
    And Si inserisce il codice delega nel pop-up "nuova_delega"
    And Si clicca sul bottone Accetta
    And Si controlla che la delega ha lo stato Attiva
      | firstName | Gaio Giulio |
      | lastName  | Cesare      |

    And PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Si inizializzano i dati per la notifica
      | modello         | A/R                |
      | documenti       | 1                  |
      | oggettoNotifica | Pagamento rata IMU |
      | costiNotifica   | false              |
    And Si aggiunge un destinatario alla notifica
      | indirizzo        | VIA ROMA           |
      | codicePostale    | 20147              |
      | comune           | Milano             |
      | dettagliComune   | Milano             |
      | provincia        | MI                 |
      | stato            | Italia             |
      | nomeCognome      | Gaio Giulio Cesare |
      | codiceFiscale    | CSRGGL44L13H501E   |
      | tipoDestinatario | PF                 |
      | avvisoPagoPa     | 1                  |
    And Creo in background una notifica per destinatario tramite API REST
    And Aspetta 10 secondi
    And Si seleziona la notifica

    And PF - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche persona fisica si clicca sulle notifiche di "(gaio giulio cesare)"
    And Si seleziona la notifica
    And Logout da portale persona fisica