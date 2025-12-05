Feature: Mittente genera una notifica tramite destinatario con pec

  @TestSuite
  @TA_PosizioneDebitoria_02
  @TA_PosizioneDebitoria_ON
  @NRT_Blocco_1
  @NRT_Q4B
  Scenario: [Posizione_Debitoria_02] - l’utente sceglie di non inserire pagamenti durante l’invio notifica, e si viene rediretti allo step di inserimento degli allegati della notifica.
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
#    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "Posizione_Debitoria_02"
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisicaPec"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisicaPec" destinatario 0
    And Cliccare su continua
    When Si verifica la presenza della sezione Posizione Debitoria
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
     ## Documenti allegati
    Then Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Verifica Assenza Pop-up Errore per Invia Posizione Debitoria
    And Nella section Allegati cliccare sul bottone Invia Posizione Debitoria
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Verifica Esistenza Tabella Notifiche
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra Notifica "filter-button"
    And Cliccare sulla notifica restituita dal filtro
    #And Aspetta 1 secondi
    And Refresh pagina
    And Verifica Presenza Sezione Pagamenti 0

