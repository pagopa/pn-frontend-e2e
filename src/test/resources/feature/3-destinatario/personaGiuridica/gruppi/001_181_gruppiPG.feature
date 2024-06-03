Feature: Si eseguono e si controllano tutte le operazioni che possono essere eseguite sui gruppi

  @TestSuite
  @TA_gruppiPG
  @gruppiPG
  Scenario: [TA-FE GRUPPI PERSONA GIURIDICA] - Si crea un gruppo per la persona giuridica e lo si modifica
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone Gruppi
    And Nella pagina gruppi si effettua la login tramite credenziali
      | user | DanteAlighieri |
      | pwd  | test           |
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
    And Aspetta 20 secondi
    Then Si visualizza il popup di conferma con la scritta "Gruppo modificato correttamente"
    And Si controlla che le modifiche siano state salvate
    And Logout da portale persona giuridica

  @TestSuite
  @gruppiPG
  Scenario: [TA-FE GRUPPI PERSONA GIURIDICA] - Si crea un gruppo per la persona giuridica, lo si sospende e lo si riattiva
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone Gruppi
    And Nella pagina gruppi si effettua la login tramite credenziali
      | user | DanteAlighieri |
      | pwd  | test           |
    And Si visualizza correttamente la pagina gruppi
    And Si clicca sul bottone Crea gruppo
    And Si visualizza correttamente la pagina crea gruppo
    Then Si compilano i campi per la creazione di un nuovo gruppo
      | nome        | Gruppo Test |
      | descrizione | descrizione |
    And Si controlla che il bottone conferma sia ablitato e lo si clicca
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test"
    And Si clicca sul bottone "Sospendi"
    And Si verifica che venga mostrato correttamente il popup di sospensione
    And Si clicca sul bottone "Annulla"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test"
    And Si clicca sul bottone "Sospendi"
    And Si clicca sul bottone "Sospendi"
    Then Si visualizza il popup di conferma con la scritta "Gruppo sospeso correttamente"
    And Nella pagina di descrizione del gruppo si visualizza la voce sospeso
    And Si clicca sul bottone "Riattiva"
    And Si verifica che venga mostrato correttamente il popup di riattivazione
    And Si clicca sul bottone "Annulla"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test"
    And Si clicca sul bottone "Riattiva"
    And Si clicca sul bottone "Riattiva"
    Then Si visualizza il popup di conferma con la scritta "Gruppo riattivato correttamente"
    And Logout da portale persona giuridica


  @TestSuite
  @gruppiPG
  Scenario: [TA-FE GRUPPI PERSONA GIURIDICA] - Si crea un gruppo per la persona giuridica, lo si duplica e lo si elimina
    Given PG - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica si clicca sul bottone Gruppi
    And Nella pagina gruppi si effettua la login tramite credenziali
      | user | DanteAlighieri |
      | pwd  | test           |
    And Si visualizza correttamente la pagina gruppi
    And Si clicca sul bottone Crea gruppo
    And Si visualizza correttamente la pagina crea gruppo
    Then Si compilano i campi per la creazione di un nuovo gruppo
      | nome        | Gruppo Test |
      | descrizione | descrizione |
    And Si controlla che il bottone conferma sia ablitato e lo si clicca
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test"
    And Si clicca sul bottone "Duplica"
    And Si verifica che venga mostrato correttamente il popup di duplicazione
    And Si clicca sul bottone "Annulla"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Gruppo Test"
    And Si clicca sul bottone "Duplica"
    And Si clicca sul bottone "Duplica"
    And Si verifica che la pagina di duplicazione del gruppo si visualizza correttamente
    And Si clicca sul bottone "Conferma"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Copia di Gruppo Test"
    And Si clicca sul bottone "Elimina"
    And Si verifica che venga mostrato correttamente il popup di eliminazione
    And Si clicca sul bottone "Annulla"
    And Si visualizza correttamente la pagina di riepilogo del gruppo "Copia di Gruppo Test"
    And Si clicca sul bottone "Elimina"
    And Si clicca sul bottone "Elimina"
    Then Si visualizza il popup di conferma con la scritta "Gruppo eliminato correttamente"
    And Si visualizza correttamente la pagina gruppi
    And Logout da portale persona giuridica