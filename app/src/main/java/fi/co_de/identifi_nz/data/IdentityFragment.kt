package fi.co_de.identifi_nz.data

import android.util.Log
import androidx.annotation.DrawableRes
import fi.co_de.identifi_nz.R
import fi.co_de.identifi_nz.network.json.IpfsIdentityFragment

class IdentityFragment(ipfsIdentityFragment: IpfsIdentityFragment) {

    companion object {
        private val TAG = IdentityFragment::class.java.simpleName
    }

    @DrawableRes
    val iconResourceId: Int = when (ipfsIdentityFragment.icon) {
        "badge" -> R.drawable.ic_identity_fragment_icon_badge
        "bank_account" -> R.drawable.ic_identity_fragment_icon_bank_account
        "document" -> R.drawable.ic_identity_fragment_icon_document
        "proof_of_address" -> R.drawable.ic_identity_fragment_icon_proof_of_address
        "wallet_address" -> R.drawable.ic_identity_fragment_icon_wallet_address
        else -> Log.e(TAG, "incorrect icon provided")
    }

    @DrawableRes
    val statusResourceId: Int = when (ipfsIdentityFragment.status) {
        "pending" -> R.drawable.ic_identity_fragment_status_pending
        "verified" -> R.drawable.ic_identity_fragment_status_verified
        else -> Log.e(TAG, "incorrect status provided")
    }

    @DrawableRes
    val statusDescriptionResourceId: Int = when (ipfsIdentityFragment.status) {
        "pending" -> R.string.status_pending
        "verified" -> R.string.status_verified
        else -> Log.e(TAG, "incorrect status provided")
    }

    val itemDescription: String = ipfsIdentityFragment.item_description
}

