Feature: PG -Eliminazione di una virtual key ruotata per un utente della PG con ruolo di amministratore di gruppo

#  @TestSuite
#  @TA_PG_AmministratoreDiGruppoEliminaVirtualKeyRuotata_QA_5335
#  #@bilinguismo
#  @NRT_Blocco_2
  #  Inglobato in 5331
  Scenario:PN-QA-5335  PG - Eliminazione di una virtual key ruotata per un utente della PG con ruolo di amministratore di gruppo
    Given Login Page persona giuridica viene visualizzata
    When Login con persona giuridica
      | user           | DanteAlighieri |
      | pwd            | test           |
      | ragioneSociale | Vita Nova Sas  |
    And Si clicca su prodotto
    And Clicca tasto Accedi OneTrust PG e PF
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

#    And Aggiornamento Pagina
    And Login con persona giuridica
      | user           | m.montessori       |
      | pwd            | test          |
      | ragioneSociale | Vita Nova Sas |
    And Si clicca su prodotto
    And Clicca tasto Accedi OneTrust PG e PF
#    Cliccando sulla CTA “Genera chiave personale”
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Pulisci ambiente virtual keys
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    #  verifica stati
    When Verifica stato Chiave Personale "Attiva"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
#    Ruota
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    When Verifica stato Chiave Personale "Attiva"
    And Verifica stato Chiave Personale "Ruotata"
#Elimina Virtual key Ruotata
    And Cliccare sui tre puntini Virtual key con stato "Ruotata"
    Then verifica tre puntini mostra di piu
      | delete | Elimina           |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Aspetta 5 secondi
    And Verifica Assenza stato Chiave Personale "Ruotata"