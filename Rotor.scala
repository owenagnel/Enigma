class Rotor{
  private var mapping: List[Int] = List(0)
  private var position: Int = 0
  private var notch: Int = 0
  /** Returns the ouput of the rotor during 
   *  the first traversal of the rotor.*/
  def output1(entryPoint: Int) = mapping(entryPoint)
  /** Returns the output of the rotor on the second
   *  traversal of the rotor.*/
  def output2(entryPoint: Int) = mapping.indexOf(entryPoint)
  /** Rotates the rotor by shifting the mapping by 1*/
  def rotate: Boolean = {
    assert(mapping.length == 26)
    val push = position == notch
    val m = mapping(25)
    mapping = m :: mapping.init 
    position = (position + 1) % 26
    push
  }
}
object Rotor{
  /** Factory function for the Rotor class*/
  def apply(rotorNum: Int, notch: Int, startPos: Int): Rotor = {
    val out = new Rotor
    out.mapping = rotors(rotorNum)
    out.notch = notch
    while(out.position < startPos) {
      out.rotate
    }
    out 
  }
  /** List of 5 possible rotors*/
  val rotorI = List(4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9)
  val rotorII = List(0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4)
  val rotorIII = List(1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14)
  val rotorIV = List(4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1)
  val rotorV = List(21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10)
  val rotors = Array(rotorI, rotorII, rotorIII, rotorIV, rotorV)
}