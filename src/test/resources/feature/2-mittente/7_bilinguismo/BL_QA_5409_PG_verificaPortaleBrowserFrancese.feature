Feature: PG - Verifica portale browser Francese

  @TestSuite_BROWSER
  @TA_bilinguismoPGVerificaPortaleBrowserInFrancese_5409
  @TA_Francese
  @bilinguismo

  Scenario: PN-5409-BL - PG - Verifica portale browser Francese

    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard


    And Refresh pagina
    And Attendi secondi "3"
    When Seleziona voce menu laterale "Notifications"
    And Verifica traduzione testo "Procurations"
    And Verifica traduzione testo "Coordonnées"
    And Verifica traduzione testo "Utilisateurs"

 ##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Notifications"
    And Seleziona voce menu laterale "Notifications de l"

    And Verifica traduzione testo "Notifications de"
    And Verifica traduzione testo "Lire les notifications de Convivio Spa"
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Expéditeur"
    And Verifica traduzione testo "Destinataire"
    And Verifica traduzione testo "Pièces jointes"
##  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Notifications mandatées"
    And Verifica traduzione testo "Lire les notifications mandatées à Convivio Spa"
##  Aggiungere e gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Procurations"
    And Verifica traduzione testo "Ici, vous pouvez gérer les mandataires de l"
    And Verifica traduzione testo "Procurations à la charge de l"

    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Verifica traduzione testo "Saisissez les données de la personne physique ou morale à qui vous souhaitez mandater la lecture de vos notifications"
    And Verifica traduzione testo "Personne physique"
    And Verifica traduzione testo "Personnalité juridique"
    And Verifica traduzione testo "Partagez ce code avec la personne mandaté"

    And Nella sezione Deleghe dell impresa Aggiungi Persona Giuridica
    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
   ##  verifica traduzione Richiesta delega creata
    And Verifica traduzione testo "Demande de procuration créée"
    And Verifica traduzione testo "Partagez le code de vérification avec la personne mandaté"
    And Verifica traduzione testo "Retour aux procurations"

    And Click torna alle deleghe
##  Raggiungere la sezione Recapiti e verificarne la traduzione
    When Seleziona voce menu laterale "Coordonnées"
    And Verifica traduzione testo "Ici, vous pouvez indiquer et modifier les coordonnées numériques auxquelles Convivio Spa"
##    Inserire PEC
    And Rimuovi tutti i recapiti se esistono
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una pec
    And Nella pagina I Tuoi Recapiti si inserisce la PEC "pec@pec.pagopa.it"
    And Nella pagina I Tuoi Recapiti si clicca sul bottone conferma

  ## verifica traduzione PEC
    And Verifica traduzione testo "Vérification PEC"
    And Verifica traduzione testo "Saisissez le code"
#    And Nella pagina I Tuoi Recapiti si recupera il codice OTP tramite chiamata request "personaGiuridica"
#    And Nella pagina I Tuoi Recapiti si inserisce il codice OTP "personaGiuridica"
    And Cliccare sul bottone Annulla
#    And Nella pagina i Tuoi Recapiti si controlla che la pec sia stata inserita correttamente
#   Inserire EMAIL
    And Nella pagina I Tuoi Recapiti si controlla che non ci sia già una email
    And Si inserisce l'email della "personaGiuridica" e si clicca sul bottone avvisami via email

    And Verifica traduzione testo "Nous avons envoyé un code à l"
    And Verifica traduzione testo "Saisissez le code"
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
    When Seleziona voce menu laterale "État de la plateforme"
    And Verifica traduzione testo "Il vérifie le fonctionnement de SEND, affiche l"
    And Verifica traduzione testo "Historique des dysfonctionnements"
#-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-

    And Cambia lingua footer "Allemand"
    And Refresh pagina
    And Attendi secondi "3"
    When Seleziona voce menu laterale "Bescheide"
    And Verifica traduzione testo "Vollmachten"
    And Verifica traduzione testo "Anschriften"
    And Verifica traduzione testo "Benutzer"
    And Refresh pagina