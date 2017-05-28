package com.example.p3.redevent.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Carlos on 28/05/2017.
 */

public class ConfiguracaoFirebase {
    private static DatabaseReference refFirebase;
    private static FirebaseAuth autentic;

    public static DatabaseReference getFirebase() {
        if (refFirebase == null){
            refFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return refFirebase;

    }
    public static FirebaseAuth getFirebaseAutentic(){
        if (autentic == null){
            autentic = FirebaseAuth.getInstance();
        }
        return autentic;
    }
}
