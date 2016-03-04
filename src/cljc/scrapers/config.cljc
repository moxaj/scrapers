(def config
  :schemas {:client-input   {:record {:token :string
                                      :input [:vector :ubyte]}}
            :client-control {:record {:token  :string
                                      :action [:enum [:connect :disconnect]]}}
            :snapshot       {:record {}}})
