package com.example.task.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.task.R
import com.example.task.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {

    lateinit var binding: FragmentSignInBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        // Cek apakah pengguna sudah login
        if (firebaseAuth.currentUser != null) {
            // Jika pengguna sudah login, navigasi ke HomeFragment
            navigateToFragment(HomeFragment())
        }

        // Login ketika tombol login ditekan
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val pass = binding.passwordInput.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Jika login sukses, navigasi ke HomeFragment
                        navigateToFragment(HomeFragment())
                    } else {
                        // Tampilkan pesan error
                        Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Navigasi ke SignUpFragment ketika link register ditekan
        binding.registerLink.setOnClickListener {
            navigateToFragment(SignUpFragment())
        }
    }

    // Function to replace fragment using FragmentTransaction
    private fun navigateToFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Ganti fragment saat ini dengan fragment baru
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.addToBackStack(null)  // Tambahkan ke back stack jika diperlukan
        fragmentTransaction.commit()
    }
}
