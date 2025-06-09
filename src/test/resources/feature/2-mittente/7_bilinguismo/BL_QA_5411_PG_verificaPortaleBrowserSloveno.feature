Feature: PG - Verifica portale browser Sloveno

  @TestSuite
  @TA_bilinguismoPGVerificaPortaleBrowserInSloveno_5411
  @Sloveno
  @bilinguismo
  @deleghe2

  Scenario: PN-5410-BL - PG - Verifica portale browser Sloveno

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard


    And Seleziona voce menu laterale "Obvestila"
    And Verifica traduzione testo "Prenosi pooblastil"
    And Verifica traduzione testo "Kontaktni podatki"
    And Verifica traduzione testo "Stanje platforme"
##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Obvestila"
    And Seleziona voce menu laterale "Obvestila podjetja"

    And Verifica traduzione testo "Obvestila za"
    And Verifica traduzione testo "Preberite obvestila za Convivio Spa"
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Pošiljatelj"
    And Verifica traduzione testo "Prejemnik"
    And Verifica traduzione testo "Datum pošiljanja"
##  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Delegirana obvestila"
    And Verifica traduzione testo "Preberite obvestila, delegirana na Convivio Spa"
##  Aggiungere e gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Prenosi pooblastil"
    And Verifica traduzione testo "Tukaj lahko upravljate pooblaščence podjetja in prenose pooblastil na podjetje"
    And Verifica traduzione testo "Prenos pooblastil na podjetje"

    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Verifica traduzione testo "Vnesite podatke fizične ali pravne osebe, na katero želite prenesti pooblastilo za branje vaših obvestil"
    And Verifica traduzione testo "Pravna oseba"
    And Verifica traduzione testo "Delite to kodo s pooblaščencem"
    And Verifica traduzione testo "Pošlji zahtevo"

    And Nella sezione Deleghe dell impresa Aggiungi Persona Giuridica
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
   ##  verifica traduzione Richiesta delega creata
    And Verifica traduzione testo "Zahteva za prenos pooblastila je bila ustvarjena"
    And Verifica traduzione testo "Delite potrditveno kodo s pooblaščencem"
    And Verifica traduzione testo "Nazaj na prenos pooblastil"

    And Click torna alle deleghe
##  Raggiungere la sezione Recapiti e verificarne la traduzione
    When Seleziona voce menu laterale "Kontaktni podatki"
    And Verifica traduzione testo "Tukaj lahko navedete in spremenite digitalne kontaktne podatke, na katere Convivio Spa boste prejemali obvestila"
##    Inserire PEC
    And Rimuovi tutti i recapiti se esistono
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC "pec@pec.pagopa.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma

  ## verifica traduzione PEC
    And Verifica traduzione testo "Kodo smo poslali na navedeni PEC"
    And Verifica traduzione testo "Vnesite kodo"
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Cliccare sul bottone Annulla
#    And Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
#   Inserire EMAIL
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email

    And Verifica traduzione testo "Kodo smo poslali na navedeni e-poštni naslov"
    And Verifica traduzione testo "Vnesite kodo"
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
    And Attendi secondi "3"
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
    When Seleziona voce menu laterale "Stanje platforme"
    And Verifica traduzione testo "Preverite delovanje SEND, oglejte si zgodovino motenj in prenesite povezana potrdila"
    And Verifica traduzione testo "Zgodovina motenj"
#-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-

    And Cambia lingua footer "Italijansko"
    And Refresh pagina
    And Attendi secondi "4"
    When Seleziona voce menu laterale "Deleghe"
    And Verifica traduzione testo "Recapiti"
    And Verifica traduzione testo "Stato della piattaforma"
    And Verifica traduzione testo "Utenti"
    And Verifica traduzione testo "Gruppi"
    And Refresh pagina