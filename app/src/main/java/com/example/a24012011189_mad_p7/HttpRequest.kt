package com.example.a24012011189_mad_p7

object HttpRequest {
    private val fallbackPersons = listOf(
        Person("1", "Yesenia Yang", "yesenia_yang@gnu.ac.in", "+917983385148", "16 Sackett Street, Courtland, Michigan", 42.8466, -85.2934),
        Person("2", "Sosa Shaffer", "sosa_shaffer@gnu.ac.in", "+917979501912", "99 Union Street, Nicholson, Iowa", 41.8781, -87.6298),
        Person("3", "Roberts Gill", "roberts_gill@gnu.ac.in", "+919241276734", "39 Tompkins Place, Bartonsville, Oklahoma", 36.7468, -95.9758),
        Person("4", "Penelope Cooper", "penelope_cooper@gnu.ac.in", "+916836390000", "35 Aitken Place, Orovada, Maryland", 39.0458, -76.6413),
        Person("5", "Park George", "park_george@gnu.ac.in", "+918895814051", "95 Woods Place, Ahwahnee, Nebraska", 41.4925, -99.9018),
        Person("6", "Deloris Lawson", "deloris_lawson@gnu.ac.in", "+918379880401", "72 Troutman Street, Mooresburg, South Dakota", 44.3683, -100.3510),
        Person("7", "Conley Hickman", "conley_hickman@gnu.ac.in", "+918258363534", "38 Bath Avenue, Coalmont, Florida", 28.3781, -81.5700),
        Person("8", "Horne Koch", "horne_koch@gnu.ac.in", "+918797996598", "95 Woods Place, Ahwahnee, Nebraska", 37.3382, -121.8863)
    )

    /**
     * Returns bundled demonstration records.
     *
     * Private provider credentials must not be embedded in an Android client:
     * values packaged in an APK can be extracted. Restore remote fetching only
     * through a trusted backend proxy that keeps credentials server-side.
     */
    fun fetchPersons(): List<Person> = fallbackPersons
}
