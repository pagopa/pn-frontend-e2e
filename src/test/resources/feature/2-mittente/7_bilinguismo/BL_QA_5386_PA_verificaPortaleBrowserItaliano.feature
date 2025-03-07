Feature: PA Verificare portale browser Italiano

  @TestSuite
  @TA_bilinguismoVerificaPortaleBrowserItaliano_QA5386
  @TA_Italiano
  @bilinguismo

  Scenario: PN-QA5387 - PA - Verificare portale browser Italiano

    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |

    When Login con mittente Comune di "Viggiu"
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente

#   Verificole traduzioni del portale
    And Verifica traduzione testo "Notifiche"
    And Verifica traduzione testo "Statistiche"
    And Verifica traduzione testo "Stato della piattaforma"
    And Verifica traduzione testo "Utenti"
    And Verifica traduzione testo "Gruppi"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Data"
    And Verifica traduzione testo "Destinatario"
    And Verifica traduzione testo "Oggetto"
    And Verifica traduzione testo "Gruppi"
    And Verifica traduzione testo "Qui trovi tutte le notifiche inviate dall"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Mittente"
    And Verifica traduzione testo "Codice Fiscale destinatario"
    And Verifica traduzione testo "Data di invio"
    And Verifica traduzione testo "Documenti allegati"
    And Verifica traduzione testo "Avviso di avvenuta ricezione"
#    And Verifica traduzione testo "DISRUPTIONS"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#    Invio Notifica
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
#    Traduzione prima pagina notifica
    And Verifica traduzione testo "Invia una nuova notifica"
    And Verifica traduzione testo "Al momento le notifiche che prevedono un pagamento si possono inviare solo tramite API Key"
    And Verifica traduzione testo "Informazioni preliminari"
    And Verifica traduzione testo "Oggetto della notifica"
    And Verifica traduzione testo "Raccomandata A/R"

    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Italiano"
    And Cliccare su continua
    #    Traduzione seconda pagina notifica
    And Verifica traduzione testo "Destinatari"
    And Verifica traduzione testo "Campi obbligatori"
    And Verifica traduzione testo "Persona fisica"
    And Verifica traduzione testo "Soggetto giuridico"
    And Verifica traduzione testo "Indirizzo"
    And Verifica traduzione testo "CAP"
    And Verifica traduzione testo "Comune"
    And Verifica traduzione testo "Provincia"
    And Verifica traduzione testo "Numero civico"

    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua

#    Traduzione terza pagina notifica
    And Verifica traduzione testo "Documenti allegati"
    And Verifica traduzione testo "Allega documento"
    And Verifica traduzione testo "carica il file"
    And Verifica traduzione testo "Titolo del documento"
    And Verifica traduzione testo "Torna a Destinatario"
    And Verifica traduzione testo "Invia"
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia

    #    Traduzione quarta  pagina notifica
    And Verifica traduzione testo "La notifica è stata creata"
    And Verifica traduzione testo "Trovi gli aggiornamenti sul suo stato nella sezione"
    And Verifica traduzione testo "Vai alle Notifiche"
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche

#  Raggiungere la sezione API Key e verificarne le traduzioni
    When Seleziona voce menu laterale "API Key"
    And Verifica traduzione testo "API Key"
    And Verifica traduzione testo "API Key generate"
    And Verifica traduzione testo "Genera API Key"
    And Verifica traduzione testo "Nome"
    And Verifica traduzione testo "Ultima modifica"

#    IMPLEMENTAZIONE CREAZIONE DELETE .... apikey
    When Click Genera Api Key
#    verifica traduzione Genera Api Kei
    And Verifica traduzione testo "Altre informazioni"
    And Verifica traduzione testo "Dai un nome alla tua API Key"
    And Verifica traduzione testo "Scegli i gruppi a cui assegnare l’API Key"
    And Verifica traduzione testo "Inserisci un nome"

    And Inserisci nome Api Key
    And Verifica traduzione testo "API Key generata con successo"
    And Verifica traduzione testo "integrazione, copia il codice e inseriscilo nella piattaforma proprietaria"
    And Verifica traduzione testo "Torna a API Key"

    And Torna a Api Key
    And Premere tre puntini
    And Seleziona Ruota

    And Verifica traduzione testo "Ruota API Key"
    And Verifica traduzione testo "Dopo che hai inserito la nuova API Key nella piattaforma"

    And Click Ruota
    And Premere tre puntini
    And Seleziona Blocca

    And Verifica traduzione testo "Blocca API Key"
    And Verifica traduzione testo "Puoi attivarla di nuovo in qualsiasi momento"
    And Click Blocca

    And Premere tre puntini
    And Seleziona Elimina

    And Verifica traduzione testo "Elimina API Key"
    And Verifica traduzione testo "Se elimini definitivamente"
    And Click Delete

#  Raggiungere la sezione Statistiche e verificarne le traduzioni

    When Seleziona voce menu laterale "Statistiche"
    And Verifica traduzione testo "Panoramica delle notifiche"
    And Verifica traduzione testo "Esporta JPEG"
    And Verifica traduzione testo "intervallo di rilevazione"

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "Stato della piattaforma"
    And Verifica traduzione testo "Verifica il funzionamento di SEND"
#    And Verifica traduzione testo "All SEND services are operational"
    And Verifica traduzione testo "Storico dei disservizi"

    When Seleziona voce menu laterale "Notifiche"

#    PARTE INGLESE
    And Cambia lingua footer "Inglese"
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Statistics"
    And Verifica traduzione testo "Platform status"
    And Verifica traduzione testo "Users"
    And Verifica traduzione testo "Groups"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Date"
    And Verifica traduzione testo "Recipient"
    And Verifica traduzione testo "Subject"
    And Verifica traduzione testo "Groups"
    And Verifica traduzione testo "Status"
    And Verifica traduzione testo "Here, you will find all the notifications sent by the institution"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Sender"
    And Verifica traduzione testo "Recipient Fiscal Code"
    And Verifica traduzione testo "Date sent"
    And Verifica traduzione testo "Attached documents"
    And Verifica traduzione testo "Notification of receipt"
#    And Verifica traduzione testo "DISRUPTIONS"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#    Invio Notifica
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
#    Traduzione prima pagina notifica
    And Verifica traduzione testo "Send a new notification"
    And Verifica traduzione testo "Notifications involving a payment can only be sent"
    And Verifica traduzione testo "Preliminary information"
    And Verifica traduzione testo "Subject of the notification"
    And Verifica traduzione testo "Registered letter with acknowledgment of receipt"

    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Italiano"
    And Cliccare su continua
    #    Traduzione seconda pagina notifica
    And Verifica traduzione testo "Recipients"
    And Verifica traduzione testo "Mandatory fields"
    And Verifica traduzione testo "Physical person"
    And Verifica traduzione testo "Legal person"
    And Verifica traduzione testo "Address"
    And Verifica traduzione testo "Postcode"
    And Verifica traduzione testo "Municipality"
    And Verifica traduzione testo "Province"
    And Verifica traduzione testo "Street no"

    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua

#    Traduzione terza pagina notifica
    And Verifica traduzione testo "Attachments"
    And Verifica traduzione testo "Attach the deed"
    And Verifica traduzione testo "upload the file"
    And Verifica traduzione testo "Document name"
    And Verifica traduzione testo "Back to Recipient"
    And Verifica traduzione testo "Send"
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia

    #    Traduzione quarta  pagina notifica
    And Verifica traduzione testo "Notification has been created"
    And Verifica traduzione testo "You can find updates on its status in the"
    And Verifica traduzione testo "Go to Notifications"
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche

#  Raggiungere la sezione API Key e verificarne le traduzioni
    When Seleziona voce menu laterale "API Key"
    And Verifica traduzione testo "API Key"
    And Verifica traduzione testo "API Keys generated"
    And Verifica traduzione testo "Generate API Key"
    And Verifica traduzione testo "First name"
    And Verifica traduzione testo "Last modified"

#    IMPLEMENTAZIONE CREAZIONE DELETE .... apikey
    When Click Genera Api Key
#    verifica traduzione Genera Api Kei
    And Verifica traduzione testo "Other information"
    And Verifica traduzione testo "Name your API Key"
    And Verifica traduzione testo "Choose the groups to which to assign the API Key"
    And Verifica traduzione testo "Enter your first name"

    And Inserisci nome Api Key
    And Verifica traduzione testo "API Key successfully generated!"
    And Verifica traduzione testo "To allow integration, copy the code and enter"
    And Verifica traduzione testo "Back to API Key"

    And Torna a Api Key
    And Premere tre puntini
    And Seleziona Ruota

    And Verifica traduzione testo "Rotate API Key"
    And Verifica traduzione testo "After you have inserted the new API Key into the institution"

    And Click Ruota
    And Premere tre puntini
    And Seleziona Blocca

    And Verifica traduzione testo "Lock API Key"
    And Verifica traduzione testo "You can activate it again at any time."

    And Click Blocca

    And Premere tre puntini
    And Seleziona Elimina

    And Verifica traduzione testo "Delete API Key"
    And Verifica traduzione testo "If you permanently delete the API Key"
    And Click Delete

#  Raggiungere la sezione Statistiche e verificarne le traduzioni

    When Seleziona voce menu laterale "Statistics"
    And Verifica traduzione testo "Overview of the notifications"
    And Verifica traduzione testo "Export JPEG"
    And Verifica traduzione testo "Select the data collection interval"

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "Platform status"
    And Verifica traduzione testo "Check the operation of SEND"
#    And Verifica traduzione testo "All SEND services are operational"
    And Verifica traduzione testo "Disruption history"

