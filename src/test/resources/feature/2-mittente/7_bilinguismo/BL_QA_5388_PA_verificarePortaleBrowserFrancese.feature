Feature: PA Verificare portale browser Francese

  @TestSuite_BROWSER
  @TA_bilinguismoVerificaPortaleBrowserFrancese_QA5388
  @TA_Francese
  @bilinguismo

  Scenario: PN-QA5388-BL - PA - Verificare portale browser Francese

    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |

    When Login con mittente Comune di "Viggiu"
    And Click entra su Send Mittente
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente

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
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
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

    When Seleziona voce menu laterale "Notifications"

    #    PARTE TEDESCO
    And Cambia lingua footer "Allemand"
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Zustellungen"
    And Verifica traduzione testo "Statistiken"
    And Verifica traduzione testo "Plattformstatus"
    And Verifica traduzione testo "Benutzer"
    And Verifica traduzione testo "Gruppen"
#   Verificare traduzione della sezione HP notifiche
    And Attendi secondi "2"
#    And Verifica traduzione testo "Datum"
    And Verifica traduzione testo "Empfänger"
    And Verifica traduzione testo "Betreff"
    And Verifica traduzione testo "Gruppen"
    And Verifica traduzione testo "Status"
    And Verifica traduzione testo "Hier findest du alle von der Körperschaft gesendeten Zustellungen"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Absender"
    And Verifica traduzione testo "Empfänger"
    And Verifica traduzione testo "Sendedatum"
    And Verifica traduzione testo "Beigefügte Anlagen"
    And Verifica traduzione testo "Empfangsbestätigung"
#    And Verifica traduzione testo "DISRUPTIONS"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#    Invio Notifica
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
#    Traduzione prima pagina notifica
    And Verifica traduzione testo "Neuen Bescheid senden"
    And Verifica traduzione testo "Zum senden des Bescheids die erforderlichen Daten eingeben und"
    And Verifica traduzione testo "Vorabinformationen"
    And Verifica traduzione testo "Gegenstand der Zustellung*"
    And Verifica traduzione testo "Einschreiben mit Rückschein"

    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Italiano"
    And Cliccare su continua
    #    Traduzione seconda pagina notifica
    And Verifica traduzione testo "Empfänger"
    And Verifica traduzione testo "Pflichtfelder"
    And Verifica traduzione testo "Natürliche Person"
    And Verifica traduzione testo "Juristische Person"
    And Verifica traduzione testo "Adresse"
    And Verifica traduzione testo "Postleitzahl"
    And Verifica traduzione testo "Gemeinde"
    And Verifica traduzione testo "Provinz"
    And Verifica traduzione testo "Hausnummer"

    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua

#    Traduzione terza pagina notifica
    And Verifica traduzione testo "Anhänge"
    And Verifica traduzione testo "Urkunde anhängen"
    And Verifica traduzione testo "lade die Datei"
    And Verifica traduzione testo "Name des Dokuments"
    And Verifica traduzione testo "Das Dokument hierhin ziehen"
    And Verifica traduzione testo "Senden"
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia

    #    Traduzione quarta  pagina notifica
    And Verifica traduzione testo "Bescheid wurde erstellt"
    And Verifica traduzione testo "Aktualisierungen zum Status im Abschnitt"
    And Verifica traduzione testo "Zu den Bescheiden"
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche

#  Raggiungere la sezione API Key e verificarne le traduzioni
    When Seleziona voce menu laterale "API-Schlüssel"
    And Verifica traduzione testo "API-Schlüssel"
    And Verifica traduzione testo "SEND bietet APIs für das"
    And Verifica traduzione testo "Generierte API-Schlüssel"
    And Verifica traduzione testo "Name"
    And Verifica traduzione testo "Letzte Änderung"

#    IMPLEMENTAZIONE CREAZIONE DELETE .... apikey
    When Click Genera Api Key
#    verifica traduzione Genera Api Kei
    And Verifica traduzione testo "Sonstige Angaben"
    And Verifica traduzione testo "Gib deinem API-Schlüssel einen Namen"
    And Verifica traduzione testo "Wähle die Gruppen aus, denen der API-Schlüssel zugewiesen werden soll"
    And Verifica traduzione testo "Bitte einen Namen eingeben"

    And Inserisci nome Api Key
    And Verifica traduzione testo "API-Schlüssel erfolgreich generiert"
    And Verifica traduzione testo "Um die Integration zu ermöglichen, kopiere den Code und gib ihn auf der Plattform der jeweiligen Körperschaft ein"
    And Verifica traduzione testo "Zurück zu API-Schlüssel"

    And Torna a Api Key
    And Premere tre puntini
    And Seleziona Ruota

    And Verifica traduzione testo "API-Schlüssel rotieren"
    And Verifica traduzione testo "Nachdem du den neuen API-Schlüssel auf der Plattform der Körperschaft eingegeben hast, sperrst und löschst du die"

    And Click Ruota
    And Premere tre puntini
    And Seleziona Blocca

    And Verifica traduzione testo "API-Schlüssel sperren"
    And Verifica traduzione testo "Er kann jederzeit wieder aktiviert werden"

    And Click Blocca
    And Premere tre puntini
    And Seleziona Elimina

    And Verifica traduzione testo "API-Schlüssel löschen"
    And Verifica traduzione testo "Wenn du den API-Schlüssel"
    And Click Delete

#  Raggiungere la sezione Statistiche e verificarne le traduzioni

    When Seleziona voce menu laterale "Statistiken"
    And Verifica traduzione testo "Übersicht der Zustellungen"
    And Verifica traduzione testo "JPEG exportieren"
    And Verifica traduzione testo "Wähle das Erfassungsintervall"

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "Plattformstatus"
    And Verifica traduzione testo "Überprüft die Funktionsweise von SEND"
#    And Verifica traduzione testo "All SEND services are operational"
    And Verifica traduzione testo "Verlauf der Fehlfunktionen"
