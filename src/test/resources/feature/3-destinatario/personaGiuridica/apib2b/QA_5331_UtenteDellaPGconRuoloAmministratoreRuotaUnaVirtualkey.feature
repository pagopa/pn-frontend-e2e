Feature: PG - Utente della PG con ruolo di amministratore di gruppo ruota una virtual key

  @TestSuite
  @TA_PG_AmministratoreDiGruppoRuotaVirtualKey_QA_5331
  @integrazioneApi
  @integrazioneApiPg2
  #@bilinguismo
  @NRT_Blocco_2
  Scenario:PN-QA-5331_5329_5333_5335_5337_5341_5339  PG - Utente della PG con ruolo di amministratore di gruppo ruota una virtual key,
                          Utente della PG con ruolo di amministratore di gruppo censisce una virtual key,
                          Eliminazione di una virtual key ruotata per un utente della PG con ruolo di amministratore di gruppo,
                          Eliminazione di una virtual key bloccata per un utente della PG con ruolo di amministratore di gruppo,
                          Utente della PG con ruolo di amministratore di gruppo prova a ruotare una virtual key con una virtual key già ruotata,
                         Utente della PG con ruolo di amministratore di gruppo prova a bloccare una virtual key con una virtual key già bloccata
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

#  Entro come operatore
    And Attesa 2 secondi

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
#5329
    And Verifica testo nel pop-up "La tua chiave personale"
    And Verifica testo nel pop-up "Puoi usarla per autenticarti in piattaforma e integrare SEND"
    And Verifica testo nel pop-up "Ok, ho capito"

  # 5331

    And Nel pop up visualizza cliccare sul tasto chiudi

    #  verifica stati
    When Verifica stato Chiave Personale "Attiva"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
#   daverificare in caso inserire di nuovo verifica tre puntini mostra di piu
    And Nella pagina Integrazione API si controlla che NON sia presente il bottone Genera chiave personale

    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Verifica stato Chiave Personale "Attiva"
    And Verifica stato Chiave Personale "Ruotata"

#    5341
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | blocca | Blocca            |
      | view   | Visualizza codice |

    And Refresh pagina

#    5335
    And Cliccare sui tre puntini Virtual key con stato "Ruotata"
    Then verifica tre puntini mostra di piu
      | delete | Elimina           |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Aspetta 5 secondi
    And Verifica Assenza stato Chiave Personale "Ruotata"

#    5333
    And Pulisci ambiente virtual keys
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    #  verifica stati
    When Verifica stato Chiave Personale "Attiva"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Verifica stato Chiave Personale "Bloccata"

#    5337
    When Verifica stato Chiave Personale "Bloccata"
    And Cliccare sui tre puntini Virtual key con stato "Bloccata"
    And verifica tre puntini mostra di piu
      | delete | Elimina            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Nella sezione Integrazione API non si visualizza alcuna chiave "Non è stata ancora generata nessuna chiave personale per la tua impresa"

#    5339
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
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Verifica stato Chiave Personale "Attiva"
    And Verifica stato Chiave Personale "Bloccata"
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | view   | Visualizza codice |