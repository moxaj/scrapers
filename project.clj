(defproject scrapers "0.1.1"
  :description "FIXME: write description"
  :url "https://github.com/moxaj/scrapers"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [moxaj/seria "0.1.225"]]

  :target-path "target/%s"
  :plugins [[lein-cljsbuild "1.1.1"]
            [moxaj/lein-seria "0.1.2"]]

  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [org.clojure/java.classpath "0.2.3"]]}}

  :cljsbuild {:builds [{:id           "id"
                        :source-paths ["src/client" "src/shared"]
                        :compiler     {:asset-path     "js/out"
                                       :output-to      "resources/public/js/app.js"
                                       :output-dir     "resources/public/js/out"
                                       :optimizations  :advanced
                                       :parallel-build true
                                       :pretty-print   false
                                       :main           "client.main"}}]})
