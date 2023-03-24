@main def hello: Unit = 
  val gameName = "Minesweeper"
  println(s"Welcome to $gameName!")
  print("Enter your Name: ")
  val userInput = scala.io.StdIn.readLine()
  println(s"Hey $userInput lets start!")


  //Generate Minefield
  generateField_prototype(30, 5)
  //print to user
  // wait for user Input





def generateField_prototype(width: Int, length: Int) =
  println("-".repeat(width))

  
