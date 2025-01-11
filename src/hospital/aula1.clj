(ns hospital.aula1
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

; Define a symbol to the model execution

(defn simula-um-dia-atendimento []
  ;Root binding
  (def hospital (h.model/novo-hospital))

  (def hospital (h.logic/inclui-na-fila hospital :espera "111"))
  (def hospital (h.logic/inclui-na-fila hospital :laboratorio1 "222"))
  (def hospital (h.logic/inclui-na-fila hospital :laboratorio3 "444"))
  (def hospital (h.logic/inclui-na-fila hospital :espera "555"))
  (def hospital (h.logic/inclui-na-fila hospital :espera "888"))
  (def hospital (h.logic/inclui-na-fila hospital :laboratorio1 "777"))

  (def hospital (h.logic/exclui-da-fila hospital :laboratorio1))
  (def hospital (h.logic/exclui-da-fila hospital :laboratorio3))

  (def hospital (h.logic/inclui-na-fila hospital :espera "999"))
  (def hospital (h.logic/inclui-na-fila hospital :espera "666"))
  (def hospital (h.logic/inclui-na-fila hospital :espera "222"))

  (pprint hospital))

(simula-um-dia-atendimento)