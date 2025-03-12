Feature: PG - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND- DE

  @TestSuite
  @TA_multiLinguaTedesco_QA5301
  @multiLingua
  @NRT_1
  Scenario: PN-QA5301- PG - Verifica corretta traduzione dopo refresh della pagina all'interno del portale SEND- DE

    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Convivio Spa   |
    #   Cambio lingua
    And Cambia lingua footer "Tedesco"
#    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Si clicca su prodotto
    And Aspetta 3 secondi
    And Refresh pagina
    And Aspetta 2 secondi
    When Seleziona voce menu laterale "Bescheide"
    And Verifica traduzione testo "Vollmachten"
    And Verifica traduzione testo "Anschriften"
    And Verifica traduzione testo "Benutzer"
##  Verificare traduzione della sezione HP notifiche
    When Seleziona voce menu laterale "Bescheide"
    And Seleziona voce menu laterale "Bescheide des Unternehmens"
    And Verifica traduzione testo "Bescheide von"
    And Verifica traduzione testo "Bescheide anzeigen mit Vollmacht"
    And Entro dentro la prima notifica
    And Verifica traduzione testo "Absender"
    And Verifica traduzione testo "Empfänger"
    And Verifica traduzione testo "Sendedatum"
    #  Raggiungere la sezione Notifiche delegate e verificarne la traduzione
    When Seleziona voce menu laterale "Bescheide mit Vollmacht"
    And Verifica traduzione testo "Bescheide anzeigen mit Vollmacht an Convivio Spa Du kannst sie nach IUN-Code und Sendedatum filtern"
  ##  Raggingere gestire una delega e verificarne la traduzione
    When Seleziona voce menu laterale "Vollmachten"
    And Verifica traduzione testo "Hier können die Bevollmächtigten des Unternehmens und deren Vollmachten verwaltet werden"
    And Verifica traduzione testo "Vollmachten des Unternehmens"
    And Nella pagina Deleghe si clicca su Delegati dall impresa
    And Nella sezione Delegati dell impresa click sul bottone aggiungi nuova delega
    And Verifica traduzione testo "Gib die Daten der natürlichen oder juristischen Person ein, der du eine Vollmacht zum Lesen deiner Bescheide erteilen möchtest"
    And Verifica traduzione testo "Natürliche Person"
    And Verifica traduzione testo "Juristische Person"
    And Verifica traduzione testo "Teile diesen Code mit der bevollmächtigten Person: Sie muss ihn bei der Annahme der Vollmacht eingeben"
##    Selezionare Stato della Piattaforma
    Then Seleziona voce menu laterale "Plattformstatus"
    And Verifica traduzione testo "Überprüft die Funktionsweise von SEND"
    And Verifica traduzione testo "Fehlerhistorie"