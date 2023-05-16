Feature: Download Atti Destinatario

  Background: Accesso sezione Dati della notifica
    Given    operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Documenti allegati della notifica e attestazioni opponibili a terzi clicca sul bottone con freccia
    Then la pagina Richiesta Atti sezione Dati della notifica è visualizzata correttamente

  @TestSuite
  @Test1

  Scenario: Download Atti Destinatario
    When nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il codice IUN "DJWE"
    And nella pagina atti-opponibili-terzi sezione Dati della notifica è selezionato di default il Soggetto giuridico come Persona fisica
    And nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il Codice fiscale del destinatario "GNIGNI80A01H501R"
    And nella pagina atti-opponibili-terzi sezione Dati della notifica cliccare sul bottone Continua
    And la pagina atti-opponibili-terzi sezione Caricamento documenti è visualizzata correttamente
    And nella pagina atti-opponibili-terzi sezione Caricamento documenti caricare il documento di riconoscimento del destinario nel box uno Carica il documento di riconoscimento del destinatario
    And nella pagina atti-opponibili-terzi sezione Caricamento documenti cliccare sul bottone Continua
    And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni esegue il download dei documenti
    And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni nei box dei documenti scaricati è visibile la spunta verde
    And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni cliccare sul bottone ho finito
    Then Si visualizza correttamente la pagina di conferma
    And nella pagina di conferma cliccare sul bottone chiudi

