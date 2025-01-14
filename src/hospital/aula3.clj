(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]
            [hospital.logic :as h.logic]))

;; Criando um atom em um map
;; O atom eh um objeto mutavel, adequado para evitar shadowing

(defn testa-atomo []
  (let [hospital-lima (atom {:espera (h.model/fila-vazia)})]
    (println hospital-lima)
    (pprint hospital-lima)
    (println "1 ---- " @hospital-lima)

    ; Alteracoes com o deref do symbolo nao alterao o valor do mesmo.
    ; @ eh a mesma coisa que chamar deref
    (print "2 ---- ")
    (pprint (assoc (deref hospital-lima) :laboratorio1 (h.model/fila-vazia)))
    (print "2 ---- ")
    (pprint (assoc @hospital-lima :laboratorio1 (h.model/fila-vazia)))
    (pprint @hospital-lima)


    ; Para alterar o valor do meu map, tenho que chamar o swap!
    (swap! hospital-lima assoc :laboratorio1 (h.model/fila-vazia))
    (print "3 ----")
    (pprint @hospital-lima)

    (swap! hospital-lima update :espera conj "111")
    (print "4 ----")
    (pprint @hospital-lima)
    
    (swap! hospital-lima update :laboratorio1 conj "222")
    (print "5 ----")
    (pprint @hospital-lima)
    ))