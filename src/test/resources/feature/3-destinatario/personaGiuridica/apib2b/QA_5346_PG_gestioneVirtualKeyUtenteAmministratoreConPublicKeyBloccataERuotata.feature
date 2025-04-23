Feature: Visualizzazione sezione Integrazione API

  @TA_PG_VerificaAzioniUtenteAmministratoreVirtualKeyConPublicKeyRuotataEBloccata_QA_5346
  @integrazioneApi
  #@bilinguismo
  @PG
  @TestSuite

  Scenario: QA-5346 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG può gestire chiavi virtuali (creazione, rotazione, blocco, eliminazione) con public key ruotata e bloccata
    # Reset ambiente di test
    Given Login Page persona giuridica viene visualizzata
    And Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl  |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente virtual keys
    And Pulisci ambiente public keys
    # Creazione chiave 1 per scenario
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome        | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Verifica stato "Attiva"
    # Rotazione chiave 1
    And Cliccare sui tre puntini con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    # Creazione chiave 2 per scenario
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Verifica stato "Attiva"
    # Blocco chiave 2
    And Cliccare sui tre puntini con stato "Attiva"
    And verifica tre puntini mostra di piu
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Verifica stato "Bloccata"
    And Logout da portale persona giuridica delegante
    # Esecuzione scenario
    And Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl  |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Verifica testo nel pop-up "La tua chiave personale"
    And Verifica testo nel pop-up "Puoi usarla per autenticarti in piattaforma e integrare SEND"
    And Verifica testo nel pop-up "Ok, ho capito"
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Verifica stato Chiave Personale "Attiva"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Nella pagina Api Key si visualizza il pop up visualizza Api Key
    And Da Visualizza codice si copia correttamente il campo Chiave Personale cliccando sul bottone di copia
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Verifica stato Chiave Personale "Attiva"
    And Verifica stato Chiave Personale "Ruotata"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Verifica stato Chiave Personale "Bloccata"
    And Cliccare sui tre puntini Virtual key con stato "Bloccata"
    And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Verifica testo nel pop-up "Elimina chiave"
    And Verifica testo nel pop-up "Se elimini definitivamente la chiave"
    And Verifica testo nel pop-up "Annulla"
    And Nella pop up cliccare sul tasto conferma
    And Logout da portale persona giuridica delegante