package com.sanus.sanus.domain.hospital.interactor;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sanus.sanus.domain.hospital.data.Hospital;
import com.sanus.sanus.domain.hospital.presenter.HospitalPresenter;

import java.util.ArrayList;
import java.util.List;


public class HospitalInteractorImpl implements HospitalInteractor{
	private final String TAG = this.getClass().getSimpleName();
	private HospitalPresenter presenter;

	private List<Hospital> commentsDoctorList = new ArrayList<>();
	private List<Hospital> listAuxiliar = new ArrayList<>();

	public HospitalInteractorImpl(HospitalPresenter presenter){this.presenter = presenter;}

	@Override
	public void viewHospital() {
		FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
		mFirestore.collection("hospitales").addSnapshotListener(new EventListener<QuerySnapshot>() {
			@Override
			public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
				if (e != null) {
					Log.d(TAG, "Error: " + e.getMessage());
				}
				for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
					if (doc.getType() == DocumentChange.Type.ADDED) {
						final String user_id = doc.getDocument().getId();
						String nombre = doc.getDocument().getString("nombre");
						String direccion = doc.getDocument().getString("direccion");

						commentsDoctorList.add(new Hospital(nombre, direccion, user_id));
						presenter.setDataAdapter(commentsDoctorList);
					}
				}
			}
		});
	}

	@Override
	public void buscador(String texto) {
		listAuxiliar.clear();

		if(texto.isEmpty()){
			presenter.setDataAdapter(commentsDoctorList);
			return;
		}

		for (int i = 0; i < commentsDoctorList.size(); i++) {
			if(commentsDoctorList.get(i).getNombre().toLowerCase().contains(texto.toLowerCase())){
				listAuxiliar.add(new Hospital(commentsDoctorList.get(i).getNombre(),commentsDoctorList.get(i).getDireccion()
												,commentsDoctorList.get(i).getId()));
			}
		}
		presenter.setDataAdapter(listAuxiliar);
	}

	@Override
	public void validateButtonEnable() {
		/*if (presenter.getEmail().matches(RegexUtils.emailPattern()) && presenter.getPassword().length() > 6) {
			presenter.enableButton();
			return;
		}
		presenter.disableButton();*/
		presenter.enableButton();
	}

    @Override
    public void getIdDoct(){
    }
}