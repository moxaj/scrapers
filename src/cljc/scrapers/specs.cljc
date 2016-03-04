(ns scrapers.specs)



(def spec-keys [:width :height])
(def chamber-keys [:x :y :radius])


;; All coordinates are relative to [0 0]
(def example-spec
  {:width  100
   :height 100
   :bodies []
   :joints []})
