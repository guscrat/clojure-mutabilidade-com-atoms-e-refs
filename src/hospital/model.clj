(ns hospital.model)

(defn novo-hospital [] 
  {:espera       clojure.lang.PersistentQueue/EMPTY,
   :laboratorio1 clojure.lang.PersistentQueue/EMPTY,
   :laboratorio2 clojure.lang.PersistentQueue/EMPTY,
   :laboratorio3 clojure.lang.PersistentQueue/EMPTY})