package com.example.gramedsaya.ui.profile

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gramedsaya.adapter.SharedPreferencesHelper
import com.example.gramedsaya.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // Check admin status and update UI accordingly
        val isAdmin = SharedPreferencesHelper.isAdmin(requireContext())
        updateLoginLogoutButton(isAdmin)

        binding.adminLoginButton.setOnClickListener {
            startActivity(Intent(context, AdminLoginActivity::class.java))
        }
        binding.adminLogoutButton.setOnClickListener {
            SharedPreferencesHelper.clearAdmin(requireContext())
            val intent = Intent(context, AdminLoginActivity::class.java)
            startActivity(intent)
            activity?.finish() // Optional: to close the current activity
        }

        return binding.root
    }

    private fun updateLoginLogoutButton(isAdmin: Boolean) {
        if (isAdmin) {
            binding.adminLoginButton.visibility = View.GONE
            binding.adminLogoutButton.visibility = View.VISIBLE
        } else {
            binding.adminLoginButton.visibility = View.VISIBLE
            binding.adminLogoutButton.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}