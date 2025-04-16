Feature: Mittente genera una notifica tramite destinatario con pec

  @TestSuite
  @TA_PosizioneDebitoria_04
  @NRT

  Scenario: Posizione_Debitoria_04 - Avviso PagoPa” come tipo di pagamento, venga mostrata la sezione relativa all’inserimento delle informazioni dell’avviso di pagamento - solo un avviso PagoPa - forfettario e sincrona
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Cliccare su continua
#    And Seleziona Modello F24
#    And Cliccare su continua
#    And Seleziona Incluso Nell Atto
#    And Carica Json con Costi Posizione Debitoria Numero Notifiche Pari a 1
#    And Inserisci Titolo Documento Posizione Debitoria 1
#
#    And Click Su Aggiungi Altro Modello F24
#
#    And Carica Json con Costi Posizione Debitoria Numero Notifiche Pari a 2
#    And Inserisci Titolo Documento Posizione Debitoria 2
#    And Cliccare su continua
#
#    And Carica File Posizione Debitoria Numero Notifiche Pari a 1
#    And Inserisci Titolo Documento Documenti Allegati 1
#
#    And Click Su Aggiungi un altro documento
#
#    And Carica File Posizione Debitoria Numero Notifiche Pari a 2
#    And Inserisci Titolo Documento Documenti Allegati 2


#    And Seleziona Avviso PagoPA
#    And Cliccare su continua
#    And Seleziona A Carico del Destinataio
#    And Inserire Costo di notifica
#    And Inserire IVA
#    And Seleziona Modo Sincrono
#    And Inserire Codice Avviso
#    And Inserire Codice Fiscale Ente
#    And Carica File Posizione Debitoria Numero Notifiche Pari a 1
#    And Seleziona Applica Costo Notifica
#    And Cliccare su continua
#
    And Attendi secondi "10"



    
    
#    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
#    And Nella section Allegati cliccare sul bottone Invia
  
#  TODO DO Da verificare
  
##    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
#    And Cliccare sul bottone vai alle notifiche
#    And Si visualizza correttamente la pagina Piattaforma Notifiche
#    And Si verifica che la notifica viene creata correttamente "datiNotifica"
#    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
#    And Cliccare sul bottone Filtra
#    And Si verifica che la notifica sia nello stato avanzato
#    And Cliccare sulla notifica restituita
##    And Si verifica che l'invio della pec sia in corso
###    And Logout da portale mittente