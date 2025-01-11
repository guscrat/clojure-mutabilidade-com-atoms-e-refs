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


(defn inclui-na-fila-malvado
  [pessoa]
  (def hospital-2 (h.logic/inclui-na-fila hospital :espera pessoa)))

(defn simula-um-dia-atendimento-2
  ;; Simulando um multi-threading com esse symbol sendo bindado.
  ;; No meu ambiente no VSCode nao funciona nem com uma thread.
  ;; Simplesmente inviavel.
  []
  (def hospital-2 (h.model/novo-hospital))
  
  (.start (Thread. (fn [] (inclui-na-fila-malvado "111"))))
  (.start (Thread. (fn [] (inclui-na-fila-malvado "222"))))
  (.start (Thread. (fn [] (inclui-na-fila-malvado "333"))))
  (.start (Thread. (fn [] (inclui-na-fila-malvado "444"))))
  (.start (Thread. (fn [] (inclui-na-fila-malvado "555"))))
  (.start (Thread. (fn [] (inclui-na-fila-malvado "666"))))
  
  (pprint hospital-2)

  )

(simula-um-dia-atendimento-2)