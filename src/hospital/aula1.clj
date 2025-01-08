(ns hospital.aula1
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

; Define a symbol to the model execution

(defn simula-um-dia-atendimento []
  ;Root binding
  (def hospital (h.model/novo-hospital))
  
  (inclui-na-fila hospital :espera "111")
  (inclui-na-fila hospital :laboratorio1 "222")
  (inclui-na-fila hospital :laboratorio3 "444")

  
  (pprint hospital))

(simula-um-dia-atendimento)