(ns scrapers.materialize
  (:require [org.nfrac.cljbox2d.core :refer [query-aabb aabb]]))

(defn materializable?
  "Checks whether a spec can be materialized in a chamber."
  [spec chamber b2d-world]
  (let [{:keys [width height]} spec
        {:keys [x y radius]}   chamber]
    (and (< (Math/sqrt (+ (* width width)
                          (* height height)))
            (* 2 radius))
         (empty? (query-aabb b2d-world
                             (aabb [(- x (/ width 2))
                                    (- y (/ height 2))]
                                   [(+ x (/ width 2))
                                    (+ y (/ height 2))]))))))

(defn materialize
  "Materializes a spec in a chamber."
  [spec user-data chamber b2d-world]
  ())
