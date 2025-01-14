(ns hospital.aula6
  (:use [clojure pprint])
  (:require
   [hospital.model :as h.model]))


(defn chega-em
  "Funcao pura a ser chamada por tras das macros BANG (!)"
  [fila pessoa]
  (conj fila pessoa))

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
     (chega-em-alter! meu-hospital "guilherme"))

    (pprint meu-hospital)))

(simula-um-dia)