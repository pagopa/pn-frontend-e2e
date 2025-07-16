Feature: PA - Verificare portale browser Tedesco

  @TestSuite_BROWSER
  @TA_bilinguismoVerificaPortaleBrowserFrancese_QA5389
  @TA_Tedesco
  @bilinguismo

  Scenario: PN-QA5389-BL - PA - Verificare portale browser Tedesco

    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |

    When Login con mittente Comune di "Viggiu"
    And Click entra su Send Mittente
    And Si clicca bottone accetta cookies
    And Home page mittente viene visualizzata correttamente

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

    When Seleziona voce menu laterale "Zustellungen"

     #    PARTE SLOVENO

    And Cambia lingua footer "Slowenisch"
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Obvestila"
    And Verifica traduzione testo "Statistični podatki"
    And Verifica traduzione testo "Stanje platforme"
    And Verifica traduzione testo "Uporabniki"
    And Verifica traduzione testo "Skupine"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Datum"
    And Verifica traduzione testo "Prejemnik"
    And Verifica traduzione testo "Skupine"
    And Verifica traduzione testo "Država"
    And Verifica traduzione testo "Tukaj boste našli vsa obvestila, ki jih je poslala organizacija. Filtrirate jih lahko po davčni številki"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Pošiljatelj"
    And Verifica traduzione testo "Prejemnik"
    And Verifica traduzione testo "Davčna številka prejemnika"
    And Verifica traduzione testo "Priloženi dokumenti"
    And Verifica traduzione testo "Potrdilo o prejemu"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#    Invio Notifica
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
#    Traduzione prima pagina notifica
    And Verifica traduzione testo "Pošljite novo obvestilo"
    And Verifica traduzione testo "Obvestila, ki vključujejo plačilo, je trenutno mogoče poslati samo prek ključa API."
    And Verifica traduzione testo "Predhodne informacije"
    And Verifica traduzione testo "Številka protokola"
    And Verifica traduzione testo "Priporočeno pismo s povratnico"

    And Nella section Informazioni preliminari inserire i dati della notifica senza pagamento senza gruppo con lingua "Italiano"
    And Cliccare su continua
    #    Traduzione seconda pagina notifica
    And Verifica traduzione testo "Prejemniki"
    And Verifica traduzione testo "Obvezna polja"
    And Verifica traduzione testo "Fizična oseba"
    And Verifica traduzione testo "Pravna oseba"
    And Verifica traduzione testo "Naslov"
    And Verifica traduzione testo "Poštna številka"
    And Verifica traduzione testo "Naslov"
    And Verifica traduzione testo "Pokrajina"
    And Verifica traduzione testo "Hišna številka"

    And Nella section Destinatario inserire nome cognome e codice fiscale da persona fisica "personaFisica"
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua
    And Seleziona Nessun Pagamento 1
    And Cliccare su continua

#    Traduzione terza pagina notifica
    And Verifica traduzione testo "Priloge"
    And Verifica traduzione testo "Priloži listino"
    And Verifica traduzione testo "naloži datoteko"
    And Verifica traduzione testo "Ime dokumenta"
    And Verifica traduzione testo "Nazaj na obvestila"
    And Verifica traduzione testo "Pošlji"
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Allegati
    And Nella section Allegati caricare l'atto e inserire il nome atto "datiNotifica"
    And Nella section Allegati cliccare sul bottone Invia

    #    Traduzione quarta  pagina notifica
    And Verifica traduzione testo "Obvestilo je bilo ustvarjeno"
    And Verifica traduzione testo "Posodobitve o njegovem stanju najdete v razdelku"
    And Verifica traduzione testo "Pojdite na obvestila"
    And Si visualizza correttamente la frase La notifica è stata correttamente creata
    And Cliccare sul bottone vai alle notifiche
    And Si visualizza correttamente la pagina Piattaforma Notifiche

#  Raggiungere la sezione API Key e verificarne le traduzioni
    When Seleziona voce menu laterale "Ključ API"
    And Verifica traduzione testo "Ključ API"
    And Verifica traduzione testo "Ustvarjeni ključi API"
    And Verifica traduzione testo "Ustvari ključ API"
    And Verifica traduzione testo "Ime"
    And Verifica traduzione testo "Zadnja sprememba"

#    IMPLEMENTAZIONE CREAZIONE DELETE .... apikey
    When Click Genera Api Key
#    verifica traduzione Genera Api Kei
    And Verifica traduzione testo "Druge informacije"
    And Verifica traduzione testo "Poimenujte svoj ključ API"
    And Verifica traduzione testo "Izberite skupine, ki jim želite dodeliti ključ API"
    And Verifica traduzione testo "Vnesite ime"

    And Inserisci nome Api Key
    And Verifica traduzione testo "Ključ API je bil uspešno ustvarjen"
    And Verifica traduzione testo "Če želite omogočiti integracijo, kopirajte kodo in jo vnesite v lastniško platformo organizacije"
    And Verifica traduzione testo "Nazaj na ključ API"

    And Torna a Api Key
    And Premere tre puntini
    And Seleziona Ruota

    And Verifica traduzione testo "Zasukaj ključ API"
    And Verifica traduzione testo "Ko vnesete nov ključ API v platformo organizacije, blokirajte in izbrišite zasukano različico."

    And Click Ruota
    And Premere tre puntini
    And Seleziona Blocca

    And Verifica traduzione testo "Blokiraj ključ API"
    And Verifica traduzione testo "Kadarkoli ga lahko znova aktivirate."

    And Click Blocca

    And Premere tre puntini
    And Seleziona Elimina

    And Verifica traduzione testo "Izbriši ključ API"
    And Verifica traduzione testo "Če trajno izbrišete ključ API"
    And Click Delete

#  Raggiungere la sezione Statistiche e verificarne le traduzioni

    When Seleziona voce menu laterale "Statistični podatki"
    And Verifica traduzione testo "Pregled obvestil"
    And Verifica traduzione testo "Izvozi JPEG"
    And Verifica traduzione testo "Izberite interval spremljanja"

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "Stanje platforme"
    And Verifica traduzione testo "Preverite delovanje SEND, oglejte si zgodovino motenj in prenesite povezana potrdila"
    And Verifica traduzione testo "Zgodovina motenj"
