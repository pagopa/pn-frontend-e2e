Feature: Avviso PagoPa a carico sincrona con iva + importo Piu codici Avvisi per uno stesso destinatario

  @TestSuite
  @TA_PosizioneDebitoria_Validation_Bug_5967
  @TA_PosizioneDebitoria_ON
  @NRT
  Scenario: [Posizione_Debitoria_Validation_Bug_5967] - 
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
    
#    And Seleziona Incluso Nell Atto 1
#    And Seleziona A Carico del Destinatario 1
#    And Inserire IVA
#    And Inserire Costo di notifica
    And Seleziona Modo Asincrono 1
##  Posizione debitoria di xxxx
    And Inserire Tutti Codice Avviso
    And Verifica campo precompilato creditorTaxId
    And Inserire Tutti Codice Fiscale Ente
    #  PosizioneDebitoria_10
    And Carica Singolo File PDF Posizione Debitoria Numero Notifiche Pari a 1
    And Selezionare Applica Costo di notifica





    And Cliccare su continua
    And Attendi secondi "10"

