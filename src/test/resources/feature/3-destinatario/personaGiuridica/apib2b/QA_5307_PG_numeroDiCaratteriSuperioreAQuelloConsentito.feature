Feature: PG -Utente Amministratore Persona Giuridica prova a censire una chiave pubblica per la Persona Giuridica inserendo nel campo “nome” e nel campo “Inserisci il valore della chiave” un numero di caratteri superiore a quello consentito

  @TestSuite
  @TA_PG_NumeroDiCampiSuperiori_QA_5307
  @integrazioneApi
  @integrazioneApiDelegato
  #@bilinguismo
  @NRT_Blocco_2
  Scenario:PN-QA-5307  PG - Utente Amministratore Persona Giuridica prova a censire una chiave pubblica per la Persona Giuridica inserendo nel campo “nome” e nel campo “Inserisci il valore della chiave” un numero di caratteri superiore a quello consentito
    Given PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente public keys
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con numero di caratteri superiori
    Then Verifica messaggio Nome di errore "Scrivi massimo 254 caratteri"
    And Verifica messaggio PublicKey di errore "Scrivi massimo 500 caratteri"
    And Verifica tasto registra disabilitato


