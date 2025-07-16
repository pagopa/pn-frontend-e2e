Feature: Invio notifica con codice tassonomico non censito

  @TestSuite
  @TA_codiceTassonomicoCensito
  @bilinguismo
  @NRT_Blocco_1

  Scenario: PN-5260-Codice Tassonomico Censito
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard

    And Selezionare da impostazione lingua la lingua "Italiano"
    And Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento con nuovi codiceTassonomici "100105P"
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
#    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua
#    #      Aggiungere alle notifiche
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua
##      Aggiungere alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
