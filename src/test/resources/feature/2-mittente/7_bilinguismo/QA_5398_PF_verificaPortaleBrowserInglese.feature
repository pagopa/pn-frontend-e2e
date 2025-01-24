Feature: PF Verificare portale browser Inglese

  @TestSuite
  @TA_bilinguismoPFVerificaPortaleBrowserInglese_QA5398
  @TA_Inglese
  @bilinguismo

  Scenario: PN-QA5398 - PF - Verificare portale browser Inglese

    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

    When Seleziona voce menu laterale "Notifications"
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Your addresses"
    And Verifica traduzione testo "Delegates"
    And Verifica traduzione testo "Platform status"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Your notifications"
    And Verifica traduzione testo "Sender"
    And Verifica traduzione testo "Subject"
    And Verifica traduzione testo "Status"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Sender"
    And Verifica traduzione testo "Recipient"
    And Verifica traduzione testo "Date sent"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "Your addresses"
    And Verifica traduzione testo "Addresses"
    And Verifica traduzione testo "Here you can manage addresses and receive the notifications sent to you by institutions registered with SEND"
    And Verifica traduzione testo "Legal address"
    And Verifica traduzione testo "PEC address"
    And Verifica traduzione testo "Email address"

#  Raggiungere la sezione deleghe e verificarne la traduzione

    When Seleziona voce menu laterale "Delegates"
    And Verifica traduzione testo "Here you can manage your delegates and your proxies. The first are the natural or legal"
    And Verifica traduzione testo "Your delegates"
    And Verifica traduzione testo "Your proxies"
    And Verifica traduzione testo "Add a delegate"

#    #    inserire dati per la delega
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
#    #    Verifica traduzione
    And Verifica traduzione testo "Add a delegate"
    And Verifica traduzione testo "Enter the data of the natural or legal person to whom you want to delegate the reading of your notifications."
    And Verifica traduzione testo "Physical person"
    And Verifica traduzione testo "Legal person"
    And Verifica traduzione testo "Name"
    And Verifica traduzione testo "Surname"
    And Verifica traduzione testo "Tax code"
    And Verifica traduzione testo "Share this code with the authorized representative: they will have to enter it when accepting the delegation"

    And Inserisci credenziali Delegato
      | soggettoGiuridico       | PF               |
      | nome                    | Lucrezia         |
      | cognome                 | Borgia           |
      | codiceFiscale           | BRGLRZ80D58H501Q |

    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Attendi secondi "3"
## verifica pagina di Richiesta di delega creata
    And Verifica traduzione testo "Delegate request created"
    And Verifica traduzione testo "Share the verification code with the delegate"
    And Verifica traduzione testo "Back to delegates"

    And Click torna alle deleghe

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "Platform status"
    And Verifica traduzione testo "Check the operation of SEND, view the history of disruptions and download the related certificates enforceable against third parties"

     #    PARTE Francese
    And Cambia lingua footer "French"
    When Seleziona voce menu laterale "Notifications"
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Notifications"
    And Verifica traduzione testo "Vos adresses"
    And Verifica traduzione testo "Procurations"
    And Verifica traduzione testo "État de la plateforme"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Date"
    And Verifica traduzione testo "Expéditeur"
    And Verifica traduzione testo "Objet"
    And Verifica traduzione testo "État"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Expéditeur"
    And Verifica traduzione testo "Destinataire"
    And Verifica traduzione testo "Date d"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "Vos adresses"
    And Verifica traduzione testo "Adresses"
    And Verifica traduzione testo "Vous pouvez ici gérer les adresses auxquelles recevoir les notifications des organismes ayant adhéré à SEND"
    And Verifica traduzione testo "Adresse à valeur légale"
    And Verifica traduzione testo "Adresse PEC"
    And Verifica traduzione testo "Adresse email"

#  Raggiungere la sezione deleghe e verificarne la traduzione

    When Seleziona voce menu laterale "Procurations"
    And Verifica traduzione testo "Ici, vous pouvez gérer vos mandataires et les procuration à votre charge"
    And Verifica traduzione testo "Vos mandataires"
    And Verifica traduzione testo "Procurations à votre charge"
    And Verifica traduzione testo "Ajouter une procuration"

#    #    inserire dati per la delega
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
#    #    Verifica traduzione
    And Verifica traduzione testo "Ajouter une procuration"
    And Verifica traduzione testo "Saisissez les données de la personne physique ou morale à qui vous souhaitez mandater la lecture de vos notifications"
    And Verifica traduzione testo "Personne physique"
    And Verifica traduzione testo "Personnalité juridique"
    And Verifica traduzione testo "Prénom"
    And Verifica traduzione testo "Nom de famille"
    And Verifica traduzione testo "Code fiscal"
    And Verifica traduzione testo "Partagez ce code avec la personne mandatée"

    And Inserisci credenziali Delegato
      | soggettoGiuridico       | PF               |
      | nome                    | Lucrezia         |
      | cognome                 | Borgia           |
      | codiceFiscale           | BRGLRZ80D58H501Q |

    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Attendi secondi "3"
## verifica pagina di Richiesta di delega creata
    And Verifica traduzione testo "Demande de procuration créée"
    And Verifica traduzione testo "Partagez le code de vérification avec la personne mandatée"
    And Verifica traduzione testo "Retour aux procurations"

    And Click torna alle deleghe

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "État de la plateforme"
    And Verifica traduzione testo "historique des dysfonctionnements et télécharge les attestations correspondantes opposables à des tiers"


