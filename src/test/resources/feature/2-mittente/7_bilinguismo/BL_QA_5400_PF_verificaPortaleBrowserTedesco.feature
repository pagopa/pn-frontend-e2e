Feature: PF Verificare portale browser Tedesco

  @TestSuite_BROWSER
  @TA_bilinguismoPFVerificaPortaleBrowserTedesco_QA5400
  @TA_Tedesco
  @bilinguismo

  Scenario: PN-QA5400-BL - PF - Verificare portale browser Tedesco

    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

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
    And Verifica traduzione testo "Hier kannst du die Adressen verwalten"
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
    And Verifica traduzione testo "Gib die Daten der natürlichen oder juristischen Person ein, der du eine Vollmacht zum Lesen deiner Zustellungen erteilen möchtest"
    And Verifica traduzione testo "Natürliche Person"
    And Verifica traduzione testo "Juristische Person"
    And Verifica traduzione testo "Name"
    And Verifica traduzione testo "Nachname"
    And Verifica traduzione testo "Steuernummer"
    And Verifica traduzione testo "Teile diesen Code mit der bevollmächtigten Person"

    And Inserisci credenziali Delegante
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
    And Verifica traduzione testo "Überprüft die Funktionsweise von SEND, zeigt den Verlauf der Fehlfunktionen an und lädt die entsprechenden Bescheinigungen herunter, die gegenüber Dritten angefochten werden können."

    And Cambia lingua footer "Slowenisch"
    When Seleziona voce menu laterale "Obvestila"
#   Verificole traduzioni del portale
    And Verifica traduzione testo "Vaša obvestila"
    And Verifica traduzione testo "Vaši kontaktni podatki"
    And Verifica traduzione testo "Pooblastila"
    And Verifica traduzione testo "Stanje platforme"
#   Verificare traduzione della sezione HP notifiche
    And Verifica traduzione testo "Datum"
    And Verifica traduzione testo "Pošiljatelj"
    And Verifica traduzione testo "Zadeva"
    And Verifica traduzione testo "Država"
#  Verificare la traduzione del dettaglio di una notifica
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Pošiljatelj"
    And Verifica traduzione testo "Prejemnik"
    And Verifica traduzione testo "Datum pošiljanja"
#  Procedere con l’ invio di una notifica e verificarne le traduzioni
    And Torna indietro

#  Raggiungere la sezione i tuoi recapiti e verificarne le traduzioni
    When Seleziona voce menu laterale "Vaši kontaktni podatki"
    And Verifica traduzione testo "Kontaktni podatki"
    And Verifica traduzione testo "Tukaj lahko upravljate kontaktne podatke, na katere boste prejemali obvestila"
    And Verifica traduzione testo "Uradna dostava"
    And Verifica traduzione testo "Naslov PEC"
    And Verifica traduzione testo "Elektronski naslov"

#  Raggiungere la sezione deleghe e verificarne la traduzione

    When Seleziona voce menu laterale "Pooblastila"
    And Verifica traduzione testo "Tukaj lahko upravljate svoje pooblaščence in prenose pooblastil na vas"
    And Verifica traduzione testo "Pooblastila"
    And Verifica traduzione testo "Prenosi pooblastil na vas"
    And Verifica traduzione testo "Dodajte prenos pooblastila"

#    #    inserire dati per la delega
    And Nella sezione Deleghe click sul bottone aggiungi nuova delega
#    #    Verifica traduzione
    And Verifica traduzione testo "Dodajte prenos pooblastila"
    And Verifica traduzione testo "Vnesite podatke fizične ali pravne osebe, na katero želite prenesti pooblastilo za branje vaših obvestil"
    And Verifica traduzione testo "Obvezna polja"
    And Verifica traduzione testo "Fizična oseba"
    And Verifica traduzione testo "Ime"
    And Verifica traduzione testo "Priimek"
    And Verifica traduzione testo "Davčna številka"
    And Verifica traduzione testo "Delite to kodo s pooblaščencem: moral jo bo vnesti ob sprejetju prenosa pooblastila"

    And Inserisci credenziali Delegante
      | soggettoGiuridico       | PF               |
      | nome                    | Lucrezia         |
      | cognome                 | Borgia           |
      | codiceFiscale           | BRGLRZ80D58H501Q |

    And Nella sezione Le Tue Deleghe click sul bottone Invia richiesta
    And Attendi secondi "3"
## verifica pagina di Richiesta di delega creata
    And Verifica traduzione testo "Zahteva za prenos pooblastila je bila ustvarjena"
    And Verifica traduzione testo "Delite potrditveno kodo s pooblaščencem: moral jo bo vnesti, ko se bo prvič prijavil v SEND in sprejeti svojo zahtev"
    And Verifica traduzione testo "Nazaj na prenos pooblastil"

    And Click torna alle deleghe

#  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    When Seleziona voce menu laterale "Stanje platforme"
    And Verifica traduzione testo "Preverite delovanje SEND, oglejte si zgodovino motenj in prenesite povezana potrdila, ki so izvršljiva proti tretjim osebam. Vsako potrdilo potrjuje motnjo"


