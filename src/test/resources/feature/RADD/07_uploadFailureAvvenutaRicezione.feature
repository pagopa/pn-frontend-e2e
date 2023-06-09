Feature: Download Avvisi Destinatario upload failure

  Background: Accesso sezione Dati del destinatario
    Given operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Avvisi di avvenuta ricezione clicca sul bottone Vai a Avvisi di avvenuta ricezione
    Then la pagina Avvisi di avvenuta ricezione sezione Dati del destinatario è visualizzata correttamente

  @RADD
  @test7

  Scenario: Download Avvisi Destinatario upload failure
    When nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario il Soggetto giuridico e selezionato di default come Persona fisica
    And nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario inserire il Codice fiscale del destinatario "RADD"
    And nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario cliccare sul bottone Continua
    And la pagina Avvisi di avvenuta ricezione sezione Caricamento documenti è visualizzata correttamente
    And nella pagina Avvisi di avvenuta ricezione sezione Caricamento documenti caricare un documento con estenzione non valida
    Then Si visualizza un messaggio di errore