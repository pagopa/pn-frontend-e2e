Feature: invio notifica con sequence

  @Tag_PN_14806_13_OK-CompiutaGiacenza_890
  @Tag_CAD_ARCAD
  @NRT_Blocco_2

  Scenario: [PN-14806_13] CAD/ARCAD SEQUENCE OK-CompiutaGiacenza_890
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    Then Nella section Informazioni preliminari si inseriscono i dati della notifica
      | oggettoNotifica   | OK-CompiutaGiacenza_890 |
      | descrizione       | OK-CompiutaGiacenza_890 |
      | gruppo            | test-TA-FE              |
      | codiceTassonomico | 100105P                 |
      | modalitaInvio     | 890                     |
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    Then Nella section Destinatario si inseriscono i dati del destinatario
      | soggettoGiuridico       | PF                |
      | nomeCognomeDestinatario | Amedeo Modigliani |
      | codiceFiscale           | MDGMDA80T25F205W  |
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinitario si clicca su "Aggiungi un indirizzo fisico" e si inseriscono i dati
      | indirizzo | Via @OK-CompiutaGiacenza_890 |
      | civico    | 20                           |
      | localita  | Milano                       |
      | comune    | Milano                       |
      | provincia | MI                           |
      | cap       | 20147                        |
      | stato     | Italia                       |
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati si carica un atto
    And Nella section Allegati cliccare sul bottone Invia
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    #And Si ottiene il codice IUN dalla notifica creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica è stata creata correttamente

    #And Aspetta 10 secondi
    #And Aspetta la notifica con IUN salvato
#    And Aspetta 10 secondi
    And Attesa 15 secondi
    And Refresh pagina
    And Si seleziona la notifica mittente
    And Si attende completamento notifica "Consegnata"
#    And Aspetta 300 secondi
    And Attesa 300 secondi
    And Refresh pagina
    And Si controlla lo stato timeline in dettaglio notifica
      | xpathStato   | //*[contains(text(), 'della raccomandata che contiene la comunicazione di avvenuto deposito')] |
      | vediDettagli | false                                                                                          |