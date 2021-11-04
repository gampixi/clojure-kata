(ns kata.exercise1
  (:use [clojure.test]
        [kata.data]))

(testing "Find rich customers"

  ; Create a sequence of customers having more budget than 10000
    ; Use (filter ...) and keywords as a (get) function
    ; Note how we use (set ...) in the test to not be dependant on the order of customers in our data
  (let [customers (:customers mall)
        rich-customers (filter #(> (:budget %) 10000) customers)]

    (is (= (count rich-customers) 2))
    (is (= (set (map :name rich-customers)) #{"Diana" "Andrew"}))))


(testing "How old are the customers"

  ; Create a sequence of customer ages
    ; Use (map ...) and keywords as a (get) function
  (let [customer-ages (map :age (:customers mall))]

    (is (= (count customer-ages) 10))
    (is (= customer-ages [22, 27, 28, 38, 26, 22, 32, 35, 21, 36]))))


