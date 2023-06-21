Feature: Failure primo step Destinatario

  Background: Accesso sezione Dati della notifica
    Given operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Documenti allegati della notifica e attestazioni opponibili a terzi clicca sul bottone con freccia
    Then la pagina Richiesta Atti sezione Dati della notifica è visualizzata correttamente

  Scenario: Failure primo step Destinatario
    When nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il codice IUN "RADD"
    And nella pagina atti-opponibili-terzi sezione Dati della notifica è selezionato di default il Soggetto giuridico come Persona fisica
    And nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il Codice fiscale del destinatario errore primo step "RADD"
    And nella pagina atti-opponibili-terzi sezione Dati della notifica cliccare sul bottone Continua
    Then nella pagina Richiesta Atti sezione Dati della notifica visualizzare la frase di errore primo step

