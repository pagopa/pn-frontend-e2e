Feature: Atti scaricati con successo Destinatario

  Background: Accesso sezione Dati della notifica
    Given operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Documenti allegati della notifica e attestazioni opponibili a terzi clicca sul bottone con freccia
    Then la pagina Richiesta Atti sezione Dati della notifica è visualizzata correttamente

  @RADD
  @test3

  Scenario: Atti scaricati con successo Destinatario
    When nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il codice IUN "RADD"
    And nella pagina atti-opponibili-terzi sezione Dati della notifica è selezionato di default il Soggetto giuridico come Persona fisica
    And nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il Codice fiscale del destinatario "RADD"
    And nella pagina atti-opponibili-terzi sezione Dati della notifica cliccare sul bottone Continua
    And la pagina atti-opponibili-terzi sezione Caricamento documenti è visualizzata correttamente
    And nella pagina atti-opponibili-terzi sezione Caricamento documenti caricare il documento di riconoscimento del destinario nel box uno Carica il documento di riconoscimento del destinatario
    And nella pagina atti-opponibili-terzi sezione Caricamento documenti cliccare sul bottone Continua
    And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni esegue il download dei documenti
    And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni nei box dei documenti scaricati è visibile la spunta verde
    And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni cliccare sul bottone ho finito
    And Si visualizza correttamente la pagina di conferma RADD
    Then nella pagina di conferma cliccare sul link dello storico
    And la landing page si visualizza
    And nella landing page cliccare sul tasto torna alla home
    And la Homepage RADD è visualizzata correttamente
