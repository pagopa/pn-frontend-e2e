Feature: Avviso PagoPa a carico sincrona con iva + importo Piu codici Avvisi per uno stesso destinatario

  @TestSuite
  @TA_PosizioneDebitoria_06_07_09_10
  @NRT_TA_PosizioneDebitoria
  @NRT
  Scenario: [Posizione_Debitoria_06_07_09_10] - Avviso PagoPa a carico sincrona con iva + importo Piu codici Avvisi per uno stesso destinatario
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Cliccare su continua
#    Posizione Debitoria
    And Seleziona Avviso PagoPA 1
    And Cliccare su continua
## Step Dettaglio posizione debitoria
    And Seleziona A Carico del Destinatario 1
    And Inserire IVA
    And Inserire Costo di notifica
    And Seleziona Incluso Nell Atto 1
    And Seleziona A Carico del Destinatario 1
    And Inserire IVA
    And Inserire Costo di notifica
    And Seleziona Modo Sincrono 1
##  Posizione debitoria di xxxx
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
#  PosizioneDebitoria_09
    And Nella section Allegati si carica un atto non pdf e visualizza messaggio di errore
    #  PosizioneDebitoria_10
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Click Su Aggiungi Codice Di Avviso PagoPa 0
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 2
    And Click Su Elimina Avviso pagoPA
    And Click Su Aggiungi Codice Di Avviso PagoPa 0
    And Inserire Tutti Codice Avviso
    And Inserire Tutti Codice Fiscale Ente
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 2
    And Seleziona Applica Costo Notifica
    And Verifica Numero Caricamento file 2
    And Cliccare su continua
 ## Documenti allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Verifica Assenza Pop-up Errore per Invia Posizione Debitoria
    And Nella section Allegati cliccare sul bottone Invia Posizione Debitoria
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Verifica Esistenza Tabella Notifiche
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra Notifica "filter-button"
    And Cliccare sulla notifica restituita dal filtro
    And Verifica Sezione Pagamenti
    
