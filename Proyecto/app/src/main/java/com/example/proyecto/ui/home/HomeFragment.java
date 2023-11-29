package com.example.proyecto.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyecto.R;
import com.example.proyecto.databinding.FragmentHomeBinding;
import com.example.proyecto.fragment_cupones;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private Button OrdenaYa;
    private Button Cupones;

    private ConstraintLayout CLprincipal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        OrdenaYa = binding.btnOrdenaYa;

        Cupones =binding.btnCupones;
        CLprincipal = binding.CLFragmentHom;
        Cupones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CLprincipal.setVisibility(View.GONE);
                //FragmentTransaction t = getParentFragmentManager().beginTransaction();
                //t.replace(R.id.ejemploFrame,new fragment_cupones());
                FragmentManager man = getParentFragmentManager();
                man.beginTransaction().replace(R.id.Constraint_hom,new fragment_cupones()).commit();
                //getParentFragmentManager().beginTransaction().commit();
                //t.commit();

            }
        });

        View root = binding.getRoot();
        OrdenaYa.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "COMIDA TIPICA MEXICANA, ORDENA YA!", Toast.LENGTH_SHORT).show();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}