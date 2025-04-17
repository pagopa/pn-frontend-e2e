Feature: Mittente genera una notifica tramite destinatario con pec

  @TestSuite
  @TA_PosizioneDebitoria_01
  @NRT

  Scenario: Posizione_Debitoria_01 - verifica della presenza di "Posizione debitoria" e "Dettagli posizione debitoria" nel form di creazione di una nuova notifica
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisicaPec"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisicaPec" destinatario 0
    And Cliccare su continua
    And Si verifica la presenza della sezione Posizione Debitoria
    And Seleziona Avviso PagoPA 1
    And Cliccare su continua
    And Si verifica la presenza della sezione Dettaglio Posizione Debitoria