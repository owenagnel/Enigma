import scala.util.matching.Regex

object Parser{
  /** Does a RegEx check to make sure that the rotor input is of the correct form */
  def rotorCheck(str: String): Boolean = {
    val rotorPattern = "\\A(I,|II,|III,|IV,|V,){2}(I\\z|II\\z|III\\z|IV\\z|V\\z){1}".r
    rotorPattern.matches(str)
  }

  /** Converts the roman numerals of the rotors to ints*/
  def convertRoman(roman: String): Int = {
    val converter = Map("I" -> 0, "II" -> 1, "III" -> 2, "IV" -> 3, "V" -> 4)
    converter(roman)
  }

  /** Returns an Array containing the rotor positions in order, or an exception */
  def rotorParse(str: String): Array[Int] = {
    val ans = new Array[Int](3)
    if(rotorCheck(str)){
      val rotorArray = str.split(",")
      for(i <- 0 until 3){
        ans(i) = convertRoman(rotorArray(i))
      }
      if(ans.length != ans.distinct.length){
        throw new SemanticException("Two of the same rotors are input!")
      }
    } else {
      throw new SyntaxException("The rotor syntax was incorrect!")
    }
    ans
  }

  /** Checks that the notch positions have correct syntax*/
  def notchCheck(str: String): Boolean = {
    val notchPattern = "\\A(\\d){1,2},(\\d){1,2}\\z".r
    notchPattern.matches(str)
  }

  /** Returns an Array containing the notch position or throws an exception*/
  def notchParse(str: String): Array[Int] = {
    val ans = new Array[Int](2)
    if(notchCheck(str)){
      val notchArray = str.split(",")
      for(i <- 0 until 2){
        ans(i) = notchArray(i).toInt
      }
      for(x <- ans){
        if(x < 0 || x > 25) {
          throw new SemanticException("Invalid notch positions! There are only 26 letters in the alphabet.")
        }
      }
    } else {
      throw new SyntaxException("The notch position syntax is incorrect!")
    }
    ans
  }

  /** Check the syntax of start position is correct*/
  def startPosCheck(str: String): Boolean = {
    val startPosPattern = "\\A[A-Z],[A-Z],[A-Z]\\z".r
    startPosPattern.matches(str)
  }

  /** Parses the start position and returns an array with the start positions*/
  def startPosParse(str: String): Array[Int] = {
    val ans = new Array[Int](3)
    if(startPosCheck(str)){
    val startPosArray = str.toArray.filter(_ != ',')
    for(i <- 0 until 3){
      ans(i) = startPosArray(i).toInt - 65
    }
    } else {
      throw new SyntaxException("The start position syntax is incorrect!")
    }
    ans
  }

  /** Converts the input string into a sequence of capital letters removing space and all other symbols. */
  def convertToUpper(str: String): Array[Char] = {
    val inputArray = str.toUpperCase.toArray
    val ans = inputArray.filter((x: Char) => x.toInt >= 65 && x.toInt <= 90)
    ans
  }
}

// Custom exceptions
case class SyntaxException(smth:String)  extends Exception

case class SemanticException(smth:String)  extends Exception