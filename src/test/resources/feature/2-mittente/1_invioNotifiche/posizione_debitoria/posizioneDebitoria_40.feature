Feature: Mittente genera una notifica tramite destinatario con pec

  @TA_PosizioneDebitoria_40
  @TA_PosizioneDebitoria_OFF

  Scenario: [Posizione_Debitoria_40] - verifica dell'assenza di "Posizione debitoria" e "Dettagli posizione debitoria" nel form di creazione di una nuova notifica a flag spento
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisicaPec"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisicaPec" destinatario 0
    And Cliccare su continua
    And Si verifica l'assenza della sezione Posizione Debitoria
    And Si verifica l'assenza della sezione Dettaglio Posizione Debitoria
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    #  Invio notifica
    Then Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Verifica Assenza Pop-up Errore per Invia Posizione Debitoria
    And Nella section Allegati cliccare sul bottone Invia Posizione Debitoria
    And Attendi secondi "2"
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
    And Si verifica che la notifica viene creata correttamente "datiNotifica"
    And Verifica Esistenza Tabella Notifiche
    And Nella pagina Piattaforma Notifiche inserire il codice IUN della notifica
    And Cliccare sul bottone Filtra
    And Cliccare sulla notifica restituita dal filtro
    And Aspetta 1 secondi
    And Verifica Presenza Sezione Pagamenti 0
