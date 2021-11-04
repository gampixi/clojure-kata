(ns kata.exercise3
  (:use [clojure.test]
        [kata.data]))

(testing "How many items wanted"

  ; Count how many items are wanted
    ; Use (count ...)
  (let [wanted-items (count (mapcat :wants-to-buy (:customers mall)))]

    (is (= wanted-items 32))))


(testing "Richest customer"

  ; Find the richest customer's budget by using (apply ...) (max ...)
  (let [biggest-budget (apply max (map :budget (:customers mall)))]

    (is (= biggest-budget 12000))))

(testing "Youngest customer"

  ; Find the youngest customer by using (apply ...) (min-key ...)
  (let [youngest-customer (apply min-key :age (:customers mall))]

    (is (= (:name youngest-customer) "Martin"))))