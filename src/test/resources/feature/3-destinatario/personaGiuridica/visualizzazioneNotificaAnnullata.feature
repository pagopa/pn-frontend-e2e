Feature: controlli notifica annullata persona giuridica

  Scenario: [TA-FE WORKFLOW DELLA NOTIFICA CON SEQUENCE-@FAIL-DiscoveryIrreperibile_AR] - Il mittente invia una notifica a destinatario con sequence
    Given PA - Si effettua la login tramite token exchange, e viene visualizzata la dashboard
    When Nella pagina Piattaforma Notifiche cliccare sul bottone Invia una nuova notifica
    And Si visualizza correttamente la pagina Piattaforma Notifiche section Informazioni preliminari
    And Creazione notifica completa
    | oggettoDellaNotifica | Pagamento rata IMU    |
    | descrizione          | PAGAMENTO RATA IMU    |
    | modello              | AR    |
    | gruppoTest           | test-TA-FE-TEST       |
    | gruppoDev            | GruppoTest            |
    | codiceTassonometrico | 123456A               |
    | nomeFileYaml         | datiNotifica          |
    | nomePF               | Gaio Giulio           |
    | cognomePF            | Cesare                |
    | codiceFiscalePF      | CSRGGL44L13H501E      |
    | indirizzoPF          | via @FAIL-DiscoveryIrreperibile_AR |
    | numeroCivicoPF       | 20   |
    | comunePF             | MILANO  |
    | provinciaPF          | MI   |
    | codicepostalePF      | 20147   |
    | statoPF              | ITALIA   |
    | nomeDocumentoNotifica | RATA SCADUTA IMU |
    And Si verifica che la notifica è stata creata correttamente
    And Si seleziona la notifica
    And Si attende completamento notifica
    #inserire annullamento notifica
  #login pg
  #verifiche