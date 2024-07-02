Feature: Mittente visualizza correttamente la pagina notifiche

  @TestSuite
  @TA_MittentevisualizzazioneNotifiche
  @mittente

  @visualizzazioneNotificheMittente
  Scenario: [TA-FE VISUALIZZAZIONE NOTIFICA] - Mittente visualizza correttamente la pagina notifiche
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche visualizzano correttamente i filtri di ricerca
    Then Nella pagina Piattaforma Notifiche si visualizza correttamente l'elenco delle notifiche
    And Logout da portale mittente

  @visualizzazioneNotificheMittente
  Scenario: [TA-FE VISUALIZZAZIONE NOTIFICA DOPO INVIO PG] - Invio notifica a PA con successiva visualizzazione lato PG
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Creazione notifica completa
      | oggettoDellaNotifica  | Pagamento rata IMU |
      | descrizione           | PAGAMENTO RATA IMU |
      | modello               | AR                 |
      | gruppoTest            | test-TA-FE-TEST    |
      | gruppoDev             | GruppoTest         |
      | codiceTassonometrico  | 123456A            |
      | nomeFileYaml          | datiNotifica       |
      | destinatario          | PG                 |
      | codiceFiscale         | 01199250158        |
      | ragioneSociale        | Comune di Milano   |
      | indirizzo             | via Roma           |
      | numeroCivico          | 20                 |
      | comune                | MILANO             |
      | provincia             | MI                 |
      | codicepostale         | 20147              |
      | stato                 | ITALIA             |
      | nomeDocumentoNotifica | RATA SCADUTA IMU   |
    And Si verifica che la notifica è stata creata correttamente
    And Logout da portale mittente
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | baldassarremazza |
      | pwd            | test             |
      | ragioneSociale | Comune di Milano |
    Then Home page persona giuridica viene visualizzata correttamente
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si seleziona la notifica
    And Logout da portale persona giuridica




