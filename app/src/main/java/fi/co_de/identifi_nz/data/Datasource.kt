package fi.co_de.identifi_nz.data

import fi.co_de.identifi_nz.R

class Datasource {

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