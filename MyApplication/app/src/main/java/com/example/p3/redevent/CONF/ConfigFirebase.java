package com.example.p3.redevent.CONF;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Augusto on 28/05/2017.
 */

public class ConfigFirebase {
    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autentica;

    public static DatabaseReference getFirebase(){
        if (referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }
    public static FirebaseAuth getFirebaseAutentica(){
        if (autentica == null){
            autentica = FirebaseAuth.getInstance();
        }
        return autentica;

    }
}

