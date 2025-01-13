(ns hospital.aula5 (:use [clojure pprint])
    (:require [hospital.logic :as h.logic]
              [hospital.model :as h.model]))

(defn chega-em!
  [hospital pessoa]
  (swap! hospital h.logic/inclui-na-fila :espera pessoa))

(defn transfere! [hospital de para]
  (swap! hospital h.logic/transfere de para))

(defn simula-um-dia
  []
  (let [hospital (atom (h.model/novo-hospital))]
    
    (chega-em! hospital "Joao")
    (chega-em! hospital "Maria")
    (chega-em! hospital "Daniela")
    (chega-em! hospital "Guilherme")
    
    (transfere! hospital :espera :laboratorio1)
    
    (pprint hospital)
    ))

(simula-um-dia)