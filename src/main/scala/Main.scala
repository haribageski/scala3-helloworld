import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext
import scala.deriving.*
import scala.compiletime.{erasedValue, summonInline}

@main def hello = {
  case class UserName(name: String)
  case class Password(hash: String)
  def help(id: UserName | Password) = {
    id match {
      case UserName(name) => println(name)
      case Password(hash) => println(hash)
    }
  }
  help(UserName(""))
  help(Password(""))

  type T[X, Y] = Map[Y, X]
  val x: T[Int, String] = Map("1" -> 2)
  println(x)

  type Tuple = [X] =>> (X, X)
  val y: Tuple[String] = ("1", "2")
  println(y)

  type Executable[T] = ExecutionContext ?=> T
  given ec: ExecutionContext = global
  def f(x: Int): ExecutionContext ?=> Int = x
  println(f(2)(using ec))
  println(f(2))

  enum Color {
    case Red, Green, Blue
  }

  val xs: List[(Int, Int)] = ???
  xs map { case (x, y) => x + y }

  trait Entry { type Key }
  type DependentFuncType = (e: Entry) => e.Key

  type PolymorphicFuncTypes = [A] => List[A] => List[A]
  def polyF[A](l: List[A]): List[A] = ???
  val polyFVal: PolymorphicFuncTypes = [A] => (xs: List[A]) => polyF[A](xs)

  trait Random[A] {
    def generate(): A
  }

  case class Circle(x: Double, y: Double, radius: Double)
  object Circle {
    extension (c: Circle) {
      def circumference: Double = c.radius * math.Pi * 2
    }
  }
  val circle = Circle(0, 0, 1)
  circle.circumference

  enum SiteMember {
    case RegisteredUser(id: Long, email: String, isAdmin: Boolean)
    case AnonymousUser(session: String)
  }

  object P {
    opaque type Permissions = Int
    object Permissions {
      def apply(d: Int): Permissions = d: Permissions
    }
    val x = Permissions(1)
    def f(x: Permissions) = x
    val z = f(1)
  }


  class BitMap
  class InkJet
  class Printer {
    type PrinterType
    def print(bits: BitMap): Unit = ???
    def status: List[String] = ???
  }
  class Scanner {
    def scan(): BitMap = ???
  }
  class Copier {
    private val printUnit = new Printer {
      type PrinterType = InkJet
    }
    private val scanUnit = new Scanner

    export scanUnit.scan
  }
  val c: Copier = ???
  val s = c.scan()

  class SBuilder(s: String) {
    def this() = this("")
  }
  SBuilder("abc")
  SBuilder()

  val xOrNull: Null | Int = ???
}