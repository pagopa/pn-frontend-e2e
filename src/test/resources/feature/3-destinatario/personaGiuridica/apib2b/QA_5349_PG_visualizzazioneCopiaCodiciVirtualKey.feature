Feature: Visualizzazione sezione Integrazione API

  @TA_PG_VisualizzazioneCopiaCodiciVirtualKeyDaAmministratoreOOperatore_QA_5349
  @integrazioneApi
  @bilinguismo
  @PG
  @TestSuite

  Scenario: QA-5349 [DELEGANTE PG AMMINISTRATORE O REFERENTE OPERATIVO] - Visualizzazione e copia dei codici di una virtual key per un utente Amministratore PG o Operatore
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
    # Creazione chiave pubblica per scenario
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome        | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    # Creazione chiave virtuale per amministratore
    And Click su tasto Genera Chiave Personale
    And Verifica testo nel pop-up "La tua chiave personale"
    And Verifica testo nel pop-up "Puoi usarla per autenticarti in piattaforma e integrare SEND"
    And Verifica testo nel pop-up "Ok, ho capito"
    And Nel pop up visualizza cliccare sul tasto chiudi
    # Esecuzione scenario per amministratore
    Then Si visualizza correttamente la lista delle Api Key generate
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Nella pagina Api Key si visualizza il pop up visualizza Api Key
    And Da Visualizza codice si copia correttamente il campo Chiave Personale cliccando sul bottone di copia
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Logout da portale persona giuridica delegante
    # Creazione chiave virtuale per operatore
    And Login con persona giuridica
      | user           | GiuseppeUngaretti |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl   |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Verifica testo nel pop-up "La tua chiave personale"
    And Verifica testo nel pop-up "Puoi usarla per autenticarti in piattaforma e integrare SEND"
    And Verifica testo nel pop-up "Ok, ho capito"
    And Nel pop up visualizza cliccare sul tasto chiudi
    # Esecuzione scenario per Operatore
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce visualizza del menu Api Key
    And Nella pagina Api Key si visualizza il pop up visualizza Api Key
    And Da Visualizza codice si copia correttamente il campo Chiave Personale cliccando sul bottone di copia
    And Nel pop up visualizza cliccare sul tasto chiudi