Feature: Creazione chiave pubblica

  @TA_PG_OperatoreNonAbilitatoCreazioneChiavePubblica_QA_5317_5319
  @integrazioneApi
  @integrazioneApiPg1
  @apiKey
  #@bilinguismo
  @PG
  @TestSuite
  @NRT_Blocco_2
  Scenario: PN-QA-5317_5319 [REFERENTE OPERATIVO PG] - Utente Operatore non abilitato prova a censire una public key,
                                        Amministratore PG visualizza sezione Integrazione API con Virtual Key censita
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
    And Logout da portale persona giuridica delegante
    # Esecuzione scenario
    And Attesa 1 secondi
    When Login con persona giuridica
      | user           | GiuseppeUngaretti |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl   |
    And Si clicca su prodotto
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Nella pagina Integrazione API si controlla che non sia presente il bottone Genera chiave pubblica
    Then Nella sezione Integrazione API non si visualizza alcuna chiave "Per poter creare una chiave personale, un amministratore deve prima abilitare l’integrazione."

#    // proposta 5319

    And Logout da portale persona giuridica delegante
    And Attesa 1 secondi
    And Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl  |
    And Si clicca su prodotto
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    # Creazione chiave pubblica per scenario
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome        | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Logout da portale persona giuridica delegante
    # Creazione chiave virtuale per scenario
    And Attesa 1 secondi
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
    And Attesa 2 secondi
    And Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl  |
    And Si clicca su prodotto
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    Then Si visualizza correttamente la lista delle Api Key generate PG