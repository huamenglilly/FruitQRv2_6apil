package kiky.beam.lilly.th.ac.rmutk.fruitqr;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowProductFragment extends Fragment {

    private String idProduct;
    private String titleToolbar = "รายละเอียดผลิตภัณฑ์";
    //เรียก Table 3 ชุด
    private ArrayList<String> productStringArrayList, framerStringArrayList, userStringArrayList, userFramerStringArrayList;
    private Myconstant myconstant = new Myconstant(); //php


    public ShowProductFragment() {
        // Required empty public constructor
    }

    public static ShowProductFragment showProductInstance(String idProduct) {
        ShowProductFragment showProductFragment = new ShowProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString("idProduct", idProduct);
        showProductFragment.setArguments(bundle);
        return showProductFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        idProduct = getArguments().getString("idProduct");
        Log.d("27AprilV3", "Receive idProduct in Fragment ==> " + idProduct);

        //สร้าง Toolbar
        createToolbar();

        //โหลดเดต้า
        loadData();

    }//Main Method

    private void loadData() {
        productStringArrayList = new ArrayList<>();
        framerStringArrayList = new ArrayList<>();
        userStringArrayList = new ArrayList<>();
        userFramerStringArrayList  = new ArrayList<>();

        String[] columnDetailProduct = myconstant.getColumnDetailProduct(); //ดึงค่าจาก myconstane


        try {

            //สำหรับ Product
            GetDataWhereOneColumn getDataWhereOneColumn = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn.execute("id", idProduct, myconstant.getUrlGetProductWhereId());
            String jsonProduct = getDataWhereOneColumn.get();
            Log.d("27AprilV3", "jsonProduct ==>>> " + jsonProduct);

            JSONArray jsonArray = new JSONArray(jsonProduct);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i = 0; i < columnDetailProduct.length; i += 1) {
                productStringArrayList.add(jsonObject.getString(columnDetailProduct[i]));
                Log.d("18AprilV3", "productStringArrayList[" + i + "] ==> " + productStringArrayList.get(i));
            }

            Log.d("27ApriV4", "id Sent ==> " + productStringArrayList.get(4));

            //สำหรับ Framer
//            GetDataWhereOneColumn getDataWhereOneColumn1 = new GetDataWhereOneColumn(getActivity());
//            getDataWhereOneColumn1.execute("id", productStringArrayList.get(4), myconstant.getUrlGetFramerWhereId()); //อยู่ในตำแหน่งที่ 4 idFramer
//            String jsonFramer = getDataWhereOneColumn1.get();
//            Log.d("27AprilV4", "jsonFramer ==>>> " + jsonFramer);
//            String[] columnDetailFramer = myconstant.getColumnDetailFramer();
//
//            JSONArray jsonArray1 = new JSONArray(jsonFramer);
//            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
//            for (int i = 0; i < columnDetailFramer.length; i += 1) {
//                framerStringArrayList.add(jsonObject1.getString(columnDetailFramer[i]));
//                Log.d("18AprilV4", "framerStringArrayList[" + i + "] ==> " + framerStringArrayList.get(i));
//            }

            //สำหรับ User
            GetDataWhereOneColumn getDataWhereOneColumn2 = new GetDataWhereOneColumn(getActivity());
            getDataWhereOneColumn2.execute("id", productStringArrayList.get(1),myconstant.getUrlGetUserWhereId());
            String jsonUser = getDataWhereOneColumn2.get();
            Log.d("27AprilV5", "jsonUser ==>>> " + jsonUser);

            JSONArray jsonArray2 = new JSONArray(jsonUser);
            JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
            String[] columnUser = myconstant.getColumnUser();
            for (int i = 0; i < columnUser.length; i += 1) {
                userStringArrayList.add(jsonObject2.getString(columnUser[i]));
                Log.d("18AprilV5", "userStringArrayList[" + i + "] ==> " + userStringArrayList.get(i));
            }

            //สำหรับ For Farmer
//            GetDataWhereOneColumn farmerGetDataWhereOneColumn2 = new GetDataWhereOneColumn(getActivity());
//            farmerGetDataWhereOneColumn2.execute("id", framerStringArrayList.get(1), myconstant.getUrlGetUserWhereId());
//            String result = farmerGetDataWhereOneColumn2.get();
//            Log.d("15MayV1", "result ==> " + result);
//
//            JSONArray farmerJsonArray2 = new JSONArray(result);
//            JSONObject farmerJsonObject2 = farmerJsonArray2.getJSONObject(0);
//
//            TextView nameFarmerTextView = getView().findViewById(R.id.txtFruitName);
//            nameFarmerTextView.setText(farmerJsonObject2.getString("Name"));
//
//            TextView addFarmerTextView = getView().findViewById(R.id.txtFruitAdd);
//            addFarmerTextView.setText(farmerJsonObject2.getString("Address"));
//
//            TextView phoneFarmerTextView = getView().findViewById(R.id.txtFruitPhone);
//            phoneFarmerTextView.setText(farmerJsonObject2.getString("Phone"));


//            //สำหรับ Framer
//            GetDataWhereOneColumn getDataWhereOneColumn3 = new GetDataWhereOneColumn(getActivity());
//            getDataWhereOneColumn3.execute("id", framerStringArrayList.get(1), myconstant.getUrlGetUserWhereId()); //อยู่ในตำแหน่งที่ 1
//            String jsonUserFramer = getDataWhereOneColumn3.get();
//            Log.d("28AprilV5", "jsonFramer ==>>> " + jsonUserFramer);
//            String[] columnUserFarmer = myconstant.getColumnDetailFramer();
//
//            JSONArray jsonArray3 = new JSONArray(jsonUserFramer);
//            JSONObject jsonObject3 = jsonArray3.getJSONObject(0);
//            for (int i = 0; i < columnUserFarmer.length; i += 1) {
//                framerStringArrayList.add(jsonObject3.getString(columnUserFarmer[i]));
//                Log.d("28AprilV5", "framerStringArrayList[" + i + "] ==> " + framerStringArrayList.get(i));
//            }



            //Famer User



//            Image Product
            ImageView imageView = getView().findViewById(R.id.imvImage);
            Picasso.get().load(productStringArrayList.get(7)).resize(800,600).into(imageView);

//          name product
            TextView nameeTextView = getView().findViewById(R.id.txtNamee);
            nameeTextView.setText(productStringArrayList.get(5));


//          Amount Product
            TextView productamountTextView = getView().findViewById(R.id.txtProductAmount);
            productamountTextView.setText(productStringArrayList.get(8));

//          Unit Product
            TextView productunitTextView = getView().findViewById(R.id.txtProductUnit);
            productunitTextView.setText(productStringArrayList.get(9));

//          Date Product
            TextView productdateTextView = getView().findViewById(R.id.txtProductdate);
            productdateTextView.setText(productStringArrayList.get(10));

//          List Product
            TextView productlistTextView = getView().findViewById(R.id.txtProductlist);
            productlistTextView.setText(productStringArrayList.get(6));

//          Frist Name Product
            TextView productnameTextView = getView().findViewById(R.id.txtProductName);
            productnameTextView.setText(productStringArrayList.get(2));




        } catch (Exception e) {
            Log.d("18AprilV3", "e ==>> " + e.toString());
        }

    }

    private void createToolbar() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarProduct);
        ((ProductActivity) getActivity()).setSupportActionBar(toolbar);
        ((ProductActivity) getActivity()).getSupportActionBar().setTitle(titleToolbar);
        ((ProductActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true); //สร้างปุ่ม
        ((ProductActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_product, container, false);
    }

}