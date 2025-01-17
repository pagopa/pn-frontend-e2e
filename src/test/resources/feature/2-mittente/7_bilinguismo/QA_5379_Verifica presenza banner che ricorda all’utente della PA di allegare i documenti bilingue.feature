Feature: PA sceglie la lingua delle sue notifiche dalla sezione Impostazioni - Italiano

#  @TestSuite
  @TA_VerificaPresenzaBannerAllegareDocumentiBilingue

  Scenario: PN-QA5379 - Verifica presenza banner che ricorda all’utente della PA di allegare i documenti bilingue
#    Pre Condizione Aver effettuato l’accesso al portale SEND e aver scelto allo step 1 di inviare una notifica bilingue

    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |
    When Login con mittente Comune di "Viggiu"
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
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua
    And Verifica Banner "Hai scelto di inviare la notifica in più lingue"



#
#    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
#    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
#    And Nella section Allegati cliccare sul bottone Invia
#    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
#    And Cliccare sul bottone vai alle notifiche
#    And Si visualizza correttamente la pagina Piattaforma Notifiche
###    And Si verifica che la notifica viene creata correttamente "datiNotifica"
#    And Logout e Login con Comune di "Viggiu"
#    And Si clicca sul bottone test
#    And Si clicca bottone accetta cookies
#    And Home page mittente viene visualizzata correttamente
#    And selezione impostazione lingua
#    And verifica lingua selezionata "Francese"



#    Given Login Page mittente viene visualizzata
#      | url | https://selfcare.test.notifichedigitali.it |
#    When Login con mittente Comune di "Viggiu"
#    And Si clicca sul bottone test
#    And Si clicca bottone accetta cookies
#    And Home page mittente viene visualizzata correttamente
#    And Selezionare da impostazione lingua "Italiano"
#
#    And Logout e Login con Comune di "Viggiu"
#    And Si clicca sul bottone test
#    And Si clicca bottone accetta cookies
#    And Home page mittente viene visualizzata correttamente
#
#
#    And Selezionare da impostazione lingua "Tedesco"
##    And Nella pagina Piattaforma Notifiche si recupera l ultimo numero protocollo
#    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
#    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
#    And verifica lingua selezionata "Tedesco"
#    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Tedesco"
#    And Cliccare su continua
#    And Si visualizza correttamente la pagina Piattaforma Notifiche section Destinatario
#    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
#    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
#    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
#    And Cliccare su continua
#    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
#    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
#    And Nella section Allegati cliccare sul bottone Invia
#    Then Si visualizza correttamente la frase La notifica è stata correttamente creata
#    And Cliccare sul bottone vai alle notifiche
#    And Si visualizza correttamente la pagina Piattaforma Notifiche













