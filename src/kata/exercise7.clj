(ns kata.exercise7
  (:use [clojure.test]
        [kata.data]))

(testing "Average Age"

  ; Calculate the average of customer ages by using your own (avg ...)
  ;; Don't forget about (apply ...)
  (let [avg-age ((fn [x] (/ (apply + x) (count x))) (map :age (:customers mall)))]

    (is (= avg-age (rationalize 28.7)))))

(testing "How Much for Everything?!"

  ; Calculate the sum of the prices for all the items in all shops by using (flatten ...)
  ;; Don't forget about (apply ...)
  (let [total (apply + (map :price (flatten (map :items (:shops mall)))))]

    (is (= total 60930))))
