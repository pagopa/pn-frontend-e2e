Feature: Atti scaricati con successo Destinatario persona giuridica

  Background: Accesso sezione Dati della notifica
    Given operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Documenti allegati della notifica e attestazioni opponibili a terzi clicca sul bottone con freccia
    Then la pagina Richiesta Atti sezione Dati della notifica è visualizzata correttamente

  @RADD
  @TestSuite

  Scenario: Atti scaricati con successo Destinatario
    When nella pagina atti-opponibili-terzi sezione Dati della notifica inserire codice IUN persona giuridica "RADD"
    And nella pagina atti-opponibili-terzi sezione Dati del destinatario selezionare persona giudridica
    And nella pagina atti-opponibili-terzi sezione Dati della notifica inserire la partita iva "RADD"
    And nella pagina atti-opponibili-terzi sezione Dati della notifica cliccare sul bottone Continua
    And la pagina atti-opponibili-terzi sezione Caricamento documenti è visualizzata correttamente
    And nella pagina atti-opponibili-terzi sezione Caricamento documenti caricare il documento di riconoscimento del destinario nel box uno Carica il documento di riconoscimento del destinatario
    And nella pagina atti-opponibili-terzi sezione Caricamento documenti cliccare sul bottone Continua
    And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni esegue il download dei documenti
    And nella pagina atti-opponibili-terzi sezione Allegati il bottone ho finito si abilita
    And nella pagina atti-opponibili-terzi sezione Allegati cliccare sul bottone home page
    And la Homepage RADD è visualizzata correttamente

#    And nella pagina atti-opponibili-terzi sezione Allegati e attestazioni cliccare sul bottone ho finito
#    And Si visualizza correttamente la pagina di conferma
#    Then nella pagina di conferma cliccare sul link dello storico
#    And la landing page si visualizza
#    And nella landing page cliccare sul tasto torna alla home
#    And la Homepage RADD è visualizzata correttamente
