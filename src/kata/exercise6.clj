(ns kata.exercise6
  (:use [clojure.test]
        [kata.data]))

(testing "Numbers Seq"

  ; Create a seq only with multiples of 3, starting from 0, size of 10, by using for example (range ...)
  ; Yes, I realize that (range 0 30 3) exists but this is more fun
  (let [numbers-list (map #(* 3 %1) (take 10 (range)))]

    (is (= numbers-list
           [0, 3, 6, 9, 12, 15, 18, 21, 24, 27]))))
