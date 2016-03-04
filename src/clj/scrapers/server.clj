(ns scrapers.server
  (:require [scrapers.util :as util]
            [seria.core :as seria]
            [buddy.sign.jws :as jws]
            [com.stuartsierra.component :as component]
            [overtone.at-at :as at-at]))

;; Client management
(def clients-atom (atom {}))

(defn connected-client? [account-id clients]
  (get-in clients [:channels account-id]))

(defn connect-client [clients account-id channel]
  (if (connected-client? account-id clients)
    clients
    (update clients :channels assoc channel account-id
                                    account-id channel)))

(defn disconnect-client [clients account-id channel]
  (update clients :channels dissoc account-id channel))

(defn update-inputs [clients account-id inputs]
  (update clients :inputs assoc account-id inputs))

(defn clear-inputs [clients]
  (update clients :inputs empty))

;; Tokens
(def token-secret "supersecret")

(defn generate-token [account-id]
  (jws/sign {:account-id account-id} token-secret))

(defn extract-account-id [token]
  (try
    (:account-id (jws/unsign token token-secret))
    (catch Exception e
      nil)))

;; Received messages
(defmulti receive-message* (fn [schema _ _ _] schema))

(defmethod receive-message* :client-input [_ data account-id channel]
  (swap! clients-atom
         (fn [clients]
           (if-not (connected-client? account-id clients)
             clients
             (update-inputs clients account-id (:input data))))))

(defmethod receive-message* :client-control [_ data account-id channel]
  (swap! clients-atom
         (fn [clients]
           (let [client-fn (case (:action data)
                             :connect    connect-client
                             :disconnect disconnect-client)]
             (client-fn clients account-id channel)))))

(defn receive-message [message channel]
  (let [unpacked-message (seria/unpack message :config "TODO")]
    (when (not= :seria.core/invalid unpacked-message)
      (let [[schema data] unpacked-message]
        (when-let [account-id (extract-account-id (:token data))]
          (receive-message* schema data account-id channel))))))

;;; server
;; update
"add new structures: materialization queues, projectiles
 remove dead structures
 apply custom game logic
 apply player input: forces, impulses, torques"

;; send
"snapshot whole world
 filter snapshot for each player
 send snapshots"

;;;client
"draw
 send inputs"

(defrecord Timer [pool function delay instance]
  component/Lifecycle
  (start [this]
    (assoc this :instance (at-at/every delay function pool)))
  (stop [this]
    (update this :instance at-at/stop)))

(defn make-timer [function delay]
  (map->Timer {:pool     (at-at/mk-pool)
               :function function
               :delay    delay}))
