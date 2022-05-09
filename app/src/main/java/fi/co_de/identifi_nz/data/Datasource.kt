package fi.co_de.identifi_nz.data

import fi.co_de.identifi_nz.R

class Datasource {
    fun loadRecentTransactions(): List<Transaction> {
        return listOf(
            Transaction(R.string.transaction1, R.drawable.ic_transaction_verified),
            Transaction(R.string.transaction2, R.drawable.ic_request_outgoing),
            Transaction(R.string.transaction3, R.drawable.ic_request_incoming),
            Transaction(R.string.transaction4, R.drawable.ic_transaction_rejected),
            Transaction(R.string.transaction5, R.drawable.ic_request_outgoing),
            Transaction(R.string.transaction6, R.drawable.ic_transaction_verified),
            Transaction(R.string.transaction7, R.drawable.ic_transaction_verified),
            Transaction(R.string.transaction8, R.drawable.ic_request_outgoing),
            Transaction(R.string.transaction9, R.drawable.ic_request_outgoing),
        )
    }
}