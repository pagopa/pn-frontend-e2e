Feature: PG - Utente della PG con ruolo di amministratore di gruppo censisce una virtual key

  @TestSuite
  @TA_PG_AmministratoreDiGruppoCensisceVirtualKey_QA_5329
  @integrazioneApi
  @apiKey
  #@bilinguismo

  Scenario:PN-QA-5329  PG - Utente della PG con ruolo di amministratore di gruppo censisce una virtual key
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Vita Nova Sas  |
    And Si clicca su prodotto
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

#  Entro come Amm. Gruppo PG Vita Nova Sas
    And Aggiornamento Pagina
    And Login con persona giuridica
      | user           | m.montessori  |
      | pwd            | test          |
      | ragioneSociale | Vita Nova Sas |
    And Si clicca su prodotto
#    Cliccando sulla CTA “Genera chiave personale”
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente virtual keys
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Verifica testo nel pop-up "La tua chiave personale"
    And Verifica testo nel pop-up "Puoi usarla per autenticarti in piattaforma e integrare SEND"
    And Verifica testo nel pop-up "Ok, ho capito"
    And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
    Then Verifica stato Chiave Personale "Attiva"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Integrazione API si controlla che NON sia presente il bottone Genera chiave personale














