# Enigma
An enigma implementation in Scala
__Disclaimer :__ Have not yet implemented plug board functionality.

___
To run the program, first compile with
```
> scalac Enigma.scala Parser.scala Rotor.scala Reflector.scala
```
Then run 
```
> scala Enigma -decrypt RotorPositions(ex: I,IV,III) notchPositions(ex: 0,25) StartPositions(ex: A,E,P) optional:[file]
```

Where `RotorPositions` is the placement of rotors [I-V] in spots 1..3, the `notchPostion` is essentially the inner ring placement on the real machines (at what point will the left rotor shift the right one), and `startPostion` is the start postion of the rotors. You can either provide a filename as the last argument or leave it blank in which case you will be prompted to enter text. Two flags can be used, either `-decrypt` or `-encrypt`, even though they are identical in practice.


The wiring of the rotors I through V is the same as in the original enigma machine (model used by the German army and airforce). The order in which you give the rotors, is the order in which they are placed from right to left.