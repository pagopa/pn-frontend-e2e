Feature: PG - Eliminazione di una public key ruotata per un utente Amministratore Persona Giuridica

  @TestSuite
  @TA_PG_EliminazionePublicKey_QA_5312
  @integrazioneApi
  @apiB2BBilinguismo

  Scenario:PN-QA-5312  PG - Eliminazione di una public key ruotata per un utente Amministratore Persona Giuridica
    Given PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente public keys
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Cliccare sui tre puntini con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Cliccare su registra
    Then Cliccare sui tre puntini con stato "Ruotata"
    And  Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Verifica Pop-up Integrazione Api "con successo"



