# Enigma
An enigma implementation in Scala

__Disclaimer :__ Plug board functionality has not yet been implemented.

___
To run the program, first compile with
```
> scalac Enigma.scala Parser.scala Rotor.scala Reflector.scala
```

Then run 
```
> scala Enigma -decrypt RotorPositions(ex: I,IV,III) notchPositions(ex: 0,25) StartPositions(ex: A,E,P) optional:[file]
```

Where `RotorPositions` is the placement of rotors [I-V] in the intended spots from left to right (i.e. the last rotor you give will be the right-most rotor on the machine), the `notchPostion` is essentially the inner ring placement on the real machines (at what point will the right rotor shift the left one), and `startPostion` is the start postion of the rotors, again from left to right. You can either provide a filename as the last argument or leave it blank in which case you will be prompted to enter text. Two flags can be used, either `-decrypt` or `-encrypt`, even though they are identical in practice.


The wiring of the rotors I through V is the same as in the original enigma machine (model used by the German army and airforce).
