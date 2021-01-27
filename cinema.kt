package cinema
import java.util.Locale

fun menu(cinemaSeats: Array<Array<Int>>) {
    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    val y = readLine()!!.toInt()

    when (y) {
        1 -> printCinema(cinemaSeats)
        2 -> buyTicket(cinemaSeats)
        3 -> statistics(cinemaSeats)
    }
}

fun printTicketPrice(rowNumber: Int, x: Int, y: Int) {
    print("Ticket price: ")
    if (x * y <= 60) {
        print("\$10")
    } else {
        if (rowNumber >= (x - (x / 2))) {
            print("\$8")
        } else {
            print("\$10")
        }
    }
    println()
}

fun buyTicket(cinemaSeats: Array<Array<Int>>) {
    println("\nEnter a row number:")
    val rowNumber = readLine()!!.toInt()
    println("Enter a seat number in that row:")
    val seatNumber = readLine()!!.toInt()
    println()
    if (rowNumber > cinemaSeats.lastIndex + 1 || seatNumber > cinemaSeats[0].lastIndex + 1) {
        println("Wrong input!")
        buyTicket(cinemaSeats)
    } else if (cinemaSeats[rowNumber - 1][seatNumber - 1] == 1) {
        println("That ticket has already been purchased !")
        buyTicket(cinemaSeats)
    } else {
        cinemaSeats[rowNumber - 1][seatNumber - 1] = 1
        printTicketPrice(rowNumber, cinemaSeats.lastIndex + 1, cinemaSeats[0].lastIndex + 1)
    }

    menu(cinemaSeats)
}

fun printCinema(cinemaSeats: Array<Array<Int>>) {
    println()
    print("Cinema: \n")
    print(" ")
    for (i in 0..cinemaSeats[0].lastIndex) {
        print(" ")
        print(i + 1)
    }
    println()
    var rowCount = 1
    for (i in cinemaSeats) {
        print(rowCount)
        rowCount++
        for (y in i){
            if (y == 0) {
                print(" S")
            } else {
                print(" B")
            }
        }
        println()
    }
    menu(cinemaSeats)
}

fun statistics(cinemaSeats: Array<Array<Int>>) {
    var bookedFront: Int = 0
    var bookedBack: Int = 0
    for (x in 0..cinemaSeats.lastIndex) {
        for (y in 0..cinemaSeats[x].lastIndex) {
            if (cinemaSeats[x][y] == 1) {
                if ((cinemaSeats.lastIndex + 1) * (cinemaSeats[0].lastIndex + 1) <= 60) {
                    bookedFront++
                } else {
                    if (x + 1 >= ((cinemaSeats.lastIndex + 1) - ((cinemaSeats.lastIndex + 1) / 2))) {
                        bookedBack++
                    } else {
                        bookedFront++
                    }
                }
            }
        }
    }
    val percentage: Double = (bookedBack + bookedFront).toDouble() / ((cinemaSeats.lastIndex + 1) * (cinemaSeats[0].lastIndex + 1)).toDouble()
    val roundedPercentage = String.format(Locale.US, "%.2f", percentage * 100)
    print("\nNumber of purchased tickets: ")
    print(bookedBack + bookedFront)
    print("\nPercentage: ")
    print(roundedPercentage)
    print("%")
    print("\nCurrent income: \$")
    print((bookedBack * 8) + (bookedFront * 10))

    if ((cinemaSeats.lastIndex + 1) * (cinemaSeats[0].lastIndex + 1) <= 60) {
        print("\nTotal income: \$")
        print((cinemaSeats.lastIndex + 1) * (cinemaSeats[0].lastIndex + 1) * 10)
    } else {
        val cheaperTickets = ((cinemaSeats.lastIndex + 1) - ((cinemaSeats.lastIndex + 1) / 2)) * (cinemaSeats[0].lastIndex + 1) * 8
        val moreExpensiveTickets = ((cinemaSeats.lastIndex + 1) / 2) * (cinemaSeats[0].lastIndex + 1) * 10
        print("\nTotal income: \$")
        print(cheaperTickets + moreExpensiveTickets)
    }
    println()
    menu(cinemaSeats)
}

fun main() {
    println("Enter the number of rows:")
    val x = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val y = readLine()!!.toInt()

    var cinemaSeats = arrayOf<Array<Int>>()

    for (z in 0..x - 1) {
        var array = arrayOf<Int>()
        for (j in 0..y - 1) {
            array += 0
        }
        cinemaSeats += array
    }

    menu(cinemaSeats)
}
