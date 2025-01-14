(ns hospital.aula6
  (:use [clojure pprint])
  (:require
   [hospital.model :as h.model]))

(defn cabe-na-fila?
  [fila]
  (-> fila
      count
      (< 5)))

(defn chega-em
  "Funcao pura a ser chamada por tras das macros BANG (!)"
  [fila pessoa]
  (if (cabe-na-fila? fila)
    (conj fila pessoa)
    (throw (ex-info "Fila ja esta cheia" {:tentando-adicionar pessoa}))))

(defn chega-em-ref-set!
  "Troca de referencia via ref-set.

   Para fazer alteracoes de valores 
   em um refset, eh necessario fazer
   o de-ref do seu ref, no caso a 
   fila (@fila ou (deref fila)).

   ref-set --> pode ser chamado em uma transacao,
   serve para alterar valor de uma ref.
   
   Sua chamada eh um pouco diferente do swap!:
   (ref-set ref (funcao args))
   "
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (ref-set fila (chega-em @fila pessoa))))


(defn chega-em-alter!
  "Troca de referencia via alter
   
   Serve tbm para adicionar/alterar valor de uma ref
   Eh chamado semelhante ao swap!:
   (alter ref funcao args)"
  [hospital pessoa]
  (let [fila (get hospital :espera)]
    (alter fila chega-em pessoa)))

(defn simula-um-dia
  "Cria um hospital com filas de espera por laboratorio ou apenas lobby"
  []
  (let [meu-hospital {:espera (ref h.model/fila-vazia)
                      :laboratorio1 (ref h.model/fila-vazia)
                      :laboratorio2 (ref h.model/fila-vazia)
                      :laboratorio3 (ref h.model/fila-vazia)}]

    ; dosync --> Starta uma transaciton para utilizacao das refs
    (dosync
     (chega-em-alter! meu-hospital "guilherme")
     (chega-em-alter! meu-hospital "maria")
     (chega-em-alter! meu-hospital "lucia")
     (chega-em-alter! meu-hospital "daniela")
     (chega-em-alter! meu-hospital "ana")
    ;;  (chega-em-alter! meu-hospital "paulo")
     )

    (pprint meu-hospital)))

(simula-um-dia)

(defn async-chega-em! [hospital pessoa]
  (future
    (.start (Thread. (fn [] (Thread/sleep 8000))))
    (println "Tentando fazer o dosync" 15)
    (dosync
     (println "Tentando o codgo sincronizado" pessoa)
     (chega-em-alter! hospital pessoa))))


(defn simula-um-dia-async
  "Cria um hospital com filas de espera por laboratorio ou apenas lobby"
  []
  (let [meu-hospital {:espera (ref h.model/fila-vazia)
                      :laboratorio1 (ref h.model/fila-vazia)
                      :laboratorio2 (ref h.model/fila-vazia)
                      :laboratorio3 (ref h.model/fila-vazia)}]
    (dotimes [pessoa 10](async-chega-em! meu-hospital pessoa))
    ))
    
(simula-um-dia-async)

(println (future 15))
(println (future (Thread/sleep (rand 5000)) 15))