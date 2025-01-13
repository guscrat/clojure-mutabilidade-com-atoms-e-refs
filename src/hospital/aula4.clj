(ns hospital.aula4
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

(defn chega-sem-malvado!
  [hospital pessoa]
  (swap! hospital h.logic/inclui-na-fila :espera pessoa))

(defn simula-um-dia-em-paralelo-com-mapv
  "Simulacao utilizando o mapv para forcar a execucao de um mapa lazy"
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]]

    (mapv #(.start (Thread. (fn [] (chega-sem-malvado! hospital %)))) pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))



(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]
        starta-thread-de-chegada #(.start (Thread. (fn [] (chega-sem-malvado! hospital %))))]

    (mapv starta-thread-de-chegada pessoas)

    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))

;; (simula-um-dia-em-paralelo)

(defn starta-thread-de-chegada
  ([hospital]
   (fn [pessoa] (starta-thread-de-chegada hospital pessoa)))
  ([hospital pessoa]
   (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa))))))

(defn simula-um-dia-em-paralelo-refatorada
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]]

    (mapv (starta-thread-de-chegada hospital) pessoas)

    (.start (Thread. (fn [] (Thread/sleep 1000)
                       (pprint hospital))))))




(defn starta-thread-de-chegada
  [hospital pessoa]
  (.start (Thread. (fn [] (chega-sem-malvado! hospital pessoa)))))

(defn simula-um-dia-em-paralelo-com-partial
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]
        starta (partial starta-thread-de-chegada hospital)]

    (mapv starta pessoas)

    (.start (Thread. (fn [] (Thread/sleep 1000)
                       (pprint hospital))))))




(defn simula-um-dia-em-paralelo-com-doseq
  "Do seq para executar com elementos especificos"
  []
  (let [hospital (atom (h.model/novo-hospital))
        pessoas ["111" "222" "333" "444" "555" "666"]]

    (println "doseq" (doseq [pessoa pessoas]
      (starta-thread-de-chegada hospital pessoa)))

    (.start (Thread. (fn [] (Thread/sleep 1000)
                       (pprint hospital))))))






(defn simula-um-dia-em-paralelo-com-dotimes
  "Dotimes para executar N vezes"
  []
  (let [hospital (atom (h.model/novo-hospital))]

    (println "dotimes" (dotimes [pessoa 6]
                       (starta-thread-de-chegada hospital pessoa)))

    (.start (Thread. (fn [] (Thread/sleep 1000)
                       (pprint hospital))))))

(simula-um-dia-em-paralelo-com-dotimes)