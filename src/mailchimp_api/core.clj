(ns mailchimp-api.core
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]))


(defn generic-post
  "Generic post event for mailchimp v3 API"
  [url data apiKey]
  (try
    (client/post url
                 {:basic-auth ["user" apiKey]
                  :body data
                  :content-type :json
                  :accept :json})
                  (catch Exception e
                    (println e))))

(defn add-to-list
  "adds a user to a list"
  [listId data apiKey dc]
  (let [url (str "https://" dc ".api.mailchimp.com//3.0/lists/" listId "/members/")]
    (generic-post url (generate-string data) apiKey)))
