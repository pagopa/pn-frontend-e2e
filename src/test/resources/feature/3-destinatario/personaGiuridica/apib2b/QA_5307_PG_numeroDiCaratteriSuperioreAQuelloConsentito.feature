Feature: PG -Utente Amministratore Persona Giuridica prova a censire una chiave pubblica per la Persona Giuridica inserendo nel campo “nome” e nel campo “Inserisci il valore della chiave” un numero di caratteri superiore a quello consentito

  @TA_PG_NumeroDiCampiSuperiori_QA_5307
  @integrazioneApi
  @integrazioneApiDelegato
  @NRT_Blocco_2
  Scenario:PN-QA-5307_5309_5311_5314  PG - Utente Amministratore Persona Giuridica prova a censire una chiave pubblica per la Persona Giuridica inserendo nel campo “nome” e nel campo “Inserisci il valore della chiave” un numero di caratteri superiore a quello consentito,
                            Rotazione di una public key attiva per un utente Amministratore Persona Giuridica,
                            Blocco di una public key attiva per un utente Amministratore Persona Giuridica,
                            Eliminazione di una public key ruotata per un utente Amministratore Persona Giuridica,
                            Eliminazione di una public key bloccata per un utente Amministratore Persona Giuridica,
            Blocco di una public key per un utente Amministratore Persona Giuridica con già una public key bloccata

    Given PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente public keys
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica con numero di caratteri superiori
    Then Verifica messaggio Nome di errore "Scrivi massimo 254 caratteri"
    And Verifica messaggio PublicKey di errore "Scrivi massimo 500 caratteri"
    And Verifica tasto registra disabilitato


    And Torna indietro
    And Nella section cliccare sul tasto esci
#  5309
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
    And Verifica Pop-up Integrazione Api "con successo"
    And Cliccare su registra
    Then Verifica stato "Attiva"
    And Verifica stato "Ruotata"
#  5311
    And Cliccare sui tre puntini con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Verifica Pop-up Integrazione Api "con successo"
    Then Verifica stato "Bloccata"
#  5312
    Then Cliccare sui tre puntini con stato "Ruotata"
    And  Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Verifica Pop-up Integrazione Api "con successo"
#  5313
    And Cliccare sui tre puntini con stato "Bloccata"
    Then  Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Nella sezione Integrazione API non si visualizza alcuna chiave "Ancora nessuna chiave pubblica"
#  5314
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Cliccare sui tre puntini con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Cliccare sui tre puntini con stato "Attiva"
    Then verifica tre puntini mostra di piu
      | ruota | Ruota             |
      | view  | Visualizza codice |