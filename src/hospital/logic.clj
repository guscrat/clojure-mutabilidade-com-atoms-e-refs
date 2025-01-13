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

(defn proxima
  "Retorna o proxima paciente"
  [hospital departamento]
  (-> hospital
      departamento
      peek))

(defn transfere
  "Transfere o proxima da fial de para fila para"
  [hospital de para]
  (let [pessoa (proxima hospital de)]
    (-> hospital
        (exclui-da-fila de)
        (inclui-na-fila para pessoa))))