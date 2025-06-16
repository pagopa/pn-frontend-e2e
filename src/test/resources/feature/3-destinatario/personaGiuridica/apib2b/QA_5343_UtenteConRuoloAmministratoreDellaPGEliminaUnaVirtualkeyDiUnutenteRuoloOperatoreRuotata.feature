Feature: PG - Utente con ruolo Amministratore della PG elimina una virtual key ruotata di un utente con ruolo di operatore

  @TestSuite
  @TA_PG_AmministratorePG_EliminaVirtualKeyDiUnUtenteRuoloOperatoreRuotata_QA_5343
  @integrazioneApi
  @apiKey
  #@bilinguismo
  @NRT_Blocco_2
  Scenario:PN-QA-5343  PG - Utente con ruolo Amministratore della PG elimina una virtual key ruotata di un utente con ruolo di operatore
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Vita Nova Sas  |
    And Si clicca su prodotto
#  Censire una chiave pubblica per un Operatore
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
##    **************************************************************************
    And Pulisci ambiente virtual keys
    And Pulisci ambiente public keys
# tasto Registra chiave pubblica
#    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
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
    And Si clicca su prodotto
#    Cliccando sulla CTA “Genera chiave personale”
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
# #    **************************************************************************
#    And Pulisci ambiente virtual keys
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    When Verifica stato Chiave Personale "Attiva"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Verifica stato Chiave Personale "Ruotata"
    And Aggiornamento Pagina
    And Logout da portale persona giuridica delegante
#  Entro come Amministratore
    And Aggiornamento Pagina
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Vita Nova Sas  |
    And Si clicca su prodotto
#  Censire una chiave pubblica per un Operatore
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Verifica stato Chiave Personale "Attiva"
    And Cliccare sui tre puntini Virtual key con stato "Ruotata"
    And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Aspetta 5 secondi
    Then Verifica Assenza stato Chiave Personale "Ruotata"
