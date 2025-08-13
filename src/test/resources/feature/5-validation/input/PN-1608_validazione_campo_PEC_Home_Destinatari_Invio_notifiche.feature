Feature: Valutazione label campo input  pec Invio notifiche

#  @TestSuite
  @TA_PN-1608_valutazione_label_campo_input_pec_Invio_notifiche_PA
  @NRT_VALIDATION
  Scenario: [PN-1608 valutazione label campo input  pec Invio notifiche] - .
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
#    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento "Posizione_Debitoria_02"
    And Cliccare su continua
    And Inserisci Max Caratteri Input pec portale PA 322
    And Verifica errore label pec "maxLength"






