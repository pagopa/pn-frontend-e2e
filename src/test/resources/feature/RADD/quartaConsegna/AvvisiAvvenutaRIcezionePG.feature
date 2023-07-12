Feature: Download Avvisi Destinatario

  Background: Accesso sezione Dati del destinatario
    Given operatore è loggato
    And la Homepage RADD è visualizzata correttamente
    When nella Homepage RADD sezione Avvisi di avvenuta ricezione clicca sul bottone Vai a Avvisi di avvenuta ricezione
    Then la pagina Avvisi di avvenuta ricezione sezione Dati del destinatario è visualizzata correttamente

    #Dati mancanti

  Scenario: Download Avvisi Destinatario
    When nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario selezionare persona giudridica
    And nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario inserire la partita IVA del destinatario "RADD"
    And nella pagina Avvisi di avvenuta ricezione sezione Dati del destinatario cliccare sul bottone Continua
    And la pagina Avvisi di avvenuta ricezione sezione Caricamento documenti è visualizzata correttamente
    And nella pagina Avvisi di avvenuta ricezione sezione Caricamento documenti caricare il documento di riconoscimento del destinario nel box 1 Carica il documento di riconoscimento del destinatario
    And nella pagina Avvisi di avvenuta ricezione sezione Caricamento documenti cliccare sul bottone Continua
    And la pagina Avvisi di avvenuta ricezione sezione Avvisi di avvenuta ricezione è visualizzata correttamente
    And nella pagina Avvisi di avvenuta ricezione sezione Avvisi di avvenuta ricezione esegue il download dei documenti
    And nella pagina Avvisi di avvenuta ricezione sezione Avvisi il bottone ho finito si abilita
    And nella pagina Avvisi di avvenuta ricezione sezione Avvisi cliccare sul bottone home page
    And la Homepage RADD è visualizzata correttamente

#    And nella pagina Avvisi di avvenuta ricezione sezione Avvisi di avvenuta ricezione cliccare sul bottone Ho finito
#    And Si visualizza correttamente la pagina di conferma
#    Then nella pagina di conferma cliccare sul link dello storico
#    And la landing page si visualizza
#    And nella landing page cliccare sul tasto torna alla home
#    And la Homepage RADD è visualizzata correttamente
