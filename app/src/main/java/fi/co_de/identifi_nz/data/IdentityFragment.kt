package fi.co_de.identifi_nz.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class IdentityFragment(
    @DrawableRes val iconResourceId: Int,
    @DrawableRes val statusResourceId: Int,
    @StringRes val itemDescriptionResourceId: Int,
    @StringRes val statusDescriptionResourceId: Int
)
