Feature: PF Verificare portale browser Francese

  @TestSuite
  @TA_bilinguismoPFVerificaPortaleBrowserFrancese_QA5399
  @TA_Francese
  @bilinguismo

  Scenario: PN-QA5399-BL - PF - Verificare portale browser Francese

    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

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

    And Inserisci credenziali Delegante
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

    And Cambia lingua footer "Allemand"
    When Seleziona voce menu laterale "Zustellungen"
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Zustellungen"
    And Verifica traduzione testo "Deine Adressen"
    And Verifica traduzione testo "Vollmachten"
    And Verifica traduzione testo "Plattformstatus"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Datum"
    And Verifica traduzione testo "Absender"
    And Verifica traduzione testo "Betreff"
    And Verifica traduzione testo "Status"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Absender"
    And Verifica traduzione testo "Empfänger"
    And Verifica traduzione testo "Sendedatum"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "Deine Adressen"
    And Verifica traduzione testo "Adressen"
    And Verifica traduzione testo "Hier kannst du die Adressen verwalten, an die du die Zustellungen der SEND"
    And Verifica traduzione testo "Rechtsgültige Adresse"
    And Verifica traduzione testo "PEC-Adresse"
    And Verifica traduzione testo "E-Mail-Adresse"

#  Raggiungere la sezione deleghe e verificarne la traduzione

    When Seleziona voce menu laterale "Vollmachten"
    And Verifica traduzione testo "Hier können die Bevollmächtigten des Unternehmens und deren Vollmachten verwaltet werden"
    And Verifica traduzione testo "Deine Bevollmächtigten"
    And Verifica traduzione testo "Deine Vollmachten"
    And Verifica traduzione testo "Eine Vollmacht hinzufügen"

#    #    inserire dati per la delega
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
#    #    Verifica traduzione
    And Verifica traduzione testo "Eine Vollmacht hinzufügen"
    And Verifica traduzione testo "Gib die Daten der natürlichen oder juristischen Person ein, der du eine Vollmacht zum Lesen deiner Zustellungen"
    And Verifica traduzione testo "Natürliche Person"
    And Verifica traduzione testo "Juristische Person"
    And Verifica traduzione testo "Name"
    And Verifica traduzione testo "Nachname"
    And Verifica traduzione testo "Steuernummer"
    And Verifica traduzione testo "Teile diesen Code mit der bevollmächtigten Person"

    And Inserisci credenziali Delegato
      | soggettoGiuridico       | PF               |
      | nome                    | Lucrezia         |
      | cognome                 | Borgia           |
      | codiceFiscale           | BRGLRZ80D58H501Q |

    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Attendi secondi "3"
## verifica pagina di Richiesta di delega creata
    And Verifica traduzione testo "Vollmachtsanfrage erstellt"
    And Verifica traduzione testo "Teile den Bestätigungscode mit der bevollmächtigten Person"
    And Verifica traduzione testo "Zurück zu den Vollmachten"

    And Click torna alle deleghe

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "Plattformstatus"
    And Verifica traduzione testo "Überprüft die Funktionsweise von SEND"


