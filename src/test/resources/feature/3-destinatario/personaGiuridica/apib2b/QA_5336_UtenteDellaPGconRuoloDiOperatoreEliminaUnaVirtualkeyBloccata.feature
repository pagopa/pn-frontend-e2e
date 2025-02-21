Feature: PG -Eliminazione di una virtual key bloccata per un utente della PG con ruolo di operatore

  @TestSuite
  @TA_PG_OperatoreEliminaVirtualKeyBloccata_QA_5336
  @integrazioneApi
  @apiB2BBilinguismo

  Scenario:PN-QA-5336  PG - Eliminazione di una virtual key bloccata per un utente della PG con ruolo di operatore
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Vita Nova Sas  |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
#  Censire una chiave pubblica per un Operatore
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
    And Aggiornamento Pagina
    And Logout da portale persona giuridica delegante
#  Entro come operatore
    And Aggiornamento Pagina
    And Login con persona giuridica
      | user           | n.lotti       |
      | pwd            | test          |
      | ragioneSociale | Vita Nova Sas |
    And Si clicca su prodotto "//div[contains(@class, 'MuiCard-root') and .//h6[contains(text(), 'TEST')]]//button"
#    Cliccando sulla CTA “Genera chiave personale”
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente virtual keys
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    #  verifica stati
    When Verifica stato Chiave Personale "Attiva"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    When Verifica stato Chiave Personale "Bloccata"
    And Cliccare sui tre puntini Virtual key con stato "Bloccata"
    And verifica tre puntini mostra di piu
      | delete | Elimina            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Nella sezione Integrazione API non si visualizza alcuna chiave "Non è stata ancora generata nessuna chiave personale per la tua impresa"
