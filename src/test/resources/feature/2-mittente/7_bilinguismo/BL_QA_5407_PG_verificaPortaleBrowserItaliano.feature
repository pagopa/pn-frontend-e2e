Feature: PG - Verifica portale browser Italiano

  @TestSuite
  @TA_bilinguismoPGVerificaPortaleBrowserInItaliano_5407
  @TA_Italiano
  @bilinguismo
  @deleghe2

  Scenario: PN-5407 - PG - Verifica portale browser Italiano

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
##  Eseguire l’accesso al portale e e verificarne le traduzioni

    And Verifica traduzione testo "Notifiche"
    And Verifica traduzione testo "Deleghe"
    And Verifica traduzione testo "Recapiti"
##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Notifiche"
    And Seleziona voce menu laterale "Notifiche dell"

    And Verifica traduzione testo "Notifiche di Convivio Spa"
    And Verifica traduzione testo "Leggi le notifiche di Convivio Spa. Puoi filtrarle per Codice IUN e data di invio"

    And Entro dentro la prima notifica
    And Verifica traduzione testo "Mittente"
    And Verifica traduzione testo "Documenti allegati"
    And Verifica traduzione testo "Avviso di avvenuta ricezione"
##  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Notifiche delegate"
    And Verifica traduzione testo "Leggi le notifiche delegate a Convivio Spa. Puoi filtrarle per Codice IUN e data di invio"
##  Aggiungere e gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Deleghe"
    And Verifica traduzione testo "Qui si possono gestire i delegati dell"
    And Verifica traduzione testo "Deleghe a carico dell"

    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Verifica traduzione testo "Inserisci i dati della persona fisica o giuridica a cui vuoi delegare la lettura delle tue notifiche"
    And Verifica traduzione testo "Soggetto giuridico"
    And Verifica traduzione testo "Solo enti selezionati"
    And Verifica traduzione testo "Condividi questo codice con la persona delegata"

    And Nella sezione Deleghe dell impresa Aggiungi Persona Giuridica
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
   ##  verifica traduzione Richiesta delega creata
    And Verifica traduzione testo "Richiesta di delega creata"
    And Verifica traduzione testo "Condividi il codice di verifica con la persona delegata"
    And Verifica traduzione testo "Torna alle deleghe"


    And Click torna alle deleghe
##  Raggiungere la sezione Recapiti e verificarne la traduzione
    When Seleziona voce menu laterale "Recapiti"
    And Verifica traduzione testo "Inserisci o modifica  i recapiti digitali a cui Convivio Spa riceverà le notifiche degli enti aderenti a SEND"
    And Verifica traduzione testo "Recapito a valore legale"
    And Verifica traduzione testo "Se scegli SEND come Domicilio Digitale ricevi le notifiche dagli enti che aderiscono alla piattaforma in tempo reale e puoi consultarle ovunque tu sia"
    And Verifica traduzione testo "avviso di avvenuta ricezione viene inviato a questo indirizzo. Per leggere la notifica e pagare eventuali spese"
##    Inserire PEC
    And Rimuovi tutti i recapiti se esistono
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC "pec@pec.pagopa.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma

  ## verifica traduzione PEC
    And Verifica traduzione testo "Abbiamo inviato un codice all’indirizzo PEC indicato"
    And Verifica traduzione testo "Inserisci codice"
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Cliccare sul bottone Annulla
#    And Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
##   Inserire EMAIL
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email

    And Verifica traduzione testo "Abbiamo inviato un codice all’indirizzo email indicato"
    And Verifica traduzione testo "Inserisci codice"
    And Cliccare sul bottone Annulla

##-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-
    #  Raggiungere la sezione Integrazione API verificarne le traduzioni
    When Seleziona voce menu laterale "Integrazione API"
    And Pulisci ambiente public keys
##    Caso semplice nessuna chiave presente
    And Click registra chiave pubblica
    And Inserisci valore della chiave "Attiva"
    And Click registra o Fine
    And Verifica Pop-up Integrazione Api "con successo"


    And Click registra o Fine
##    devo premere sui tre puntini e selezionare Ruota la colonna Valore = Attiva- lo stato = Attiva
    And Click tre puntini public keys "Attiva"
    And Click Ruota Integrazione Api
    And Verifica traduzione testo "Ruotando la chiave pubblica esistente, ne dovrai inserire una nuova e otterrai i valori di Kid e Issuer."
    And Click ruota e registra nuova chiave
    And Inserisci valore della chiave "Ruota"
    And Click registra o Fine
    And Verifica Pop-up Integrazione Api "con successo"
    And Verifica traduzione testo "Registra chiave pubblica"
    And Verifica traduzione testo "Ottieni parametri"
    And Verifica traduzione testo "utilizza questi dati per configurare la tua chiave di accesso a SEND"
    And Click registra o Fine
    And Refresh pagina
    ##    devo premere sui tre puntini e selezionare Blocca la colonna Valore = Ruota- lo stato = Attiva
    And Click tre puntini public keys "Ruota"
    And Click Blocca Integrazione Api
    And Verifica traduzione testo "Blocca chiave"
    And Verifica traduzione testo "qualsiasi richiesta effettuata attraverso di essa avrà esito negativo"
    And Click Blocca
    And Verifica Pop-up Integrazione Api "con successo"
    And Click tre puntini public keys "Elimina"
    And Click Elimina Integrazione Api
    And Verifica traduzione testo "Elimina chiave"
    And Verifica traduzione testo "Se elimini definitivamente la chiave, questa non potrà essere nuovamente attivata e utilizzata"
    And Click Delete
    And Verifica Pop-up Integrazione Api "con successo"
    ## Generare una chiave Personale
    And Pulisci ambiente virtual keys
    And Click genera chiave personale
    And Verifica traduzione testo "La tua chiave personale"
    And Verifica traduzione testo "Puoi usarla per autenticarti in piattaforma e integrare SEND. Puoi copiare la tua chiave personale in ogni momento nella sezione"
    And Click ok ho capito
    And Refresh pagina
    And Click tre puntini virtual keys "Attiva"
    And Click Ruota Integrazione Api
    And Verifica traduzione testo "Ruotando la chiave esistente, la disattivi e ne generi una nuova con gli stessi attributi"
    And Click Ruota
    And Refresh pagina
    And Click tre puntini virtual keys "Attiva"
    And Click Blocca Integrazione Api
    And Verifica traduzione testo "Se blocchi la chiave, qualsiasi richiesta effettuata attraverso di essa avrà esito negativo"
    And Click Blocca
    And Refresh pagina
    And Click tre puntini virtual keys "Elimina"
    And Click Elimina Integrazione Api
    And Verifica traduzione testo "Elimina chiave"
    And Verifica traduzione testo "Se elimini definitivamente la chiave, questa non potrà essere nuovamente attivata e utilizzata"
    And Click Delete
##    Selezionare Stato della Piattaforma
    When Seleziona voce menu laterale "Stato della piattaforma"
    And Verifica traduzione testo "Stato della piattaforma"
    And Verifica traduzione testo "Verifica il funzionamento di SEND, visualizza lo storico dei disservizi e scarica le relative attestazioni opponibili"
##-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-

    And Cambia lingua footer "Inglese"
    And Refresh pagina
    And Attendi secondi "3"
    When Seleziona voce menu laterale "Notifications"
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Delegations of authority"
    And Verifica traduzione testo "Contact details"
##  Verificare traduzione della sezione HP notifiche

    And Seleziona voce menu laterale "Company notifications"

    And Verifica traduzione testo "Notifications of"
    And Verifica traduzione testo "You can filter them by IUN Code and send date"
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Sender"
    And Verifica traduzione testo "Recipient"
    And Verifica traduzione testo "Date sent"
##  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Delegated notifications"
    And Verifica traduzione testo "Read the notifications delegated"
##  Aggiungere e gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Delegations of authority"
    And Verifica traduzione testo "Here you can manage the company"
    And Verifica traduzione testo "Authorities held by the company"

    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Verifica traduzione testo "Enter the details of the person or entity you wish to authorise to read your notifications"
    And Verifica traduzione testo "Natural person"
    And Verifica traduzione testo "Legal person"
    And Verifica traduzione testo "Share this code with the authorised representative"

    And Nella sezione Deleghe dell impresa Aggiungi Persona Giuridica
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
   ##  verifica traduzione Richiesta delega creata
    And Verifica traduzione testo "Authority request created"
    And Verifica traduzione testo "Share the verification code with the authorised representative"
    And Verifica traduzione testo "Back to authorities"

    And Click torna alle deleghe
##  Raggiungere la sezione Recapiti e verificarne la traduzione
    When Seleziona voce menu laterale "Contact details"
    And Verifica traduzione testo "you can indicate and change the digital contact details to which Convivio Spa will receive notifications"
##    Inserire PEC
    And Rimuovi tutti i recapiti se esistono
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC "pec@pec.pagopa.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma

  ## verifica traduzione PEC
    And Verifica traduzione testo "We have sent a code to the PEC address indicated"
    And Verifica traduzione testo "Enter code"
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Cliccare sul bottone Annulla
#    And Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
#   Inserire EMAIL
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email

    And Verifica traduzione testo "We have sent a code to the email address indicated"
    And Verifica traduzione testo "Enter code"
    And Cliccare sul bottone Annulla

#-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-
    #  Raggiungere la sezione Integrazione API verificarne le traduzioni
    When Seleziona voce menu laterale "Integrazione API"
    And Pulisci ambiente public keys
#    Caso semplice nessuna chiave presente
    And Click registra chiave pubblica
    And Inserisci valore della chiave "Attiva"
    And Click registra o Fine
    And Verifica Pop-up Integrazione Api "con successo"


    And Click registra o Fine
#    devo premere sui tre puntini e selezionare Ruota la colonna Valore = Attiva- lo stato = Attiva
    And Click tre puntini public keys "Attiva"
    And Click Ruota Integrazione Api
    And Verifica traduzione testo "Ruotando la chiave pubblica esistente, ne dovrai inserire una nuova e otterrai i valori di Kid e Issuer."
    And Click ruota e registra nuova chiave
    And Inserisci valore della chiave "Ruota"
    And Click registra o Fine
    And Verifica Pop-up Integrazione Api "con successo"
    And Verifica traduzione testo "Registra chiave pubblica"
    And Verifica traduzione testo "Ottieni parametri"
    And Verifica traduzione testo "utilizza questi dati per configurare la tua chiave di accesso a SEND"
    And Click registra o Fine
    And Refresh pagina
    #    devo premere sui tre puntini e selezionare Blocca la colonna Valore = Ruota- lo stato = Attiva
    And Click tre puntini public keys "Ruota"
    And Click Blocca Integrazione Api
    And Verifica traduzione testo "Blocca chiave"
    And Verifica traduzione testo "qualsiasi richiesta effettuata attraverso di essa avrà esito negativo"
    And Click Blocca
    And Verifica Pop-up Integrazione Api "con successo"
    And Click tre puntini public keys "Elimina"
    And Click Elimina Integrazione Api
    And Verifica traduzione testo "Elimina chiave"
    And Verifica traduzione testo "Se elimini definitivamente la chiave, questa non potrà essere nuovamente attivata e utilizzata"
    And Click Delete
    And Verifica Pop-up Integrazione Api "con successo"
    # Generare una chiave Personale
    And Pulisci ambiente virtual keys
    And Click genera chiave personale
    And Verifica traduzione testo "La tua chiave personale"
    And Verifica traduzione testo "Puoi usarla per autenticarti in piattaforma e integrare SEND. Puoi copiare la tua chiave personale in ogni momento nella sezione"
    And Click ok ho capito
    And Refresh pagina
    And Click tre puntini virtual keys "Attiva"
    And Click Ruota Integrazione Api
    And Verifica traduzione testo "Ruotando la chiave esistente, la disattivi e ne generi una nuova con gli stessi attributi"
    And Click Ruota
    And Refresh pagina
    And Click tre puntini virtual keys "Attiva"
    And Click Blocca Integrazione Api
    And Verifica traduzione testo "Se blocchi la chiave, qualsiasi richiesta effettuata attraverso di essa avrà esito negativo"
    And Click Blocca
    And Refresh pagina
    And Click tre puntini virtual keys "Elimina"
    And Click Elimina Integrazione Api
    And Verifica traduzione testo "Elimina chiave"
    And Verifica traduzione testo "Se elimini definitivamente la chiave, questa non potrà essere nuovamente attivata e utilizzata"
    And Click Delete
#    Selezionare Stato della Piattaforma
    When Seleziona voce menu laterale "Platform status"
    And Verifica traduzione testo "Check the operation of SEND, view service disruption history and download the attestations"
    And Verifica traduzione testo "Disruption history"
#-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-