(ns mike.layout.core
  (:require [mike.layout.top-bar :refer [top-bar]]
            [hiccup.page :refer [html5 include-css include-js]]
            [clojure.string :refer [trim join]]))

(def dinosaur-links
  [{:label "Stegosaurus" :href "/stegosaurus"}
   {:label "Triceratops" :href "/triceratops"}])

(def tool-links
  [{:label "Shovel" :href "/shovel"}
   {:label "Bucket" :href "/bucket"}])

(def top-bar-items
  [{:type :link-dropdown :label "Dinosaurs" :href "/dinosaurs" :items dinosaur-links}
   {:type :link-dropdown :label "Tools" :href "/tools" :items tool-links }])

(def ferret-footer
  [:section.ferret
   [:div.row
    [:div.large-6.columns
     [:p "This is the bottom part of the footer."]]
    [:div.large-6.columns
     [:ul.inline-list.right
      [:li {:href "#"} "Link 1"]
      [:li {:href "#"} "Link 2"]
      [:li {:href "#"} "Link 3"]
      [:li {:href "#"} "Link 4"]]]]])

(defn snake-section
  [{:keys [id heading paragraph] :or {label nil-text href nil-text}}]
  [:section.snake {:id id}
   [:div.row
    [:div.small-8.columns
     [:h3 heading
      [:p paragraph]]]]])

(defn common
  [title & body]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:name "viewport" :content "initial-scale=1.0,width=device-width"}]
    [:title title]
    (include-css "css/normalize.css" "css/foundation.min.css" "css/screen.css")
    (include-js "js/vendor/modernizr.js")]
   [:body
    (top-bar title top-bar-items)
    body
    ferret-footer
    (include-js "js/vendor/jquery.js" "js/foundation.min.js")
    [:script "$(document).foundation();"]]))