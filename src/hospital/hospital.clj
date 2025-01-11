(ns hospital.hospital
  (:use [clojure pprint]))
; PPRINT -> Prettier Print. VSCode do it without print only by invoke the symbol, or a fuction executing ...


; Working with persistent queue
(def espera (conj clojure.lang.PersistentQueue/EMPTY "11" "22"))

(conj espera "33")
(pop espera)





(defn novo-hospital []
  {:espera       clojure.lang.PersistentQueue/EMPTY,
   :laboratorio1 clojure.lang.PersistentQueue/EMPTY,
   :laboratorio2 clojure.lang.PersistentQueue/EMPTY,
   :laboratorio3 clojure.lang.PersistentQueue/EMPTY})

(def meu-hospital (novo-hospital))

meu-hospital
(def meu-hospital-2 (update meu-hospital :espera conj "333"))

meu-hospital-2
(update meu-hospital-2 :espera pop)