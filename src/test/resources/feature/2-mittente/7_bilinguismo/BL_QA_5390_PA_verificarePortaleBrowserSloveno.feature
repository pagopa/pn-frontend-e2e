Feature: PA - Verificare portale browser Sloveno

  @TestSuite_BROWSER
  @TA_bilinguismoVerificaPortaleBrowserFrancese_QA5390
  @TA_Sloveno
  @bilinguismo

  Scenario: PN-QA5390-BL - PA - Verificare portale browser Sloveno

    Given Login Page mittente viene visualizzata
      | url | https://selfcare.test.notifichedigitali.it |

    And Si clicca bottone accetta cookies
    When Login con mittente Comune di "Viggiu"
    And Si clicca bottone accetta cookies
    And Click entra su Send Mittente
    And Home page mittente viene visualizzata correttamente

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

    When Seleziona voce menu laterale "Obvestila"
     #    PARTE Italiano
    And Cambia lingua footer "Italijansko"
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
    And Seleziona radion button Inserimento Manuale se esiste "0"
    And Nella section Destinatario cliccare su aggiungi indirizzo fisico, compilare i dati della persona fisica "personaFisica" destinatario 0
    And Nella section Destinatario cliccare su Aggiungi domicilio Digitale, compilare i dati della persona fisica
    And Cliccare su continua

    And Seleziona Nessun Pagamento 1
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
