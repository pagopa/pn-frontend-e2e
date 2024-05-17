Feature:Controllo dati notifica con pagamento

  @ControlloNotificaConPagamento
  Scenario:[NOTIFICA-ANNULLATA CON PAGAMENTO AFFETTUATO] Verifica testo rimborso su notifica pagata e successivamente annullata
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "DETN-WPNP-EPNJ-202405-D-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "DETN-WPNP-EPNJ-202405-D-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica annullata persona fisica
    And Si controlla testo in box pagamento
      | xpath    | //div[@data-testid='cancelledAlertPayment']|
      | isTrue    | true         |
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento1
  Scenario:[NOTIFICA- AVVISO PAGO-PA COSTI INCLUSI] Verifica testo avviso pago pa e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "TLRP-MGWN-MUXG-202405-J-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "TLRP-MGWN-MUXG-202405-J-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    # il parametro isTrue é utilizzato per verificare se un elemento deve esistere o no
    And Si controlla testo in box pagamento
      | xpath    | //p[@data-testid='apply-costs-caption']|
      | isTrue    | true         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-pagoPA-notice-button']|
      | isTrue    | true         |
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento2
  Scenario:[NOTIFICA- AVVISO PAGO-PA COSTI NON INCLUSI] Verifica testo avviso pago pa e notifica senza costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "JGMW-UHZX-JWXD-202405-P-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "JGMW-UHZX-JWXD-202405-P-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    # il parametro isTrue é utilizzato per verificare se un elemento deve esistere o no
    And Si controlla testo in box pagamento
      | xpath    | //p[@data-testid='apply-costs-caption']|
      | isTrue    | false         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-pagoPA-notice-button']|
      | isTrue    | true         |
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento3
  Scenario:[NOTIFICA- F24 COSTI INCLUSI] Verifica testo f24 e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "YKNL-AMKL-UXPD-202405-M-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "YKNL-AMKL-UXPD-202405-M-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    # il parametro isTrue é utilizzato per verificare se un elemento deve esistere o no
    And Si controlla testo in box pagamento
      | xpath    | //p[@data-testid='f24-apply-costs-caption']|
      | isTrue    | false         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-f24-button']|
      | isTrue    | true         |
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento4
  Scenario:[NOTIFICA- F24 COSTI NON INCLUSI] Verifica testo f24 e notifica senza costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "GQRP-QZRG-KGZM-202405-E-1 "
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "GQRP-QZRG-KGZM-202405-E-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    # il parametro isTrue é utilizzato per verificare se un elemento deve esistere o no
    And Si controlla testo in box pagamento
      | xpath    | //p[@data-testid='f24-apply-costs-caption']|
      | isTrue    | true         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-f24-button']|
      | isTrue    | true         |
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento5
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA COSTI INCLUSI] Verifica testo avviso pago pa e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "HPJD-GRJV-NGDL-202405-A-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "HPJD-GRJV-NGDL-202405-A-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath    | //span[contains(text(),"Almeno un destinatario ha letto la notifica.")]|
    # il parametro isTrue é utilizzato per verificare se un elemento deve esistere o no
    And Si controlla testo in box pagamento
      | xpath    | //p[@data-testid='apply-costs-caption']|
      | isTrue    | true         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-pagoPA-notice-button']|
      | isTrue    | true         |
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento5
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA COSTI NON INCLUSI] Verifica testo avviso pago pa e notifica senza costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "GKDW-YEZE-DNPL-202405-Z-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "GKDW-YEZE-DNPL-202405-Z-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath    | //span[contains(text(),"L'invio della notifica è terminato in quanto un recapito di almeno un destinatario è valido.")]|
    # il parametro isTrue é utilizzato per verificare se un elemento deve esistere o no
    And Si controlla testo in box pagamento
      | xpath    | //p[@data-testid='apply-costs-caption']|
      | isTrue    | false         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-pagoPA-notice-button']|
      | isTrue    | true         |
    And Logout da portale persona fisica

  @ControlloNotificaConPagamento6
  Scenario:[NOTIFICA-MULTIDESTINATARIO- AVVISO PAGO-PA E F24 COSTI INCLUSI] Verifica testo avviso pago pa con anche f24 e notifica con costi inclusi
    Given PF - Si effettua la login tramite token exchange come "delegante", e viene visualizzata la dashboard
    And Nella pagina Piattaforma Notifiche di PF si visualizzano correttamente i filtri di ricerca
    And Nella pagina Piattaforma Notifiche PF si filtra per codice IUN "ZVJN-QWEA-GLPY-202405-A-1"
    And Cliccare sul bottone Filtra persona fisica
    And Nella pagina Piattaforma Notifiche persona fisica vengo restituite tutte le notifiche con il codice IUN "ZVJN-QWEA-GLPY-202405-A-1"
    And Cliccare sulla notifica restituita
    Then Si visualizza correttamente la section Dettaglio Notifica persona fisica
    And Si controlla lo stato timeline in dettaglio notifica
      | xpath    | //span[contains(text(),"L'invio della notifica è terminato in quanto un recapito di almeno un destinatario è valido.")]|
    # il parametro isTrue é utilizzato per verificare se un elemento deve esistere o no
    And Si controlla testo in box pagamento
      | xpath    | //p[@data-testid='apply-costs-caption']|
      | isTrue    | true         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-pagoPA-notice-button']|
      | isTrue    | true         |
    And Si controlla testo in box pagamento
      | xpath    | //button[@data-testid='download-f24-button']|
      | isTrue    | true         |
    And Logout da portale persona fisica



