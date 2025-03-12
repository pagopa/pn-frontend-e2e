Feature: PG - Cambio Lingua da Area Riservata a portale SEND - FR

  @TestSuite
  @TA_multiLinguaFrancese_QA5287
  @multiLingua
  @NRT
  Scenario: PN-QA5287 - PG - Cambio Lingua da Area Riservata a portale SEND - FR

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Aspetta 1 secondi
    And Cambia lingua footer "Francese"
#    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto
#    And Riduci zoom pagina al "60"%
    And Aspetta 3 secondi
    When Seleziona voce menu laterale "Notifications"
    And Aspetta 1 secondi
    And Seleziona voce menu laterale "Notifications de l"
    And Aspetta 1 secondi
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
#    Selezionare Stato della Piattaforma
    When Seleziona voce menu laterale "État de la plateforme"
    And Verifica traduzione testo "Il vérifie le fonctionnement de SEND, affiche l"
    And Verifica traduzione testo "Historique des dysfonctionnements"
#-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-
    When Cambia lingua footer "Allemand"
    And Seleziona voce menu laterale "Bescheide"
    And Verifica traduzione testo "Vollmachten"
    And Verifica traduzione testo "Anschriften"
    And Verifica traduzione testo "Benutzer"
##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Vollmachten"
    And Verifica traduzione testo "Hier können die Bevollmächtigten des Unternehmens und deren Vollmachten verwaltet werden"
    And Verifica traduzione testo "Vollmachten des Unternehmens"
    And Aspetta 2 secondi
##  Raggiungere la sezione Recapiti e verificarne la traduzione
    When Seleziona voce menu laterale "Anschriften"
    And Verifica traduzione testo "Hier können digitale Anschriften angeben und geändert werden, an die Bescheide für Convivio Spa gesendet werden sollen"
#    Selezionare Stato della Piattaforma
    When Seleziona voce menu laterale "Plattformstatus"
    And Verifica traduzione testo "Überprüft die Funktionsweise von SEND"
    And Verifica traduzione testo "Fehlerhistorie"
#-*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*--*-*-*-*-*-
    And Cambia lingua footer "Französisch"
    And Cambia lingua footer "Anglais"
    And Aspetta 1 secondi
    When Seleziona voce menu laterale "Notifications"
    And Verifica traduzione testo "Delegations of authority"
    And Verifica traduzione testo "Contact details"
##  Verificare traduzione della sezione HP notifiche
    And Attendi secondi "1"
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
    And Cambia lingua footer "French"
    And Cambia lingua footer "Slovène"
    And Seleziona voce menu laterale "Obvestila"
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
