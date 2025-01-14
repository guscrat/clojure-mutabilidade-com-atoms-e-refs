(ns hospital.test
  (:use [clojure pprint]) 
  (:require
    [hospital.logic :as h.logic]
    [hospital.model :as h.model]))



(defn chega-em
  [fila paciente]
  (conj fila paciente))

(defn chega-em!
  [hospital paciente]
  (let [fila (get hospital :espera)]
    (alter fila chega-em paciente)
    ))

(defn incia-meu-hospital
  "Cria meu hospital com uma fila 
   de espera e tres laboratorios"
  []
  (let
   [meu-hospital {:espera (ref h.model/fila-vazia)
                  :laboratorio1 (ref h.model/fila-vazia)
                  :laboratorio2 (ref h.model/fila-vazia)
                  :laboratorio3 (ref h.model/fila-vazia)}]
    
    (dosync
     (chega-em! meu-hospital "gustavo"))
    (pprint meu-hospital)
    ))

(incia-meu-hospital)