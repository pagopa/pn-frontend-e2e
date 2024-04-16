Feature: Mittente invia una notifica e quando passa allo stato depositata viene annullata

  @TestSuite
  @TA_invioNotificaStatoDepositataEAnnullamento
  @mittente
  @invioNotifiche

  Scenario: PN-10236 - Mittente invia una notifica e quando passa allo stato depositata viene annullata
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU |
      | descrizione       | PAGAMENTO RATA IMU |
      | gruppo            | test-TA-FE-TEST    |
      | codiceTassonomico | 123456A            |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio      |
      | codiceFiscale           | CSRGGL44L13H501E |
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma |
      | civico    | 20       |
      | localita  | Milano   |
      | comune    | Milano   |
      | provincia | MI       |
      | cap       | 20147    |
      | stato     | Italia   |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
    And Aspetta 180 secondi
    And Cliccare sulla notifica restituita
    Then Si verifica che la notifica abbia lo stato "Depositata"
    And Si annulla la notifica
    Then Si verifica che la notifica abbia lo stato "Annullata"
    #And manca check per il pop up di conferma eliminazione?
    And Il bottone annulla notifica non è visualizzabile nella descrizione della notifica
    And Nella pagina Piattaforma Notifiche la notifica presenta lo stato "Annullata"

    And Logout da portale mittente
