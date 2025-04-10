Feature: PA invia notifica in lingua differente da quella scelte nelle impostazioni - Italiano Francese

  @TestSuite
  @TA_bilinguismoLinguaDifferenteDalleImpostazioni_ItalianoFrancese_QA5372
  @bilinguismo

  Scenario: PN-QA5372 - PA invia notifica in lingua differente da quella scelte nelle impostazioni - Italiano Francese
#    Pre Condizione Aver settato nelle impostazioni Italiano e Sloveno
    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente Comune di "Viggiu"
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente
    And Selezionare da impostazione lingua "Sloveno"

    And Logout e Login con Comune di "Viggiu"
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente


    And Selezionare da impostazione lingua "Francese"
#    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And verifica lingua selezionata "Francese"
    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Francese"
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
         #      TODO verificare VAS
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia
    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche













