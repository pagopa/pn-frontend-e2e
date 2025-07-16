Feature: Mittente invia una notifica digitale al destinatario con indirizzo fornito dalla PA

  @TestSuite
  @InvioNotificaACaricaDocumentoNonPdf
  @invioNotifiche_07
  @loginFE
  @NRT_Blocco_1
  Scenario: PN-9317-Check upload documento non pdf
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | Pagamento rata IMU PN-9317 |
      | descrizione       | PAGAMENTO RATA IMU PN-9317 |
      | gruppo            | test-TA-FE-TEST    |
      | codiceTassonomico | 100105P           |
      | modalitaInvio     | A/R                |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF               |
      | nomeCognomeDestinatario | Gaio Giulio      |
      | codiceFiscale           | CSRGGL44L13H501E |
    And Si aggiungi un domicilio digitale "test@fail.it"
    And Seleziona radion button Inserimento Manuale se esiste "0"

    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via Roma              |
      | civico    | 20                    |
      | localita  | Milano                |
      | comune    | Milano                |
      | provincia | MI                    |
      | cap       | 20147                 |
      | stato     | Italia                |
    And Cliccare su continua
    #      Aggiungere alle notifiche
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
#      Aggiungere alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati si carica un atto non pdf e visualizza messaggio di errore
#    And Logout da portale mittente

