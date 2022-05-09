package fi.co_de.identifi_nz.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import fi.co_de.identifi_nz.R
import fi.co_de.identifi_nz.data.Datasource
import fi.co_de.identifi_nz.data.IdentityFragmentCardAdapter
import fi.co_de.identifi_nz.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Load identity fragments from datasource
        val identityFragmentDataset = Datasource().loadIdentityFragments()

        // Create recyclerview adapter
        val recyclerView = root.findViewById<RecyclerView>(R.id.identity_fragment_list)
        recyclerView.adapter =
            IdentityFragmentCardAdapter(requireContext(), identityFragmentDataset)

        recyclerView.setHasFixedSize(true)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}