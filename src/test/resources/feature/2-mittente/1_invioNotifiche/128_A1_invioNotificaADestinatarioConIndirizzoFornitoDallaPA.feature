Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @TA_invioNotificaConIndirizzoFornitoDallaPA
  @mittente
  @invioNotifiche

  Scenario: PN-9644 - Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA
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
      | civico    | 1        |
      | localita  | 00100    |
      | comune    | Roma     |
      | provincia | Roma     |
      | cap       | 00100    |
      | stato     | RM       |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    Then Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente
#    And Si verifica che la notifica viene creata correttamente "datiNotifica"
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica "datiNotifica"
#    And Cliccare sul bottone Filtra
#    And Si verifica che la notifica sia nello stato avanzato
#   And Cliccare sulla notifica restituita
#    And Si verifica che l'invio della pec sia in corso
#    And Logout da portale mittente