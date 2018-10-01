//
// F# program to analyze Divvy daily ride data.
//
// Maciej Girek
// U. of Illinois, Chicago
// CS 341, Spring 2018
// Project #04
//

#light

let ParseLine (line:string) = 
  let tokens = line.Split(',')
  let bikeID = System.Convert.ToInt32(tokens.[0])
  let tripDuaration = System.Convert.ToInt32(tokens.[1])
  let gender = System.Convert.ToInt32(tokens.[2])
  let birthYear = System.Convert.ToInt32(tokens.[3])
  (
  (bikeID,tripDuaration,gender,birthYear)
  )

let rec ParseInput lines = 
  let rides = Seq.map ParseLine lines
  Seq.toList rides

let averageTime list =
  let time = List.map (fun (_,y,_,_) -> ((y))) list
  time

let avgAgePerson list =
  let personAge = list |> List.map( fun (_, _, _, birthYear) -> float((2018 - birthYear)))
  let averageAge = float( List.average personAge)
  averageAge

let averageMaleAge list =
  let males = list |> List.filter(fun (bikeID, tripDuration, gender, birthYear) -> (gender = 1))
  let malesAge = avgAgePerson males
  malesAge

let averageFemaleAge list = 
  let females = list |> List.filter(fun (bikeID, tripDuration, gender, birthYear) -> (gender = 2))
  let femaleAge = avgAgePerson females
  femaleAge

let maleRiders list =
    let numberOfMaleRiders = list |> List.filter (fun (bikeID, tripDuration, gender, birthYear) -> ( gender = 1))
    let total =  float (List.length numberOfMaleRiders)
    total

let femaleRiders list =
    let numberOfFemaleRiders = list |> List.filter (fun (bikeID, tripDuration, gender, birthYear) -> ( gender = 2))
    let total =  float (List.length numberOfFemaleRiders)
    total

[<EntryPoint>]
let main argv =
  //
  // input file name, then input divvy ride data and build
  // a list of lists --- [ [1308;321;2;1991]; ... ]
  //
  let filename = System.Console.ReadLine()

  let contents = System.IO.File.ReadLines(filename)

  let ridedata = ParseInput  contents
 // printfn "%A" ridedata
  let N = List.length ridedata
  
  //printfn "# of rides: %A" N
  

  let findMen =  maleRiders ridedata
  //printfn "# of males: %A" findMen

  let findWomen = femaleRiders ridedata
  //printfn "# of females: %A" findWomen
  printfn "# of riders: %A" ((int findWomen) + (int findMen))
   // % of male riders
  let percentMale = (fun x y -> ((x / y) * 100.00)) findMen (double N) 
  printfn "%% of male riders: %A" (percentMale)
   // % of female riders
  let percentFemale = (fun x y -> ((x / y) * 100.00)) findWomen (double N) 
  printfn "%% of female riders: %A" (percentFemale)
   //avg duration
  let avgDuration =  averageTime ridedata
  let sum = List.sum avgDuration
  let averageDuration = (fun x y -> (x / y)) (float sum)(float N)
  printfn "Avg duration: %A mins" (averageDuration / 60.0)
   
   //avg age of male riders
  let ageMale = averageMaleAge ridedata 
  printfn "Avg age of male riders: %A" ageMale
  
   //avg age of female riders
  let ageFemale = averageFemaleAge ridedata
  printfn "Avg age of female riders: %A" ageFemale




 //
  // TODO:
  //
   
  0 
