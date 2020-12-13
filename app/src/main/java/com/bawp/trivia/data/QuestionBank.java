package com.bawp.trivia.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bawp.trivia.controller.AppController;
import com.bawp.trivia.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuestionBank {
    ArrayList<Question> questionArrayList = new ArrayList<>();
    private String url = "https://raw.githubusercontent.com/zrentia/Questions/master/Bank?token=ARP67IHOEBEM52CSZEMSXRC72VXRC";
    public List<Question> getQuestions() {
        questionArrayList.add(new Question("Greenhouse gasses trap heat in the earth’s atmosphere.", true));
        questionArrayList.add(new Question("Recycling helps reduce greenhouse gases.", true));
        questionArrayList.add(new Question("Our sun contributes to climate change.", false));
        questionArrayList.add(new Question("Climate change is a change in the earth’s conditions.", true));
        questionArrayList.add(new Question("Climate change is a natural process.", true));
        questionArrayList.add(new Question("Saving water reduces climate change impacts.", false));
        questionArrayList.add(new Question("Consequences of climate change are rising sea levels.", true));
        questionArrayList.add(new Question("A consequence of climate change is drought.", true));
        questionArrayList.add(new Question("Consequences of climate change are heat waves.", true));
        questionArrayList.add(new Question("The use of cars is one of the main reasons that speeds up climate change.", true));
        questionArrayList.add(new Question("Droughts are becoming more frequent because of climate change.", true));
        questionArrayList.add(new Question("Mangrove trees are known to protect people from climate change disasters, such as from rising sea levels and hurricanes", true));
        questionArrayList.add(new Question("It is a big deal that the average temperature of the earth is 1.9 F degrees hotter than it was in 1880.", true));
        questionArrayList.add(new Question("Sea level rising will not affect communities.", false));
        questionArrayList.add(new Question("Carbon dioxide emissions of humans mainly contribute to the speed of climate change.", true));
        questionArrayList.add(new Question("Climate change affects animals.", true));
        questionArrayList.add(new Question("Climate change affects the ocean.", true));
        questionArrayList.add(new Question("Burning of fossil fuels slows down climate change.", true));
        questionArrayList.add(new Question("Deforestation contributes to the speed of climate change.", true));
        questionArrayList.add(new Question("The main reason for rising sea levels is more precipitation.", false));
        questionArrayList.add(new Question("An effect of climate change are changes in precipitation patterns.", true));
        questionArrayList.add(new Question("Production of livestock contributes to climate change.", true));
        questionArrayList.add(new Question("Climate change leads to more health outbreaks and epidemics.", true));
        questionArrayList.add(new Question("People living in islands are more vulnerable to climate change effects.", true));
        questionArrayList.add(new Question("Farming contributes to climate change.", true));
        questionArrayList.add(new Question("Nitrogen rich fertilizers are able to trap 50 more times of heat than carbon dioxide.", false));
        questionArrayList.add(new Question("Oil drilling emits carbon dioxide into the atmosphere.", true));
        questionArrayList.add(new Question("Garbage contributes to climate change", true));

        return questionArrayList;
    }
    public List<Question> getQuestions(final AnswerListAsyncResponse callBack) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                Question question = new Question();
                                question.setAnswer(response.getJSONArray(i).get(0).toString());
                                question.setAnswerTrue(response.getJSONArray(i).getBoolean(1));



                                //Add question objects to list
                                questionArrayList.add(question);
                                //Log.d("Hello", "onResponse: " + question.getAnswer());


                               // Log.d("JSON", "onResponse: " + response.getJSONArray(i).get(0));
                                //Log.d("JSON2", "onResponse: " + response.getJSONArray(i).getBoolean(1));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (null != callBack) callBack.processFinished(questionArrayList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            System.out.println("testing" +error);
            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);


        return questionArrayList;

    }
}
