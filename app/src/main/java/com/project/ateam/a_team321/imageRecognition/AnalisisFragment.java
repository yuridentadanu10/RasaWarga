
package com.project.ateam.a_team321.imageRecognition;





import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.common.modeldownload.FirebaseLocalModel;
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceAutoMLImageLabelerOptions;
import com.project.ateam.a_team321.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.project.ateam.a_team321.imageRecognition.BaseFragment.RC_SELECT_PICTURE;
import static com.project.ateam.a_team321.imageRecognition.BaseFragment.RC_STORAGE_PERMS1;
import static com.project.ateam.a_team321.imageRecognition.BaseFragment.RC_STORAGE_PERMS2;
import static com.project.ateam.a_team321.imageRecognition.BaseFragment.RC_TAKE_PICTURE;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnalisisFragment extends BaseFragment implements View.OnClickListener {
    private ImageView mImageView;
    private TextView mTextView;
    private static final String TAG = "Image Scanner";
    private static final String LOCAL_MODEL_NAME = "my_local_model";
    private FirebaseVisionImageLabeler labeler;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    CardView btnPhoto, btnGallery;


    public String  makanan;
    public   float deteksijelek =0;


    public AnalisisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_analisis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView = view.findViewById(R.id.place_holder);
        mTextView = view.findViewById(R.id.confident_level);
        btnGallery = view.findViewById(R.id.btn_gallery);
        btnPhoto = view.findViewById(R.id.btn_kamera);


        FirebaseLocalModel localModel = new FirebaseLocalModel.Builder(LOCAL_MODEL_NAME)
                .setAssetFilePath("automl/manifest.json")
                .build();



        FirebaseModelManager manager = FirebaseModelManager.getInstance();
        manager.registerLocalModel(localModel);


        FirebaseVisionOnDeviceAutoMLImageLabelerOptions labelerOptions = new FirebaseVisionOnDeviceAutoMLImageLabelerOptions
                .Builder()
                .setLocalModelName(LOCAL_MODEL_NAME)
                .setConfidenceThreshold(0.5f)
                .build();

        try {
            labeler = FirebaseVision.getInstance().getOnDeviceAutoMLImageLabeler(labelerOptions);
        } catch (FirebaseMLException e) {
            mTextView.setTextColor(Color.RED);
            mTextView.setText(e.getMessage());
        }

        btnPhoto.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RC_STORAGE_PERMS1:
                case RC_STORAGE_PERMS2:
                    checkStoragePermission(requestCode);
                    break;
                case RC_SELECT_PICTURE:
                    Uri dataUri = data.getData();
                    String path = MyHelper.getPath(getContext(), dataUri);
                    if (path == null) {
                        bitmap = MyHelper.resizeImage(imageFile, getContext(), dataUri, mImageView);
                    } else {
                        bitmap = MyHelper.resizeImage(imageFile, path, mImageView);
                    }
                    if (bitmap != null) {
                        mTextView.setText(null);
                        mImageView.setImageBitmap(bitmap);
                        runMyModel(bitmap);
                    }
                    break;
                case RC_TAKE_PICTURE:
                    bitmap = MyHelper.resizeImage(imageFile, imageFile.getPath(), mImageView);

                    if (bitmap != null) {
                        mTextView.setText(null);
                        mImageView.setImageBitmap(bitmap);
                        runMyModel(bitmap);
                    }
                    break;
            }
        }
    }




    private void runMyModel(Bitmap bitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
        labeler.processImage(image).addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
            @Override
            public void onSuccess(List<FirebaseVisionImageLabel> labels) {
                extractLabel(labels);
                Log.d(TAG, "onSuccess:JAAAAAAAAAAAAAAAAANGKTIK  "+ makanan);
                if(deteksijelek>0.5) {
                    //cekdatabase(makanan);

                }
                else{

                    Toast.makeText(getContext(),
                            "Scan gambar gagal karena gambar yang anda ambil kurang bagus! Silahkan scan ulang ya :)  ", Toast.LENGTH_SHORT).show();
                }
              //  Log.d(TAG, "onSuccess: OOOOOOOOOOOOOOOOOOOOOOOOO"+calorie);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mTextView.setTextColor(Color.RED);
                mTextView.setText(e.getMessage());
            }
        });
    }

    private void extractLabel(List<FirebaseVisionImageLabel> labels) {
        float temp = 0;
        for (FirebaseVisionImageLabel label : labels) {

            mTextView.setText(label.getText() + "\n");
            mTextView.setText(label.getConfidence() + "\n\n");
            Log.d(TAG, "labels "+labels+" label ===="+label.getText() +" TEXTVIEW ==="+mTextView );


            String  makananSementara = label.getText();
            float  deteksijelekSementara = label.getConfidence();


            if(deteksijelekSementara>temp){
                makanan = makananSementara;
                deteksijelek = deteksijelekSementara;
                temp =  deteksijelekSementara;
            }

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_kamera:
                checkStoragePermission(RC_STORAGE_PERMS1);
                break;
            case R.id.btn_gallery:
                checkStoragePermission(RC_STORAGE_PERMS2);
                break;

        }
    }
}
