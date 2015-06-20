(ns mike.resource.sentence
  (:require [lang.sentence.factory :refer [build-sentence-repo]]
            [lang.sentence.api :as api]
            [clojure.core.async :refer [<!!]]
            [clojure.data.json :as json]))

(defn parse-int
  [str]
  (Integer/parseInt str))

(defn succeed
  [body]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/write-str body)})

(defn fail
  [message]
  {:status 400
   :headers {"Content-Type" "application/json"}
   :body (json/write-str message)})

(defn sid
  [repo yak id]
  (<!! (api/get-sentence repo (keyword yak) (Integer/parseInt id))))

(defn srandom
  [repo yak]
  (<!! (api/get-random-sentence repo (keyword yak))))


(defn srandoms
  [repo yak n]
  (<!! (api/get-random-sentences repo (keyword yak) (parse-int n))))

(defn srange
  [repo yak start end]
  (<!! (api/get-sentence-range repo (keyword yak) (parse-int start) (parse-int end))))

(defn language-resource
  [repo id]
  (if id
    (let [language (api/get-language id)
          sentence-count (api/count-sentences repo yak)]
      (succeed (assoc info :sentence-count sentence-count)))
    (fail "NONONO")))

(defn sentence-resource
  [repo {:keys [yak id]}]
  (if yak
    (if id
      (succeed (sid repo yak id))
      (succeed (srandom repo yak)))
    (fail "MISSING LANGUAGE PAIR")))

(defn sentences
  [repo {:keys [yak n start end]}]
  (if yak
    (if n
      (succeed (<!! (api/get-random-sentences
                     repo
                     (keyword yak)
                     (parse-int n))))
      (if (and start end)
        (succeed (<!! (api/get-sentence-range
                       repo
                       (keyword yak)
                       (parse-int start)(parse-int end))))
        (fail "BAD COMBINATION")))
    (fail "MISSING LANGUAGE PAIR")))

