Feature: Atti scaricati con successo Delegato

  Background: Accesso sezione Dati della notifica
    Given operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Documenti allegati della notifica e attestazioni opponibili a terzi clicca sul bottone con freccia
    Then la pagina Richiesta Atti sezione Dati della notifica è visualizzata correttamente

  @TestSuite
  @RADD
    @Test4

  Scenario: Atti scaricati con successo Delegato
      When nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il codice IUN "DJWE"
      And nella pagina atti-opponibili-terzi sezione Dati della notifica è selezionato di default il Soggetto giuridico come Persona fisica
      And nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il Codice fiscale del destinatario "GNIGNI80A01H501R"
      And nella pagina atti-opponibili-terzi sezione Dati della notifica inserire il Codice fiscale del del delegato "MNLPLS80D26F205I"
      And nella pagina atti-opponibili-terzi sezione Dati della notifica cliccare sul bottone Continua
      And la pagina atti-opponibili-terzi sezione Caricamento documenti è visualizzata correttamente
      And nella pagina atti-opponibili-terzi sezione Caricamento documenti caricare il documento di riconoscimento del destinario nel box uno Carica il documento di riconoscimento del destinatario
      And nella pagina Richiesta Atti sezione Caricamento documenti caricare il documento di riconoscimento del delegato nel box due Carica il documento di riconoscimento del delegato
      And nella pagina Richiesta Atti sezione Caricamento documenti caricare il Modulo di delega nel box tre  Modulo di delega
      And nella pagina atti-opponibili-terzi sezione Caricamento documenti cliccare sul bottone Continua
      And la pagina Richiesta Atti sezione Allegati e attestazioni è visualizzata correttamente
      And nella pagina Richiesta Atti sezione Allegati e attestazioni è visualizzata la frase Assicurati di scaricare tutti i documenti
      And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni esegue il download dei documenti
      And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni nei box dei documenti scaricati è visibile la spunta verde
      And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni cliccare sul bottone ho finito
      Then nella pagina di conferma cliccare sul link dello storico
      And la landing page si visualizza
      And nella landing page cliccare sul tasto torna alla home
      And la Homepage RADD è visualizzata correttamente
