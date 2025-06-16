Feature: Visualizzazione sezione Integrazione API

  @TA_PG_VisualizzazioneIntegrazioneAPIVirtualKeyCensita_QA_5319
  @integrazioneApi_NRT
  #@bilinguismo
  @PG
  @TestSuite

  Scenario: PN-QA-5319 [DELEGANTE PG AMMINISTRATORE] - Amministratore PG visualizza sezione Integrazione API con Virtual Key censita
    # Reset ambiente di test
    Given Login Page persona giuridica viene visualizzata
    And Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl  |
    And Si clicca su prodotto
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
    And Logout da portale persona giuridica delegante
    # Creazione chiave virtuale per scenario
    And Login con persona giuridica
      | user           | GiuseppeUngaretti |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl   |
    And Si clicca su prodotto
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Verifica testo nel pop-up "La tua chiave personale"
    And Verifica testo nel pop-up "Puoi usarla per autenticarti in piattaforma e integrare SEND"
    And Verifica testo nel pop-up "Ok, ho capito"
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Logout da portale persona giuridica delegante
    # Esecuzione scenario
    When Login Page persona giuridica viene visualizzata
    And Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl  |
    And Si clicca su prodotto
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    Then Si visualizza correttamente la lista delle Api Key generate