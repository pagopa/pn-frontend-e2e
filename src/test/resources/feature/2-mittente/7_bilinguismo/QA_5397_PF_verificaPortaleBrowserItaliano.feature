Feature: PF Verificare portale browser Italiano

  @TestSuite
  @TA_bilinguismoPFVerificaPortaleBrowserItaliano_QA5397
  @TA_Italiano
  @bilinguismo

  Scenario: PN-QA5397 - PF - Verificare portale browser Italiano

    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

#   Verificole traduzioni del portale
    And Verifica traduzione testo "Notifiche"
    And Verifica traduzione testo "I tuoi recapiti"
    And Verifica traduzione testo "Deleghe"
    And Verifica traduzione testo "Stato della piattaforma"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Data"
    And Verifica traduzione testo "Mittente"
    And Verifica traduzione testo "Oggetto"
    And Verifica traduzione testo "Stato"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Mittente"
    And Verifica traduzione testo "Destinatario"
    And Verifica traduzione testo "Data di invio"
    And Verifica traduzione testo "Documenti allegati"
    And Verifica traduzione testo "Avviso di avvenuta ricezione"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "I tuoi recapiti"
    And Verifica traduzione testo "Recapiti"
    And Verifica traduzione testo "Qui puoi gestire i recapiti ai quali ricevere le notifiche che ti inviano gli enti aderenti a SEND."
    And Verifica traduzione testo "Recapito a valore legale"
    And Verifica traduzione testo "Indirizzo PEC"
    And Verifica traduzione testo "Indirizzo email"

#  Raggiungere la sezione deleghe e verificarne la traduzione

    When Seleziona voce menu laterale "Deleghe"
    And Verifica traduzione testo "Qui puoi gestire i tuoi delegati e le deleghe a tuo carico. I primi sono le persone fisiche o giuridiche"
    And Verifica traduzione testo "I tuoi delegati"
    And Verifica traduzione testo "Deleghe a tuo carico"
    And Verifica traduzione testo "Aggiungi una delega"

#    #    inserire dati per la delega
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
#    #    Verifica traduzione
    And Verifica traduzione testo "Aggiungi una delega"
    And Verifica traduzione testo "Inserisci i dati della persona fisica o giuridica a cui vuoi delegare la lettura delle tue notifiche"
    And Verifica traduzione testo "Persona fisica"
    And Verifica traduzione testo "Persona giuridica"
    And Verifica traduzione testo "Nome"
    And Verifica traduzione testo "Cognome"
    And Verifica traduzione testo "Codice Fiscale"
    And Verifica traduzione testo "Condividi questo codice con la persona delegata: dovrà inserirlo"

    And Inserisci credenziali Delegato
      | soggettoGiuridico       | PF               |
      | nome                    | Lucrezia         |
      | cognome                 | Borgia           |
      | codiceFiscale           | BRGLRZ80D58H501Q |

    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Attendi secondi "3"
## verifica pagina di Richiesta di delega creata
    And Verifica traduzione testo "Richiesta di delega creata"
    And Verifica traduzione testo "Condividi il codice di verifica con la persona delegata"
    And Verifica traduzione testo "Torna alle deleghe"

    And Click torna alle deleghe

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "Stato della piattaforma"
    And Verifica traduzione testo "Verifica il funzionamento di SEND, visualizza lo storico dei disservizi e scarica"

  #    PARTE INGLESE
    And Cambia lingua footer "Inglese"
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

