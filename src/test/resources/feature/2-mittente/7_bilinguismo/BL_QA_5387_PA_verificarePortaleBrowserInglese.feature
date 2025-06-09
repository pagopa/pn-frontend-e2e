Feature: PA Verificare portale browser Inglese

  @TestSuite
  @TA_bilinguismoVerificaPortaleBrowserInglese_QA5387
  @TA_Inglese
  @bilinguismo

  Scenario: PN-QA5387 - PA - Verificare portale browser Inglese

    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |

    When Login con mittente Comune di "Viggiu"
    And Si clicca sul bottone test
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente

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
         #      TODO verificare VAS
    And Seleziona radion button Inserimento Manuale se esiste "0"
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

    When Seleziona voce menu laterale "Notifications"

#    PARTE FRANCESCE
    And Cambia lingua footer "French"
#  Verificole traduzioni del portale
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Statistiques"
    And Verifica traduzione testo "État de la plateforme"
    And Verifica traduzione testo "Utilisateurs"
    And Verifica traduzione testo "Groupes"
    #   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Date"
    And Verifica traduzione testo "Destinataire"
    And Verifica traduzione testo "Objet"
    And Verifica traduzione testo "Groupes"
    And Verifica traduzione testo "État"
    And Verifica traduzione testo "Vous trouverez ici toutes les notifications"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Expéditeur"
    And Verifica traduzione testo "Code fiscal destinataire"
    And Verifica traduzione testo "Date"
    And Verifica traduzione testo "Pièces jointes"
    And Verifica traduzione testo "Accusé de réception"
    And Verifica traduzione testo "DYSFONCTIONNEMENTS"
    #  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

    #    Invio Notifica
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
#    Traduzione prima pagina notifica
    And Verifica traduzione testo "Envoyer une nouvelle notification"
    And Verifica traduzione testo "les notifications impliquant un paiement ne peuvent être envoyées que via API Key."
    And Verifica traduzione testo "Informations préliminaires"
    And Verifica traduzione testo "Objet de la notification"
    And Verifica traduzione testo "Lettre recommandée avec A/R"

    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Italiano"
    And Cliccare su continua
      #    Traduzione seconda pagina notifica
    And Verifica traduzione testo "Destinataires"
    And Verifica traduzione testo "Champs obligatoires"
    And Verifica traduzione testo "Personne physique"
    And Verifica traduzione testo "Personne morale"
    And Verifica traduzione testo "Adresse"
    And Verifica traduzione testo "Code postal"
    And Verifica traduzione testo "Commune"
    And Verifica traduzione testo "Province"
    And Verifica traduzione testo "Numéro de rue"

    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
         #      TODO verificare VAS
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua
    #    Traduzione terza pagina notifica
    And Verifica traduzione testo "Annexes"
    And Verifica traduzione testo "Joindre l"
    And Verifica traduzione testo "Charger un document"
    And Verifica traduzione testo "Nom du document"
    And Verifica traduzione testo "Retour au Destinataire"
    And Verifica traduzione testo "Envoyer"
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia

        #    Traduzione quarta  pagina notifica
    And Verifica traduzione testo "La notification a été créée"
    And Verifica traduzione testo "Retrouvez les mises à jour sur son statut dans la rubrique"
    And Verifica traduzione testo "Aller aux notifications"
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche
#    #  Raggiungere la sezione API Key e verificarne le traduzioni
    When Seleziona voce menu laterale "API Key"
    And Verifica traduzione testo "API Key"
    And Verifica traduzione testo "API Key generate"
    And Verifica traduzione testo "Générer Api Key"
    And Verifica traduzione testo "Prénom"
    And Verifica traduzione testo "Dernière modification"

    #    IMPLEMENTAZIONE CREAZIONE DELETE .... apikey
    When Click Genera Api Key
#    verifica traduzione Genera Api Kei
    And Verifica traduzione testo "Autres informations"
    And Verifica traduzione testo "Donnez un nom à votre API key"
    And Verifica traduzione testo "Choisissez les groupes auxquels attribuer"
    And Verifica traduzione testo "Saisissez un prénom"

    And Inserisci nome Api Key
    And Verifica traduzione testo "API Key générée avec succès"
    And Verifica traduzione testo "copiez le code et entrez-le dans la plate-forme propriétaire"
    And Verifica traduzione testo "Retour à API key"

    And Torna a Api Key
    And Premere tre puntini
    And Seleziona Ruota

    And Verifica traduzione testo "Tourner API Key"
    And Verifica traduzione testo "Après avoir inséré la nouvelle API key dans la plate-forme de"

    And Click Ruota
    And Premere tre puntini
    And Seleziona Blocca

    And Verifica traduzione testo "Bloquer API key"
    And Verifica traduzione testo "activer à nouveau à tout moment"

    And Click Blocca

    And Premere tre puntini
    And Seleziona Elimina

    And Verifica traduzione testo "Supprimer API key"
    And Verifica traduzione testo "Si vous supprimez définitivement"
    And Click Delete

    #  Raggiungere la sezione Statistiche e verificarne le traduzioni

    When Seleziona voce menu laterale "Statistiques"
    And Verifica traduzione testo "Aperçu des notifications"
    And Verifica traduzione testo "Exporter JPEG"
    And Verifica traduzione testo "Choisissez l’intervalle d’analyse"

    #  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "État de la plateforme"
    And Verifica traduzione testo "Il vérifie le fonctionnement de SEND"
#    And Verifica traduzione testo "All SEND services are operational"
    And Verifica traduzione testo "Historique des dysfonctionnements"
