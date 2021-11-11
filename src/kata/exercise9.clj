(ns kata.exercise9
  (:use [clojure.test]
        [kata.data]))

; I'm not sure how the solution that involves _partial_ is supposed to work?
; Ideally, I'd just do (join "," (map :name xs))
(testing "Razor-sharp Focus"

  ; Implement a custom function joining a seq of customer names into csv,
  ;;  by combining (reduce ...) (partial ...) and (join ...)
  (let [joiner (fn [xs] (reduce
                          (fn [cur_str cust] (clojure.string/join "," [cur_str (:name cust)]))
                          (:name (first xs))
                          (rest xs)))
        csv (joiner (:customers mall))]

    (is (= csv "Joe,Steven,Patrick,Diana,Chris,Kathy,Alice,Andrew,Martin,Amy"))))

; This is ugly, but at least it works :)
; I'm sure there is an easier way than this, probably using group-by
; I've pondered it, decided that the solution is not _trivial_ to come up with and will proceed
(testing "I want you now"

  ; Implement a function which creates a map with keys as item name and
  ;;  values as set of customers who are wanting to buy that item.
  (let [audience-analyzer (fn [market] (into {}
                                             (map
                                               (fn [item] [item (set (map key
                                                                          (filter #(contains? (val %1) item)
                                                                                  (into {}
                                                                                        (map
                                                                                          (fn [c]
                                                                                            [(:name c) (set (map :name (:wants-to-buy c)))])
                                                                                          (:customers market))))))])
                                               (flatten (map #(map :name (:items %1)) (:shops market))))))
        target-audience (audience-analyzer mall)]

    (is (= (target-audience "plane") #{"Chris"}))
    (is (= (target-audience "onion") #{"Patrick", "Amy"}))
    (is (= (target-audience "ice cream") #{"Patrick", "Steven"}))
    (is (= (target-audience "earphone") #{"Steven"}))
    (is (= (target-audience "plate") #{"Joe", "Martin"}))
    (is (= (target-audience "fork") #{"Joe", "Martin"}))
    (is (= (target-audience "cable") #{"Diana", "Steven"}))
    (is (= (target-audience "desk") #{"Alice"}))))

(defn get-bitstring-tokens [bitlist]
  (clojure.string/split bitlist #","))

(defn get-bitstring-len [bitlist]
  (apply max (map read-string (clojure.string/split bitlist #",|-"))))

(defn get-token-bit [token bit]
  (let [toknum (sort (map read-string (clojure.string/split token #"-")))]
    (if (> (count toknum) 1)
      (<= (first toknum) bit (second toknum))
      (= (first toknum) bit))))

(defn get-tokens-bit [tokens bit]
  (boolean (some #(get-token-bit %1 bit) tokens)))

(defn bool-to-bit [bool]
  (if bool "1" "0"))

; At least this solution is kinda okay
(testing "bitList2BitString"

  ; Create a function building a string of "n'th bit is ON" from an encoded specification.
  ;;  See tests for examples
  (let [bitSpec1 "3"
        bitSpec2 "1,3,5"
        bitSpec3 "1-3"
        bitSpec4 "7,1-3,5"
        bitSpec "22-24,9,42-44,11,4,46,14-17,5,2,38-40,33,50,48"
        bit-decoder (fn [spec] (clojure.string/join
                                 (map bool-to-bit
                                      (map
                                        (partial get-tokens-bit (get-bitstring-tokens spec))
                                        (map
                                          (partial + 1)
                                          (range (get-bitstring-len spec)))))))]

    (is (= (bit-decoder bitSpec1) "001"))
    (is (= (bit-decoder bitSpec2) "10101"))
    (is (= (bit-decoder bitSpec3) "111"))
    (is (= (bit-decoder bitSpec4) "1110101"))
    (is (= (bit-decoder bitSpec)  "01011000101001111000011100000000100001110111010101"))))