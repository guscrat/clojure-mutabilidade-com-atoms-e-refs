(ns hospital.hospital
  (:use [clojure pprint]))
; PPRINT -> Prettier Print. VSCode do it without print only by invoke the symbol, or a fuction executing ...


; Working with persistent queue
(def espera (conj clojure.lang.PersistentQueue/EMPTY "11" "22"))

(conj espera "33")
(pop espera)