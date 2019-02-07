package sbts.dmw.com.sbtrackingsystem.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import sbts.dmw.com.sbtrackingsystem.R;
import sbts.dmw.com.sbtrackingsystem.adapter.RecyclerViewAdapter;
import sbts.dmw.com.sbtrackingsystem.classes.SingletonClass;
import sbts.dmw.com.sbtrackingsystem.model.Student;

public class StudentList extends Fragment {

    private JsonArrayRequest request;
    private List<Student> studentList;
    private RecyclerView recyclerView;
    private ProgressBar loading;
    RecyclerViewAdapter sAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_list, container, false);

        studentList = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recycler_view);
        loading = v.findViewById(R.id.loading_student_list);

        request = new JsonArrayRequest(getText(R.string.recycle_url).toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject;

                for (int i = response.length() - 1; i >= 0; i--) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Student s = new Student();
                        s.setName(jsonObject.getString("Name"));
                        s.setDivision(jsonObject.getString("Division"));
                        s.setRoll_no(jsonObject.getString("Rollno"));
                        s.setS_class(jsonObject.getString("Class"));
                        s.setPhoto(jsonObject.getString("Photo"));
                        studentList.add(s);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                loading.setVisibility(View.GONE);

                sAdapter = new RecyclerViewAdapter(getActivity().getApplicationContext(), studentList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                recyclerView.setAdapter(sAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        SingletonClass.getInstance(getContext()).addToRequestQueue(request);

        return v;

    }
}
