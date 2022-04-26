package fi.co_de.identifi_nz.data

import fi.co_de.identifi_nz.R

class Datasource {
    fun loadAffirmations(): List<Transaction> {
        return listOf<Transaction>(
            Transaction(R.string.transaction1, R.drawable.ic_transaction_verified),
            Transaction(R.string.transaction2, R.drawable.ic_request_outgoing),
            Transaction(R.string.transaction3, R.drawable.ic_request_incoming)
        )
    }
}