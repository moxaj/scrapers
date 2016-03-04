(ns scrapers.util)

(defn id-generator []
  (let [counter (volatile! 0)]
    (fn [] (vswap! counter inc))))
