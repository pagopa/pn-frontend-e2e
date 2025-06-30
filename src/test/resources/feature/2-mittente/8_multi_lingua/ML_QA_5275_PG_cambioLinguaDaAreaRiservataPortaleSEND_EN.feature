Feature: PG - Cambio Lingua da Area Riservata a portale SEND - EN

  @TestSuite
  @TA_multiLinguaInglese_QA5275
  @multiLingua
  @multiLinguaPg
  @NRT_Blocco_3
  Scenario: PN-QA5275-ML - PG - Cambio Lingua da Area Riservata a portale SEND - EN

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Inglese"
    And Si clicca su prodotto
#    And Aspetta 2 secondi
    And Attesa 2 secondi
    When Seleziona voce menu laterale "Notifications"
    And Verifica traduzione testo "Delegations of authority"
    And Verifica traduzione testo "Contact details"
##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Notifications"
    And Seleziona voce menu laterale "Company notifications"
    And Verifica traduzione testo "Notifications of"
    And Verifica traduzione testo "You can filter them by IUN Code and send date"
    #  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Delegated notifications"
    And Verifica traduzione testo "Read the notifications delegated"
  ##  Raggingere gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Delegations of authority"
    And Verifica traduzione testo "Here you can manage the company"
    And Verifica traduzione testo "Authorities held by the company"
##    Selezionare Stato della Piattaforma
    When Seleziona voce menu laterale "Platform status"
    And Verifica traduzione testo "view service disruption history and download the attestations"
    And Verifica traduzione testo "Disruption history"
#-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-
#    And Aspetta 1 secondi
    And Attesa 1 secondi
    When Cambia lingua footer "German"
    And Seleziona voce menu laterale "Bescheide"
    And Verifica traduzione testo "Vollmachten"
    And Verifica traduzione testo "Anschriften"
    And Verifica traduzione testo "Benutzer"
##  Verificare traduzione della sezione HP notifiche
#    And Aspetta 2 secondi
    And Attesa 2 secondi
    When Seleziona voce menu laterale "Vollmachten"
    And Verifica traduzione testo "Hier können die Bevollmächtigten des Unternehmens und deren Vollmachten verwaltet werden"
    And Verifica traduzione testo "Vollmachten des Unternehmens"
##  Raggiungere la sezione Recapiti e verificarne la traduzione
    When Seleziona voce menu laterale "Anschriften"
    And Verifica traduzione testo "Hier können digitale Anschriften angeben und geändert werden, an die Bescheide für Convivio Spa gesendet werden sollen"
#    Selezionare Stato della Piattaforma
    When Seleziona voce menu laterale "Plattformstatus"
    And Verifica traduzione testo "Überprüft die Funktionsweise von SEND"
    And Verifica traduzione testo "Fehlerhistorie"
#-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-
    And Cambia lingua footer "Englisch"
    And Attendi secondi "1"
    And Cambia lingua footer "French"
    When Seleziona voce menu laterale "Notifications"
    And Verifica traduzione testo "Procurations"
    And Verifica traduzione testo "Coordonnées"
    And Verifica traduzione testo "Utilisateurs"
##  Raggiungere la sezione Recapiti e verificarne la traduzione
    And Attesa 1 secondi
    When Seleziona voce menu laterale "Coordonnées"
    And Verifica traduzione testo "Ici, vous pouvez indiquer et modifier les coordonnées numériques auxquelles Convivio Spa"
#    Selezionare Stato della Piattaforma
#    And Aspetta 1 secondi
    And Attesa 1 secondi
    When Seleziona voce menu laterale "État de la plateforme"
#    And Aspetta 1 secondi
    And Attesa 1 secondi
    And Verifica traduzione testo "Il vérifie le fonctionnement de SEND, affiche l"
    And Verifica traduzione testo "Historique des dysfonctionnements"

    #-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-
    And Cambia lingua footer "Anglais"
    And Cambia lingua footer "Slovenian"
#    And Aspetta 1 secondi
    And Attesa 1 secondi
    And Seleziona voce menu laterale "Obvestila"
#    And Aspetta 1 secondi
    And Attesa 1 secondi
    And Verifica traduzione testo "Prenosi pooblastil"
    And Verifica traduzione testo "Kontaktni podatki"
    And Verifica traduzione testo "Stanje platforme"
##  Aggiungere e gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Prenosi pooblastil"
    And Verifica traduzione testo "Tukaj lahko upravljate pooblaščence podjetja in prenose pooblastil na podjetje"
    And Verifica traduzione testo "Prenos pooblastil na podjetje"
    #    Selezionare Stato della Piattaforma
    Then Seleziona voce menu laterale "Stanje platforme"
    And Verifica traduzione testo "Preverite delovanje SEND, oglejte si zgodovino motenj in prenesite povezana potrdila"
    And Verifica traduzione testo "Zgodovina motenj"
