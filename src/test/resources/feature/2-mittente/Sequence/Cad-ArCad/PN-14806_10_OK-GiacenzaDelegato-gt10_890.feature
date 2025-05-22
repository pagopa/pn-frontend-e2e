Feature: invio notifica con sequence

  @Tag_PN_14806_10_OK-GiacenzaDelegato-gt10_890
  @TestSuite
  @Tag_CARD_ARCAD
  @NRT

  Scenario: [PN-14806_10] CAD/ARCAD SEQUENCE OK-GiacenzaDelegato-gt10_890
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | OK-GiacenzaDelegato-gt10_890 |
      | descrizione       | OK-GiacenzaDelegato-gt10_890 |
      | gruppo            | test-TA-FE                   |
      | codiceTassonomico | 100105P                      |
      | modalitaInvio     | 890                          |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF                |
      | nomeCognomeDestinatario | Amedeo Modigliani |
      | codiceFiscale           | MDGMDA80T25F205W  |
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via @OK-GiacenzaDelegato-gt10_890 |
      | civico    | 20                               |
      | localita  | Milano                           |
      | comune    | Milano                           |
      | provincia | MI                               |
      | cap       | 20147                            |
      | stato     | Italia                           |
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente

    And Aspetta 10 secondi
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Consegnata"
    And Aspetta 300 secondi
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //*[contains(text(), 'avviso di ricevimento della raccomandata che contiene la comunicazione di avvenuto deposito')] |
      | vediDettagli | false