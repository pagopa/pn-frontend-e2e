Feature: PG - Rotazione di una public key per un utente Amministratore Persona Giuridica con già una public key ruotata

  @TestSuite
  @TA_PG_RuotaPublicKeyRuotata_QA_5315
  @integrazioneApi
  @integrazioneApiDelegato
  #@bilinguismo
  @NRT_Blocco_2
  Scenario:PN-QA-5315_5318_5320_5321_5322_5323_5324_5325_5326  PG - Rotazione di una public key per un utente Amministratore Persona Giuridica con già una public key ruotata,
                                      Utente Amministratore Persona Giuridica censisce una virtual key,
                                      Rotazione di una virtual key attiva per un utente Amministratore Persona Giuridica,
                                      Blocco di una virtual key attiva per un utente Amministratore Persona Giuridica,
                                      Eliminazione di una virtual key ruotata per un utente Amministratore Persona Giuridica,
                                      Eliminazione di una virtual key bloccata per un utente Amministratore Persona Giuridica,
                                      Rotazione di una virtual key per un utente Amministratore Persona Giuridica con già una virtual key ruotata.
                                      Virtual key attiva, blocca la virtual key e registra una nuova virtual key che verrà a sua volta ruotata e ne verrà registrata una nuova.

    Given PG - Si effettua la login tramite token exchange come "delegato", e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche persona giuridica click sul bottone Integrazione API
    And Refresh pagina
    And Attesa 1 secondi
    And Pulisci ambiente public keys
    And Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave pubblica
    And Nella pagina Integrazione API si clicca sul bottone Genera chiave pubblica
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Cliccare sui tre puntini con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Nella sezione Registra chiave pubblica si inseriscono i dati della chiave pubblica
      | nome | Chiave- |
    And Cliccare su registra
    And Si visualizza correttamente la sezione Ottieni Parametri
    And Cliccare su registra
    And Cliccare sui tre puntini con stato "Attiva"
    Then verifica tre puntini mostra di piu
      | blocca | Blocca            |
      | view   | Visualizza codice |

    And Refresh pagina

#  5318
    And Pulisci ambiente virtual keys
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Verifica testo nel pop-up "La tua chiave personale"
    And Verifica testo nel pop-up "Puoi usarla per autenticarti in piattaforma e integrare SEND"
    And Verifica testo nel pop-up "Ok, ho capito"
    And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
    Then Verifica stato Chiave Personale "Attiva"

#  5320
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    When verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Verifica stato Chiave Personale "Attiva"
    And Verifica stato Chiave Personale "Ruotata"

#  5321
    And Pulisci ambiente virtual keys
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
    And Verifica stato Chiave Personale "Attiva"

    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    When verifica tre puntini mostra di piu
      | ruota  | Ruota             |
      | blocca | Blocca            |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    Then Verifica stato Chiave Personale "Bloccata"

#  5322
#    And Pulisci ambiente virtual keys
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
    And Verifica stato Chiave Personale "Attiva"
# ruoto la key
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Cliccare sui tre puntini Virtual key con stato "Ruotata"
    Then verifica tre puntini mostra di piu
      | delete | Elimina           |
      | view   | Visualizza codice |
    And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Nella pop up cliccare sul tasto conferma

    And Refresh pagina

#  5323
    And Pulisci ambiente virtual keys

    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
    And Verifica stato Chiave Personale "Attiva"
# ruoto la key
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And Cliccare sui tre puntini Virtual key con stato "Bloccata"
    And Nella pagina Api Key si clicca sulla voce Elimina del menu Api Key
    And Verifica testo nel pop-up "Elimina chiave"
    And Verifica testo nel pop-up "Se elimini definitivamente la chiave"
    And Verifica testo nel pop-up "Annulla"
    And Nella pop up cliccare sul tasto conferma
    Then Nella sezione Integrazione API non si visualizza alcuna chiave "Non è stata ancora generata nessuna chiave personale per la tua impresa"

  #  5324
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
#  verifica stati
    And Verifica stato Chiave Personale "Attiva"

    And Cliccare sui tre puntini Virtual key con stato "Attiva"

    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma
    And  Verifica stato Chiave Personale "Bloccata"

    Then Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Verifica stato Chiave Personale "Attiva"

    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | ruota | Ruota             |
      | view  | Visualizza codice |

    And Refresh pagina
#  5325
    And Pulisci ambiente virtual keys
# Inserire una chiave Attiva
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Verifica stato Chiave Personale "Attiva"


    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma

    Then Verifica stato Chiave Personale "Attiva"
    And Verifica stato Chiave Personale "Ruotata"

    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And verifica tre puntini mostra di piu
      | blocca | Blocca            |
      | view   | Visualizza codice |

    And Refresh pagina
#  5326
    And Pulisci ambiente virtual keys
    And Attesa 1 secondi
# Inserire una chiave Attiva
    When Nella pagina Integrazione API si controlla sia presente il bottone Genera chiave personale
    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Verifica stato Chiave Personale "Attiva"

    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce blocca del menu Api Key
    And Nella pop up cliccare sul tasto conferma

    And Click su tasto Genera Chiave Personale
    And Nel pop up visualizza cliccare sul tasto chiudi
    And Cliccare sui tre puntini Virtual key con stato "Attiva"
    And Nella pagina Api Key si clicca sulla voce ruota del menu Api Key
    And Nella pop up cliccare sul tasto conferma

    Then Verifica stato Chiave Personale "Attiva"
    And Verifica stato Chiave Personale "Bloccata"
    And Verifica stato Chiave Personale "Ruotata"