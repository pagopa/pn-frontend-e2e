Feature: PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - DE

  @TestSuite
  @TA_multiLinguaTedesco_QA5296
  @multiLingua
  @NRT_1
  Scenario: PN-QA5296 - PF - Verifica traduzione presente nel passaggio da pagina di Login a portale SEND - DE
    Given Login Page persona fisica test viene visualizzata
    When Login con persona fisica scelta lingua
      | user         | cesare                 |
      | pwd          | password123            |
      | name         | Gaio Giulio            |
      | familyName   | Cesare                 |
      | fiscalNumber | TINIT-CSRGGL44L13H501E |
      | lingua       | Tedesco |
    And Refresh pagina
    And Aspetta 2 secondi
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
#  Raggiungere la sezione deleghe e verificarne la traduzione
    When Seleziona voce menu laterale "Vollmachten"
    And Verifica traduzione testo "Hier können die Bevollmächtigten des Unternehmens und deren Vollmachten verwaltet werden"
    And Verifica traduzione testo "Deine Bevollmächtigten"
    And Verifica traduzione testo "Deine Vollmachten"
    And Verifica traduzione testo "Eine Vollmacht hinzufügen"
  #  Navigare nella sezione Stato della piattaforma e verificarne le traduzioni
    Then Seleziona voce menu laterale "Plattformstatus"
    And Verifica traduzione testo "Überprüft die Funktionsweise von SEND, zeigt den Verlauf der Fehlfunktionen an und lädt die entsprechenden Bescheinigungen herunter, die gegenüber Dritten angefochten werden können."