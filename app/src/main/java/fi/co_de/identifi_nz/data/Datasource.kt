package fi.co_de.identifi_nz.data

import fi.co_de.identifi_nz.R

class Datasource {
    fun loadRecentTransactions(): List<Transaction> {
        return listOf(
            Transaction(R.string.transaction1, R.drawable.ic_activity_icon_verified),
            Transaction(R.string.transaction2, R.drawable.ic_activity_icon_request_outgoing),
            Transaction(R.string.transaction3, R.drawable.ic_activity_icon_request_incoming),
            Transaction(R.string.transaction4, R.drawable.ic_activity_icon_rejected),
            Transaction(R.string.transaction5, R.drawable.ic_activity_icon_request_outgoing),
            Transaction(R.string.transaction6, R.drawable.ic_activity_icon_verified),
            Transaction(R.string.transaction7, R.drawable.ic_activity_icon_verified),
            Transaction(R.string.transaction8, R.drawable.ic_activity_icon_request_outgoing),
            Transaction(R.string.transaction9, R.drawable.ic_activity_icon_request_outgoing),
        )
    }

    fun loadIdentityFragments(): List<IdentityFragment> {
        return listOf(
            IdentityFragment(
                R.drawable.ic_identity_fragment_icon_badge,
                R.drawable.ic_identity_fragment_status_pending,
                R.string.badge,
                R.string.status_pending
            ),
            IdentityFragment(
                R.drawable.ic_identity_fragment_icon_bank_account,
                R.drawable.ic_identity_fragment_status_verified,
                R.string.bank_account,
                R.string.status_verified
            ),
            IdentityFragment(
                R.drawable.ic_identity_fragment_icon_document,
                R.drawable.ic_identity_fragment_status_verified,
                R.string.document,
                R.string.status_verified
            ),
            IdentityFragment(
                R.drawable.ic_identity_fragment_icon_proof_of_address,
                R.drawable.ic_identity_fragment_status_verified,
                R.string.proof_of_address,
                R.string.status_verified
            ),
            IdentityFragment(
                R.drawable.ic_identity_fragment_icon_wallet_address,
                R.drawable.ic_identity_fragment_status_verified,
                R.string.wallet_address,
                R.string.status_verified
            ),
        )
    }

}