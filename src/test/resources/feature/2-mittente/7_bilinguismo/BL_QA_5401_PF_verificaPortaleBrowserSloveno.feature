Feature: PF Verificare portale browser Sloveno

  @TestSuite_BL
  @TA_bilinguismoPFVerificaPortaleBrowserSloveno_QA5401
  @TA_Tedesco
  @bilinguismo

  Scenario: PN-QA5401-BL - PF - Verificare portale browser Sloveno

    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard

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

    And Cambia lingua footer "Italijansko"
    When Seleziona voce menu laterale "Notifiche"
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

    And Inserisci credenziali Delegante
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
