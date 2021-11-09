(ns kata.exercise8
  (:use [clojure.test]
        [kata.data]))

(testing "Nowhere to be Found"

  ; Create a set of item names that are in {@link customer->wants-to-buy} but not on sale in any shop.
  (let [not-for-sale (set (filter
                            #(not (contains? (set (map :name (flatten (map :items (:shops mall))))) %1))
                            (set (map :name (flatten (map :wants-to-buy (:customers mall)))))))]

    (is (= not-for-sale #{"bag", "pants", "coat"}))))


(defn all-items-cheapest [shops]
  (map first (partition-by :name (sort-by (juxt :name :price)
                                          (flatten (map :items shops))))))

(defn item-cost [names]
 (apply + (map :price (filter #(contains? names (:name %1))
                              (all-items-cheapest (:shops mall))))))



(testing "I see it, I like it, I want it, I got it "

  ; Create a customers' name list including who are having enough money to buy all items they want which is on sale.
  ; Items that are not for sale can be counted as 0 money cost.
  ; If there are multiple items with the same names, but different prices, customer will choose the cheapest one.
  (let [richies (map :name (filter #(>= (:budget %1)
                                        (item-cost (set (map :name (:wants-to-buy %1)))))
                                   (:customers mall)))]

    (is (= richies ["Joe", "Patrick", "Chris", "Kathy", "Alice", "Andrew", "Amy"]))))
