(ns hospital.logic)


(defn cabe-na-fila?
  [object queue]
  (-> object
      (get ,,, queue)
      count ,,,
      (< ,,, 5)))

(defn inclui-na-fila
  [object queue pessoa]
  (if (cabe-na-fila? object queue)
    (update object queue conj pessoa)
    (throw (ex-info "Fila ja esta cheia" {:tentando-adicionar pessoa}))))

(defn exclui-da-fila
  [object queue]
  (update object queue pop))