Feature: Si eseguono e si controllano tutte le operazioni che possono essere eseguite sui gruppi

  @TestSuite
  @gruppiPG

  Scenario: [TA-FE GRUPPI PERSONA GIURIDICA] - Si crea un gruppo per la persona giuridica, lo si modifica e lo si elimina
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone Gruppi
    And Nella pagina gruppi si effettua la login tramite credenziali
      | user | DanteAlighieri |
      | pwd  | test           |
    And Aspetta 5 secondi
    And Si visualizza correttamente la pagina gruppi
    And Si clicca sul bottone Crea gruppo
    And Si visualizza correttamente la pagina crea gruppo
    Then Si compilano i campi per la creazione di un nuovo gruppo
      | nome        | Gruppo Test |
      | descrizione | descrizione |
    And Si controlla che il bottone conferma sia ablitato e lo si clicca
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test"
    And Si clicca sul bottone "Modifica"
    And Si controlla che i campi del gruppo sono modificabili
    And Si modifica il campo "descrizione" inserendo "Nuova descrizione"
    And Si controlla che il bottone conferma sia ablitato e lo si clicca
    Then Si visualizza il popup di conferma con la scritta "Gruppo modificato correttamente"
    And Si controlla che le modifiche siano state salvate
    And Si clicca sul bottone "Sospendi"
    And Si verifica che venga mostrato correttamente il popup di sospensione
    And Si clicca sul bottone "Annulla"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test"
    And Si clicca sul bottone "Sospendi"
    And Si clicca sul bottone "Sospendi" del popup
    Then Si visualizza il popup di conferma con la scritta "Gruppo sospeso correttamente"
    And Nella pagina di descrizione del gruppo si visualizza la voce sospeso
    And Si clicca sul bottone "Riattiva"
    And Si verifica che venga mostrato correttamente il popup di riattivazione
    And Si clicca sul bottone "Annulla"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test" sospeso
    And Si clicca sul bottone "Riattiva"
    And Si clicca sul bottone "Riattiva" del popup
    Then Si visualizza il popup di conferma con la scritta "Gruppo riattivato correttamente"
    And Si clicca sul bottone "Duplica"
    And Si verifica che venga mostrato correttamente il popup di duplicazione
    And Si clicca sul bottone "Annulla"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test"
    And Si clicca sul bottone "Duplica"
    And Si clicca sul bottone "Duplica" del popup
    And Si verifica che la pagina di duplicazione del gruppo si visualizza correttamente
    And Si clicca sul bottone "Conferma"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Copia di Gruppo Test"
    And Si clicca sul bottone "Elimina"
    And Si verifica che venga mostrato correttamente il popup di eliminazione
    And Si clicca sul bottone "Annulla"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Copia di Gruppo Test"
    And Si clicca sul bottone "Elimina"
    And Si clicca sul bottone "Elimina" del popup
    Then Si visualizza il popup di conferma con la scritta "Gruppo eliminato correttamente"
    And Si visualizza correttamente la pagina gruppi
    And Si "Elimina" il gruppo "Gruppo Test" creato inizialmente
    Then Si visualizza il popup di conferma con la scritta "Gruppo eliminato correttamente"
    And Si visualizza correttamente la pagina gruppi
    And Logout da portale persona giuridica