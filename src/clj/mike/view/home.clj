(ns mike.view.home
  (:require [compojure.core :refer [defroutes GET]]
            [mike.layout.core :as layout]))

(def what-is-this "It's a website with no purpose or content. The internet is filled with them. There is a picture of Thierry Henry on this page somewhere. The only reason it's there is because otherwise this paragraph would have to be much longer to fill up the space.")

(def nonsense-paragraph "Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, dolore aliqua non est magna in labore pig pork biltong. Eiusmod swine spare ribs reprehenderit culpa. Boudin aliqua adipisicing rump corned beef.")

(def animal-facts ["For every human in the world there are one million ants."
                   "Almost half the pigs in the world are kept by farmers in China."
                   "To escape the grip of a crocodile’s jaw, push your thumb into its eyeballs - it will let you go instantly."])

(defn home []
  (layout/common
   "Mike's Home"
   [:section#home-picture]
   (layout/snake-section {:id :snake1
                          :heading "This is a heading!"
                          :paragraph "This is right under the big picture."})
   [:hr]
   [:div.row
    [:div.large-6.columns
     [:h4 "What is this?"]
     [:p what-is-this]
     [:h4 "Here's some facts about animals:"]
     [:ul (map (fn [s] [:li s]) animal-facts)]]
    [:div.large-6.columns
     [:img {:src "img/thierry-henry.jpg"}]]]
   [:hr]
   [:div.row
    [:div.large-6.columns
     [:h4 "This is a paragraph of nonsense"]
     [:p nonsense-paragraph]]
    [:div.large-6.columns
     [:h4 "This is the same paragraph of nonsense"]
     [:p nonsense-paragraph]]]
   [:hr]))