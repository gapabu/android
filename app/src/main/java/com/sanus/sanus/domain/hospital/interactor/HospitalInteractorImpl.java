package com.sanus.sanus.domain.hospital.interactor;

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

	public HospitalInteractorImpl(HospitalPresenter presenter){this.presenter = presenter;}

	@Override
	public void viewComents() {
		FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
		mFirestore.collection("hospitales").addSnapshotListener(new EventListener<QuerySnapshot>() {
			@Override
			public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
				if (e != null) {
					Log.d(TAG, "Error: " + e.getMessage());
				}
				for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
					if (doc.getType() == DocumentChange.Type.ADDED) {
						String nombre = doc.getDocument().getString("nombre");
						String direccion = doc.getDocument().getString("direccion");

						commentsDoctorList.add(new Hospital(nombre, direccion));
						presenter.setDataAdapter(commentsDoctorList);
					}
				}
			}
		});
	}
}