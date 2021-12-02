val file = sc.textFile("/home/alumno/practicas/sistemas-distribuidos/practica4/customers.csv")
val data = file.map(line => line.split(","))
// val customers  = data.map(trans => (trans(2).toInt, trans.toInt))
// // Primera 
// customers.keys.count()
// customers.keys.distinct().count()

val customers  = data.map(trans => (trans(2).toInt, trans(4).toInt))

val custCount = customers.reduceByKey((a,b) => (a + b ))

// Segunda
val custSorted = custCount.sortBy(_._2, false)
custSorted.first()

val id = custSorted.first()._1
val products = customers.lookup(id).distinct.sorted

// Tercera
val cust100Transations = customers.countByKey().get(100)
val cust100 = data.filter(tr => tr(2).toInt == 100 && tr(4).toInt > 5)
cust100.count

// Cuarta
val productTrans = data.map(tr=>(tr(3).toInt, tr))
val amounts = productTrans.mapValues(tr => tr(4).toInt)
val totals = amounts.reduceByKey((acc, n) => (acc + n))

val prodNames = sc.textFile("/home/alumno/practicas/sistemas-distribuidos/practica4/products.csv").map(line => line.split(",")).map(pr => (pr(0).toInt, pr(1).toString))

val joined = totals.join(prodNames)

val sortedJoin = joined.sortBy(_._2._1,false)


// Cuarta.1
sortedJoin.first._2._2

val notSold = prodNames.subtractByKey(totals)

// Quinta

val transactionsFile = sc.textFile("/home/alumno/practicas/sistemas-distribuidos/practica4/transactions.csv").map(line => line.split(","))
val transations = transactionsFile.map(pr => (pr(2).toInt, pr(6).toString))
val stockholmTransations = transations.filter(value => (value._2 == "Stockholm"))
stockholmTransations.distinct.foreach(x => println(x._1))

// Sexta
val transationsQuantity = transactionsFile.map( tr => (tr(2).toInt, tr(4).toInt))
transationsQuantity.sortBy(_._2, false).first

// Septima
val transationsQuantity = transactionsFile.map( tr => (tr(2).toInt, tr(4).toInt))
val transactionsQuantityValues = transationsQuantity.reduceByKey((x,y) => (x + y))
val transactionsQuantityValuesFiltered = transactionsQuantityValues.filter(tr => tr._2 >= 80 && tr._2 <= 100)
transactionsQuantityValuesFiltered.foreach(x => println(x))

// Octava
val transactionClientMap = transactionsFile.map(pr => (pr(1).toInt,pr(4).toInt))
val transactionClientMapFiltered = transactionClientMap.filter( quantity => quantity._2 >= 45)
transactionClientMapFiltered.keys.distinct.count