Feature: PG - Utente Amministratore Persona Giuridica con public key attiva, blocca la public key e registra una nuova public key che verrà a sua volta ruotata e ne verrà registrata una nuova

  @TestSuite
  @TA_PG_StatoAttivaRuotataBloccata_QA_5316
  @integrazioneApi_NRT
  #@bilinguismo

  Scenario:PN-QA-5315  PG - Utente Amministratore Persona Giuridica con public key attiva, blocca la public key e registra una nuova public key che verrà a sua volta ruotata e ne verrà registrata una nuova
    Given PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente public keys
# tasto Registra chiave pubblica
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
#  chiave registrata
    And Cliccare sui tre puntini con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
  # tasto Registra chiave pubblica
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
#  Ruota la chiave
    And Cliccare sui tre puntini con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
#  verifica stati
    Then Verifica stato "Attiva"
    And Verifica stato "Ruotata"
    And Verifica stato "Bloccata"













