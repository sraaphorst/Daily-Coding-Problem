package dcp.day232
// day232.kt
// By Sebastian Raaphorst, 2019.

class PrefixMapSum(val prefixesMap: MutableMap<String, Int> = mutableMapOf()) {
    fun insert(key: String, value: Int) {
        prefixesMap[key] = value
    }

    fun sum(prefix: String) = prefixesMap.keys.filter { it.startsWith(prefix) }.map{ prefixesMap[it] }.filterNotNull().sum()
}
