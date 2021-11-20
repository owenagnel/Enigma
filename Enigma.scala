import scala.io.AnsiColor._
object Enigma{
  /** Read file into array */
  def readFile(fname: String) : Array[Char] = {
    scala.io.Source.fromFile(fname).toArray
  }
  
  /** Read from stdin in a similar manner */
  def readStdin() = scala.io.StdIn.readLine().toArray

  /** Transforms a character [A-Z] to a number [0-25] */
  def toNum(char: Char) = char.toInt - 65
  
  /** Encrypts/Decrypts the text according to the given settings.*/
  def crypt(rotors: Array[Int], positions: Array[Int], notches: Array[Int], text: Array[Char]): Array[Char] = {
    // set the rotor position and notch
    val rotor1 = Rotor(rotors(2), notches(0), positions(0))
    val rotor2 = Rotor(rotors(1), notches(1), positions(1))
    val rotor3 = Rotor(rotors(0), 0, positions(2))
    //initialise output array
    val out = new Array[Char](text.length)
    var i = 0
    while(i < text.length){
      if(rotor1.rotate){
        // rotates 2nd or third rotor depending on the notch position
        if(rotor2.rotate) rotor3.rotate
      }
      // split result for readibility
      val intermediate = rotor3.output1(rotor2.output1(rotor1.output1(toNum(text(i)))))
      out(i) = (rotor1.output2(rotor2.output2(rotor3.output2(Reflector.output(intermediate)))) + 65).toChar
      i += 1
    }
    out
  }


  def main(args: Array[String]) = {
    // Error string to print
    val errString = 
      s"${Console.GREEN}${BOLD}Usage: scala Enigma -encrypt RotorPositions(ex: I,IV,III) notchPositions(ex: 0,25) StartPositions(ex: A,E,P) optional:[file]\n"+
      s"${Console.GREEN}     | scala Enigma -decrypt RotorPositions(ex: I,IV,III) notchPositions(ex: 0,25) StartPositions(ex: A,E,P) optional:[file]\n"


    /** Get the plaintext, either from the file whose name appears in position
    4, or from standard input */
    def getInput(flag : String)= {
      val input = if(args.length>= 5) readFile(args(4)) else {
        print("Input your " + flag + " : ")
        readStdin()
      }
      // Converts input to array of capital chars (deletes all other symbols)
      val ans = Parser.convertToUpper(input.mkString)
      ans
    }

    /** Check there are at least n arguments */
    def checkNumArgs(n: Int) = if(args.length<n){println(errString); sys.exit()}

    /** Parses the arguments, and calls the correct functions */
    checkNumArgs(4)

    val command = args(0)

    if(command=="-encrypt" || command == "-decrypt"){
      try {
        val input: Array[Char] = if(command=="-encrypt") getInput("plaintext") else getInput("ciphertext")
        val rotorPos = Parser.rotorParse(args(1))
        val notchPos = Parser.notchParse(args(2))
        val startPos = Parser.startPosParse(args(3))
        val out = crypt(rotorPos, startPos, notchPos, input)
        println(out.mkString)
      } catch {
        case x: SyntaxException => println(errString); println(s"${Console.RED}${BOLD}${UNDERLINED}Possible issue${RESET}${Console.RED} : " + x.smth); sys.exit()
        case x: SemanticException => println(errString); println(s"${Console.RED}${BOLD}${UNDERLINED}Possible issue${RESET}${Console.RED} : " + x.smth); sys.exit()
      }
      
    }
    // Incorrect flag
    else println(errString)
  }
}
