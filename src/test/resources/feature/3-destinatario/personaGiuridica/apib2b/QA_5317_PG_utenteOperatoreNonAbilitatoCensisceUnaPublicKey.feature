Feature: Creazione chiave pubblica

  @TA_PG_OperatoreNonAbilitatoCreazioneChiavePubblica_QA_5317
  @IntegrazioneAPIB2B
  @PG
  @TestSuite

  Scenario: QA-5305 [REFERENTE OPERATIVO PG] - Utente Operatore non abilitato prova a censire una public key
    # Reset ambiente di test
    Given Login Page persona giuridica viene visualizzata
    And Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl  |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente public keys
    And Logout da portale persona giuridica delegante
    # Esecuzione scenario
    When Login con persona giuridica
      | user           | GiuseppeUngaretti |
      | pwd            | test           |
      | ragioneSociale | DivinaCommedia Srl   |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
    And Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Nella pagina Integrazione API si controlla che non sia presente il bottone Genera chiave pubblica
    Then Nella pagina Integrazione API si visualizza il messaggio di alert "Le chiavi personali non sono utilizzabili perché l’integrazione è stata disabilitata. Per usarle, chiedi a un amministratore di abilitare l’integrazione."