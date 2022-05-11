package fi.co_de.identifi_nz.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import fi.co_de.identifi_nz.R
import fi.co_de.identifi_nz.data.ActivityCardAdapter
import fi.co_de.identifi_nz.data.Datasource
import fi.co_de.identifi_nz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Load transactions from datasource
        val transactionDataset = Datasource().loadRecentTransactions()

        // Create recyclerview adapter
        val recyclerView = root.findViewById<RecyclerView>(R.id.recent_activity_list)
        recyclerView.adapter = ActivityCardAdapter(requireContext(), transactionDataset)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}